package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.rddBuilder
import com.libin.etl.loader.utils.loadUtils.stu
import org.apache.spark.rdd.RDD

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

class convertJobScheduler extends SparkJobBase {
  override def appName = "convertJobScheduler"

  val sc = createSparkContextLocal()
  val ss = createSparkSessionLocal()
}

object convertJobScheduler {

  def apply() = new convertJobScheduler

  def main(args: Array[String]) {
    val convertScheduler: convertJobScheduler = apply()
    convertScheduler.logger.info("convertJobScheduler start ...")

    // 创建RDD
    val rdd: RDD[stu] = rddBuilder.createRdd(convertScheduler.sc)

    import convertScheduler.ss.implicits._
    println("rdd.toDF().show() ...")
    rdd.toDF().show()
    println("rdd.toDS().show() ...")
    rdd.toDS().show()
  }
}
