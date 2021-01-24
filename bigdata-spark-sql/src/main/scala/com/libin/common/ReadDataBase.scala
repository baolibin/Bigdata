package com.libin.common

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Copyright (c) 2021/1/24. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 
  */
trait ReadDataBase {
    /**
      * 读取csv文件格式数据
      *
      * @param ss        SparkSession
      * @param paths     文件路径集合
      * @param columnMap 字段别名
      */
    def loadCsvFileWithSparkSql(ss: SparkSession, paths: Seq[String], columnMap: Map[String, String]): DataFrame = {
        var frame: DataFrame = ss.read.csv(paths: _*)
        frame.columns.foreach(key => frame = frame.withColumnRenamed(key, columnMap(key)))
        frame
    }

    /**
      * 读取指定字段的Parquet数据
      *
      * @param ss      SparkSession
      * @param paths   文件路径集合
      * @param columns 读取列数据
      */
    def loadParquetWithSparkSql(ss: SparkSession, paths: Seq[String], columns: String): DataFrame = {
        val frame: DataFrame = ss.read.parquet(paths: _*)
        frame.createOrReplaceTempView("frameTable")
        ss.sql(s"select ${columns} from frameTable").toDF()
    }
}
