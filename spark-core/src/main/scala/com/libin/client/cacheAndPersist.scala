package com.libin.client

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2016/11/02. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object cacheAndPersist {
  def main(args: Array[String]): Unit = {
    /*if (args.length != 1) {
      System.err.println("Usage <Input>")
      System.exit(1)
      val Array(input) = args
    }*/

    val input = "E://sparkmllibData/cache.txt"
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("cacheAndPersist")
    val sc: SparkContext = new SparkContext(conf)

    val data1 = sc.textFile(input)
      .map(_.split("\\|", 100))
      .map(line => {
        val Array(name, age) = line
        (name, age)
      }).cache()
    val data2 = sc.textFile(input)
      .map(line => {
        line.split("\\|", 100)
      }).map(x => {
        val Array(name, age) = x
        (name, age)
      }).filter(y => {y._1.equals("ML")
    }).persist(StorageLevel.MEMORY_AND_DISK)

    data1.intersection(data2).foreach(println)
  }

}
