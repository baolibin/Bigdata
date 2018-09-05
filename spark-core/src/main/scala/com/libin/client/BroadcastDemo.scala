package com.libin.client

import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.immutable.HashMap

/**
  * Copyright (c) 2016/11/02. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object BroadcastDemo {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("CacheRadius").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val input = "E://sparkmllibData/cacheAndPersist.txt"
    val data = sc.textFile(input).map(_.split("\\|", 100)).map(line => {
      val Array(privateIP, account, timeFormat, timeType) = line
      (privateIP, (account, timeFormat.toLong, timeType.toInt))
    })

    var accountHash = new HashMap[String, Set[(String, Long, Int)]]()
    data.groupByKey().collect().foreach(x => {
      accountHash += (x._1 -> x._2.toSet)
    })
    val broacast = sc.broadcast(accountHash)

    println(broacast.id)
    val hashvalue = broacast.value
    for (entry <- hashvalue) {
      println(entry._1 + "|" + entry._2)
    }
  }
}
