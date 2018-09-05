package com.libin.client

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.JavaConverters._

/**
  * Copyright (c) 2017/07/25. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose : 求key只出现一次的数据， 如果用groupByKey或reduceByKey很容易就做出来了，现在用aggregateByKey求解一下。
  */
object AggregateByKeyDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("aggregateByKeyDemo").setMaster("local")
    val sc = new SparkContext(conf)

    sc.textFile("D://sparkmllibData/sparkml/mllibdata/arrregation.txt")
      .map {
        line =>
          (line.split("\t")(0), line.split("\t")(1).toLong)
      }.aggregateByKey(0L)(seqOp, combOp)
      .filter(line => line._2 == 1L)
      .collect().foreach(println)
  }

  def seqOp(U: Long, V: Long): Long = {
    U + 1L
  }

  def combOp(U: Long, V: Long): Long = {
    U + V
  }
}

/**
  * asdfgh	546346
  * retr	4567
  * asdfgh	7685678
  * ghj	2345
  * asd	234
  * hadoop	435
  * ghj	23454
  * asdfgh	54675
  * asdfgh	546759878
  * asd	234
  * asdfgh	5467598782
  */
