package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.dumper.{JsonDumper, ParquetDumper}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.joda.time.DateTime

/**
  * Copyright (c) 2021/1/24. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 
  */
object DumperJob extends SparkJobBase {
    val path = s"D:\\tmp\\sparksql_${new DateTime().getMillis}"

    def main(args: Array[String]): Unit = {
        // require(args.length >= 1)
        val action = "parquet" // args(0)
        // 生成测试数据
        val featureRdd: RDD[FeatureDataStruct] = generateTestData(createSparkSessionLocal())
        // 匹配执行
        action match {
            case "json" =>
                val jsonRdd: RDD[String] =
                    JsonDumper.processRDDJson(createSparkSessionLocal(), featureRdd, path, 1)
                writeTextDataBySparkCore(path, jsonRdd)
            case "parquet" =>
                val parquetDF: DataFrame =
                    ParquetDumper.processRDDParquet(createSparkSessionLocal(), featureRdd, path, 1)
                print(s"parquetDF.schema=${parquetDF.schema}")
                print(s"parquetDF.show=${parquetDF.show()}")
                writeParquetDataBySparkSql(path, parquetDF)
            case _ => logger.info("input action is error...")
        }
    }

    /**
      * 获得测试数据
      */
    def generateTestData(sparkSession: SparkSession): RDD[FeatureDataStruct] = {
        val struct1 = FeatureDataStruct("uid_1", 111, "key_111", "value_111")
        val struct2 = FeatureDataStruct("uid_2", 222, "key_222", "value_222")
        val struct3 = FeatureDataStruct("uid_1", 333, "key_333", "value_333")
        sparkSession.sparkContext.makeRDD(Array(struct1, struct3, struct2))
    }

    /**
      * 数据落盘字段格式 & 排序的特征Id
      */
    def getSortedColumns(featureRdd: RDD[FeatureDataStruct]): (String, List[Int]) = {
        val columnsList: List[(Int, String)] = featureRdd.map(x => (x.featureId, x.featureKey)).collect().toList
        // 数据落盘字段格式
        val columns: String = s"uid_id,${columnsList.sortBy(_._1).map(_._2).mkString(",")}"
        // 排序的特征Id
        val sortedFeatureId: List[Int] = columnsList.map(_._1).sorted
        (columns, sortedFeatureId)
    }

    /**
      * 上游数据格式
      */
    case class FeatureDataStruct(userId: String, featureId: Int, featureKey: String, featureValue: String)

}
