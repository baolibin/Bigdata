package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.DfBuilder
import com.libin.etl.utils.LogUtils
import com.libin.utils.FileUtils
import org.apache.spark.sql.DataFrame

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

class DfJobScheduler extends SparkJobBase {
    override def appName = "dfJobScheduler"

    val ss = createSparkSessionLocal()
}

object DfJobScheduler {
    def apply() = new DfJobScheduler

    def main(args: Array[String]) {
        val dfScheduler: DfJobScheduler = apply()
        LogUtils.setSparkLogLevels()
        dfScheduler.logger.info("dfJobScheduler start ...")
        // 测试读取json配置数据
        // loadUtils.readResourceFile("stu.json").foreach(println)
        // 读取df操作
        val df: DataFrame = DfBuilder.readJsonToDf(dfScheduler.ss, FileUtils.STU_File)

        /**
         * DataFrame基本操作
         */
        // op1.显示数据
        println("df.show() ...")
        df.show()
        // op2.输出结构信息schema
        println("df.printSchema() ...")
        df.printSchema()
        // op3.查询字段
        println("df.select(\"name\").show() ...")
        df.select("name").show()
        // op4.身高高于150，按照年龄倒叙排序输出
        println("df.select($\"name\", $\"age\", $\"height\").where($\"height\" > 150).orderBy(df(\"age\").desc).show() ...")
        import dfScheduler.ss.implicits._
        df.select($"name", $"age", $"height").where($"height" > 150).orderBy(df("age").desc).show()
        // op5.使用groupBy
        println("df.groupBy(\"age\").max(\"height\").show()")
        df.groupBy("age").max("height").show()

        /**
         * 执行sql语句
         */
        df.createOrReplaceTempView("stu")
        println("df.sqlContext.sql(\"select * from stu\")")
        df.sqlContext.sql("select * from stu").show()
        println("df.sqlContext.sql(\"select name,age,height from stu\")")
        df.sqlContext.sql("select name,age,height from stu").show()
    }
}
