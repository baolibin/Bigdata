package com.libin.etl.dumper

import com.libin.common.SparkJobBase
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
        val spark = createSparkSessionLocal()
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
        println(dataFrame.show())

    }

    def toTupleStr(as: List[String]): Product = {
        val tupleClass = Class.forName("scala.Tuple" + as.size)
        tupleClass.getConstructors.apply(0).newInstance(as: _*).asInstanceOf[Product]
    }

}
