package com.libin.etl.jobs

import com.libin.common.SparkJobBase
import com.libin.etl.loader.data.DsBuilder
import com.libin.etl.utils.LoadUtils.stu
import org.apache.spark.sql.Dataset

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

class DsJobScheduler extends SparkJobBase {
    override def appName = "dsJobScheduler"

    val ss = createSparkSessionLocal()
}

object DsJobScheduler {
    def apply() = new DsJobScheduler()

    def main(args: Array[String]) {
        val dsScheduler: DsJobScheduler = apply()
        dsScheduler.logger.info("dsJobScheduler start ...")

        val ds: Dataset[stu] = DsBuilder.createDsBySeq(dsScheduler.ss)

        ds.show()
    }
}
