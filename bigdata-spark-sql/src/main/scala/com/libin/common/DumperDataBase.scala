package com.libin.common

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame
import org.apache.thrift.TBase

import scala.reflect.ClassTag

/**
  * Copyright (c) 2021/1/24. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 落盘数据基础类
  */
trait DumperDataBase {
    /**
      * 数据Parquet落盘HDFS
      */
    def writeParquetDataBySparkCore[T <: TBase[_, _] : ClassTag](path: String, data: RDD[T]): Unit = {
        // data.saveAsParquetFile(path)
    }

    /**
      * 数据Text落盘HDFS
      */
    def writeTextDataBySparkCore(path: String, data: RDD[String]): Unit = {
        data.saveAsTextFile(path)
    }

    /**
      * 数据DataFrame落盘HDFS
      *
      * @param path 落盘地址
      * @param df   落盘Parquet数据DataFrame
      */
    def writeParquetDataBySparkSql(path: String, df: DataFrame): Unit = {
        df.write.parquet(path)
    }

}
