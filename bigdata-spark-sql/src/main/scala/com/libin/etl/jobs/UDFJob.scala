package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.DfBuilderLoader
import com.libin.utils.FileUtils
import org.apache.spark.sql.DataFrame

/**
  * Copyright (c) 2021/6/15. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 
  */
object UDFJob extends SparkJobBase {
    def main(args: Array[String]): Unit = {

        val ss = createSparkSessionLocal()
        val df: DataFrame = DfBuilderLoader.readJsonToDf(ss, FileUtils.STU_File)

        // 注册自定义函数
        ss.udf.register("add_one", add_one _)
        // 使用自定义函数
        import org.apache.spark.sql.functions
        val frame = df.withColumn("age2", functions.callUDF("add_one", functions.col("age")))
        println(frame.show())
        println(frame.schema)
    }

    // 定义自定义函数
    def add_one(col: Double) = {
        col + 1
    }
}
