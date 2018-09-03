package com.libin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object Test {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("test")
    val sc: SparkContext = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(('a', 2), ('b', 4), ('c', 6), ('d', 9)))
    val rdd2 = sc.parallelize(List(('c', 6), ('c', 7), ('d', 8), ('e', 10)))
    val unionrdd = rdd1 union rdd2
    //rdd1.coalesce()
    unionrdd.foreach(println)

  }
}
