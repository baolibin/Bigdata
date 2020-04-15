package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.{DfBuilder, RddBuilder}
import com.libin.etl.utils.LoadUtils.stu
import com.libin.utils.FileUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

class ConvertJobScheduler extends SparkJobBase {
    override def appName = "convertJobScheduler"

    val sc = createSparkContextLocal()
    val ss = createSparkSessionLocal()
}

object ConvertJobScheduler {

    def apply() = new ConvertJobScheduler

    def main(args: Array[String]) {
        val convertScheduler: ConvertJobScheduler = apply()
        convertScheduler.logger.info("convertJobScheduler start ...")

        // 创建RDD
        val rdd: RDD[stu] = RddBuilder.createRdd(convertScheduler.sc)

        import convertScheduler.ss.implicits._
        println("rdd.toDF().show() ...")
        rdd.toDF().show()
        println("rdd.toDS().show() ...")
        rdd.toDS().show()

        // --------------------------------------------------------------------------------
        // join操作
        val stuDf: DataFrame = DfBuilder.readJsonToDf(convertScheduler.ss, FileUtils.STU_File)
        val schoolDf: DataFrame = DfBuilder.readJsonToDf(convertScheduler.ss, FileUtils.SCHOOL_File)

        // stuDf.createOrReplaceTempView("stu_df")
        // schoolDf.createOrReplaceTempView("school_df")
        println("stuDf.join(schoolDf,\"name\").show() ...")
        stuDf.join(schoolDf, "name").show()

        // --------------------------------------------------------------------------------
        // 读取test,parquet格式
        println(s"dfBuilder.readTextToDf(convertScheduler.ss,${FileUtils.PEOPLE_File}).show() ...")
        DfBuilder.readTextToDf(convertScheduler.ss, FileUtils.PEOPLE_File).show()
        println(s"dfBuilder.readParquetToDf(convertScheduler.ss,${FileUtils.USERS_File}).show() ...")
        DfBuilder.readParquetToDf(convertScheduler.ss, FileUtils.USERS_File).show()
    }
}
