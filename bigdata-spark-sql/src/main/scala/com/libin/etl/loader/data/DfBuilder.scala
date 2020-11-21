package com.libin.etl.loader.data

import org.apache.spark.sql._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

object DfBuilder {
    /**
     * 读取配置文件中的数据创建DataFrame
     *
     * @param ss       SparkSession
     * @param fileName 文件名字
     * @return 返回DataFrame数据集
     */
    def readJsonToDf(ss: SparkSession, fileName: String): DataFrame = {
        val url: String = this.getClass.getClassLoader.getResource(fileName).toString
        ss.read.json(url)
    }

    def readTextToDf(ss: SparkSession, fileName: String): DataFrame = {
        val url: String = this.getClass.getClassLoader.getResource(fileName).toString
        ss.read.text(url)
    }

    def readParquetToDf(ss: SparkSession, fileName: String): DataFrame = {
        val url: String = this.getClass.getClassLoader.getResource(fileName).toString
        ss.read.parquet(url)
    }

    /**
     * 一次读取多个Parquet目录
     */
    def readMultiParquetFileToDf(ss: SparkSession, fileName: String*): Unit = {
        ss.read.parquet(fileName: _*)
    }

    /**
     * 使用Seq+toDf创建DataFrame
     *
     * @param ss SparkSession
     * @return 返回DataFrame数据集
     */
    def createDfBySeq(ss: SparkSession): DataFrame = {
        import ss.implicits._
        Seq(("xiaoming", 22, 175), ("xiaoli", 18, 161), ("xiaoqiang", 26, 198), ("xiaohong", 18, 158))
                .toDF("name", "age", "height")
    }

    /**
     * 使用createDataFrame创建DataFrame
     *
     * @param ss SparkSession
     * @return 返回DataFrame数据集
     */
    def createDf(ss: SparkSession): DataFrame = {
        val schema = StructType(List(
            StructField("name", StringType, nullable = false),
            StructField("age", IntegerType, nullable = false),
            StructField("height", IntegerType, nullable = false)
        ))
        val rdd = ss.sparkContext.parallelize(Seq(
            Row("xiaoming", 22, 175),
            Row("xiaoli", 18, 161),
            Row("xiaoqiang", 26, 198),
            Row("xiaohong", 18, 158)
        ))
        ss.sqlContext.createDataFrame(rdd, schema)
    }
}
