package com.libin.scala

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object AggredateTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("aggredate_test")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(List((1, 3), (1, 2), (1, 4), (2, 3)))
    data.aggregateByKey(5)(seq, comb).collect.foreach(println)
  }

  def seq(a: Int, b: Int): Int = {
    println("seq: " + a + "\t " + b)
    math.max(a, b)
  }

  def comb(a: Int, b: Int): Int = {
    println("comb: " + a + "\t " + b)
    a + b
  }
}
