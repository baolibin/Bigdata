package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.dsBuilder
import com.libin.etl.loader.utils.loadUtils.stu
import org.apache.spark.sql.Dataset

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

class dsJobScheduler extends SparkJobBase {
  override def appName = "dsJobScheduler"

  val ss = createSparkSessionLocal()
}

object dsJobScheduler {
  def apply() = new dsJobScheduler()

  def main(args: Array[String]) {
    val dsScheduler: dsJobScheduler = apply()
    dsScheduler.logger.info("dsJobScheduler start ...")

    val ds: Dataset[stu] = dsBuilder.createDsBySeq(dsScheduler.ss)

    ds.show()
  }
}
