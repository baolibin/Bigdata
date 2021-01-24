package com.libin.etl.dumper

import com.libin.common.SparkJobBase
import com.libin.etl.jobs.DumperJob
import com.libin.etl.jobs.DumperJob.{FeatureDataStruct, getSortedColumns}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Copyright (c) 2021/1/24. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 
  */
object ParquetDumper extends SparkJobBase {

    /**
      * RDD数据转换存储成JSON格式
      *
      * @param sparkSession SparkSession对象实例
      * @param featureRdd   需要转换的数据
      * @param path         存储的路径
      * @param partitions   落盘分区数
      */
    def processRDDParquet(sparkSession: SparkSession,
                          featureRdd: RDD[FeatureDataStruct],
                          path: String,
                          partitions: Int): DataFrame = {
        val (columns, sortedFeatureId) = getSortedColumns(featureRdd)
        println(s"columns=${columns}")
        val culumnLink = columns.split(separator).map(x => "\"" + x + "\"").mkString(separator)
        println(s"culumnLink=${culumnLink}")
        val parquetRdd: RDD[Product] = featureRdd.keyBy(_.userId)
                .groupByKey()
                .map {
                    case (uid, iter) =>
                        val struts = iter.toList.map(x => (x.featureId, s"${x.featureValue}")).toMap
                        val line = ParquetDumper.transStructToString(uid, sortedFeatureId, struts)
                        toTuple(line.split(separator).toList)
                }
        //parquetRdd.collect().foreach(println)
        import sparkSession.implicits._
        parquetRdd.toDF(culumnLink.split(separator).toSeq: _*)
    }

    /**
      * 用户user_id对应的特征摊平,缺省值的特征用None填充.
      */
    def transStructToString(uid: String,
                            featureIdList: List[Int],
                            struts: Map[Int, String]): String = {
        val sb = new StringBuilder
        sb.append(uid)
        for (i <- featureIdList.indices) {
            if (struts.contains(featureIdList(i))) sb.append(separator + struts(featureIdList(i)))
            else sb.append(separator + "None")
        }
        sb.toString()
    }

    /**
      * 列表转换成元组
      */
    def toTuple[A <: Object](as: List[A]): Product = {
        val tupleClass = Class.forName("scala.Tuple" + as.size)
        tupleClass.getConstructors.apply(0).newInstance(as: _*).asInstanceOf[Product]
    }


