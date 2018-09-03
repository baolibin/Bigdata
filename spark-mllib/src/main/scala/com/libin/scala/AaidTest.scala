package com.libin.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.JavaConverters._

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object AaidTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("AaidTest").setMaster("local")
    val sc = new SparkContext(conf)

    sc.textFile("D://sparkmllibData/sparkml/mllibdata/arrregation.txt")
      .map(line => {
        (line.split("\t")(0), line.split("\t")(1).toLong)
      }).aggregateByKey(0L)(seqOp, seqOp)
      .filter(line => line._2 != 1L)
      .collect().foreach(println)

  }

  def seqOp(U: Long, v: Long): Long = {
    println("seqOp")
    println("U=" + U)
    println("v=" + v)
    var count: Int = 0
    if (U != 0L) {
      count += 1
    }
    if (v != 0L) {
      count += 1
    }
    if (count > 1) {
      1L
    } else {
      v
    }
  }
}
