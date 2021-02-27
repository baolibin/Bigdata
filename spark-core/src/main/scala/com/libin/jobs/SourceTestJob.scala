package com.libin.jobs

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2021/2/27. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 用户提交作业流程示例代码。
  */
object SourceTestJob {
    def main(args: Array[String]): Unit = {
        // Create a SparkConf that loads defaults from system properties and the classpath
        val conf = new SparkConf()

        // Create a SparkContext that loads settings from system properties
        // (for instance, when launching with ./bin/spark-submit).
        val sc = new SparkContext(conf)

        // create a ParallelCollectionRDD
        val rdd: RDD[String] = sc.makeRDD(Array("hadoop", "spark"))

        // sc.runJob(this, Utils.getIteratorSize _).sum
        rdd.count()
    }
}