    def main(args: Array[String]): Unit = {
        val sparkSession = createSparkSessionLocal()
        // processDataFrameParquet(sparkSession, DumperJob.generateTestData(sparkSession))
        processDataFrameParquetTest(sparkSession, DumperJob.generateTestData(sparkSession))

        /*val spark = createSparkSessionLocal()
        import spark.implicits._

        val list = List("uidId", "field1", "None", "field3", "field4")
        val frame = spark.sparkContext.makeRDD(Array(list))
                .map {
                    x =>
                        toTupleStr(x)
                }.map(x => x)
        frame.collect().foreach(println)
        val dataFrame = frame.toDF(/*"uidId", "field1", "None", "field3", "field4"*/)
        println(dataFrame.schema)
        println(dataFrame.show())*/

    }

    def processDataFrameParquetTest(sparkSession: SparkSession,
                                    featureRdd: RDD[FeatureDataStruct]): Unit = {
        val columnsList: List[(Int, String)] = featureRdd.map(x => (x.featureId, x.featureKey)).collect().toList
        // 数据落盘格式
        val columns = s"uid_id,${columnsList.sortBy(_._1).map(_._2).mkString(",")}"
        val culumnLink = columns.split(separator).map(x => "\"" + x + "\"").mkString(separator)
        val sortedFeatureId: List[Int] = columnsList.map(_._1).sorted

        import sparkSession.implicits._
        val frame = featureRdd.toDS()
        val dataset = frame.groupByKey(_.userId).mapGroups {
            case (uid, iter) =>
                // featureId到featureValue映射
                val struts = iter.toList.map(x => (x.featureId, x.featureValue)).toMap
                transStructToString(uid, sortedFeatureId, struts)
        }.toDF("value")

        /**
          * |"uid_id","key_111","key_222","key_333"|
          * +--------------------------------------+
          * |                  [uid_1, value_111...|
          * |                  [uid_2, None, val...|
          * +--------------------------------------+
          */
        /**
          * StructType(StructField(value,StringType,true))
          * +--------------------+
          * |               value|
          * +--------------------+
          * |uid_1,value_111,N...|
          * |uid_2,None,value_...|
          * +--------------------+
          */
        // println(dataset.schema)
        // println(dataset.show())

        import org.apache.spark.sql.functions._
        import sparkSession.implicits._
        var newDF = dataset.withColumn("splitCols", split($"value", separator))

        /**
          * StructType(StructField(value,StringType,true), StructField(splitCols,ArrayType(StringType,true),true))
          * +--------------------+--------------------+
          * |               value|           splitCols|
          * +--------------------+--------------------+
          * |uid_1,value_111,N...|[uid_1, value_111...|
          * |uid_2,None,value_...|[uid_2, None, val...|
          * +--------------------+--------------------+
          */
        // println(newDF.schema)
        // println(newDF.show())

        val numAttrs = dataset.first().toString().split(separator).length
        val attrs = Array.tabulate(numAttrs)(n => "col_" + n)
        attrs.zipWithIndex.foreach(x => {
            newDF = newDF.withColumn(x._1, $"splitCols".getItem(x._2))
        })

        /**
          * +--------------------+--------------------+-----+---------+---------+---------+
          * |               value|           splitCols|col_0|    col_1|    col_2|    col_3|
          * +--------------------+--------------------+-----+---------+---------+---------+
          * |uid_1,value_111,N...|[uid_1, value_111...|uid_1|value_111|     None|value_333|
          * |uid_2,None,value_...|[uid_2, None, val...|uid_2|     None|value_222|     None|
          * +--------------------+--------------------+-----+---------+---------+---------+
          */
        // println(newDF.schema)
        // println(newDF.show())

        // 字段到key的映射
        var count = -1
        val colKeyMap = columnsList.sortBy(_._1).map {
            x =>
                count = count + 1
                (s"col_${count}", x._2)
        }.toMap

        // 字段转换成key
        println("colKeyMap=" + colKeyMap.mkString("|"))
        val featureIdList = columnsList.map(_._1)
        for (i <- featureIdList.indices) {
            newDF = newDF.withColumnRenamed(s"col_${i}", colKeyMap(s"col_${i}"))
        }
        newDF = newDF.drop("value", "splitCols")

        /**
          * StructType(StructField(key_111,StringType,true), StructField(key_222,StringType,true), StructField(key_333,StringType,true), StructField(col_3,StringType,true))
          * +-------+---------+---------+---------+
          * |key_111|  key_222|  key_333|    col_3|
          * +-------+---------+---------+---------+
          * |  uid_1|value_111|     None|value_333|
          * |  uid_2|     None|value_222|     None|
          * +-------+---------+---------+---------+
          */
        println(newDF.schema)
        println(newDF.show())
        writeParquetDataBySparkSql(DumperJob.path, newDF)
        // newDF
    }


    def processDataFrameParquet(sparkSession: SparkSession,
                                featureRdd: RDD[FeatureDataStruct]): DataFrame = {
        val columnsList: List[(Int, String)] = featureRdd.map(x => (x.featureId, x.featureKey)).collect().toList
        // 数据落盘格式
        val columns = s"uid_id,${columnsList.sortBy(_._1).map(_._2).mkString(",")}"
        val culumnLink = columns.split(separator).map(x => "\"" + x + "\"").mkString(separator)
        val sortedFeatureId: List[Int] = columnsList.map(_._1).sorted

        // 字段到key的映射
        var count = -1
        val colKeyMap = columnsList.sortBy(_._1).map {
            x =>
                count = count + 1
                (s"col_${count}", x._2)
        }.toMap


        import sparkSession.implicits._
        val frame = featureRdd.toDS()

        // println(frame.schema)
        // println(frame.show())
        /*val dataset = frame.groupByKey(_.userId).mapGroups{
            case (uid, iter) =>
                // featureId到featureValue映射
                val struts = iter.toList.map(x => (x.featureId, x.featureValue)).toMap
                transStructToString(uid, sortedFeatureId, struts).split(",")
        }.toDF(culumnLink)
        println(dataset.schema)
        println(dataset.show())*/

        frame.createOrReplaceTempView("resTable")
        // val sql = "select userId,concat_ws(',',collect_list(concat(featureValue))) from resTable group by userId"
        val sql = "select userId,concat_ws(',',collect_list(concat(featureId,':',featureValue))) as value from resTable group by userId"
        val dataFrame = sparkSession.sql(sql)

        /**
          * |userId|              value|
          * +------+-------------------+
          * | uid_1|value_111,value_333|
          * | uid_2|          value_222|
          * +------+-------------------+
          */
        println(dataFrame.schema)
        println(dataFrame.show())

        import org.apache.spark.sql.functions._
        import sparkSession.implicits._
        var newDF = dataFrame.withColumn("splitCols", split($"value", separator))

        /**
          * +------+-------------------+--------------------+---------+---------+-----+
          * |userId|              value|           splitCols|    col_0|    col_1|col_2|
          * +------+-------------------+--------------------+---------+---------+-----+
          * | uid_1|value_111,value_333|[value_111, value...|value_111|value_333| null|
          * | uid_2|          value_222|         [value_222]|value_222|     null| null|
          * +------+-------------------+--------------------+---------+---------+-----+
          */
        val numAttrs = dataFrame.first().toString().split(separator).length
        val attrs = Array.tabulate(numAttrs)(n => "col_" + n)
        attrs.zipWithIndex.foreach(x => {
            newDF = newDF.withColumn(x._1, $"splitCols".getItem(x._2))
        })

        // 字段转换成key
        println("colKeyMap=" + colKeyMap.mkString("|"))
        val featureIdList = columnsList.map(_._1)
        for (i <- featureIdList.indices) {
            newDF = newDF.withColumnRenamed(s"col_${i}", colKeyMap(s"col_${i}"))
        }
        newDF = newDF.drop("value", "splitCols")

        /**
          * StructType(StructField(userId,StringType,true), StructField(key_111,StringType,true), StructField(key_222,StringType,true), StructField(key_333,StringType,true))
          * +------+---------+---------+-------+
          * |userId|  key_111|  key_222|key_333|
          * +------+---------+---------+-------+
          * | uid_1|value_111|value_333|   null|
          * | uid_2|value_222|     null|   null|
          * +------+---------+---------+-------+
          */
        println(newDF.schema)
        println(newDF.show())
        newDF
        // writeParquetDataBySparkSql(DumperJob.path, newDF)
    }

    def toTupleStr(as: List[String]): Product = {
        val tupleClass = Class.forName("scala.Tuple" + as.size)
        tupleClass.getConstructors.apply(0).newInstance(as: _*).asInstanceOf[Product]
    }

}
