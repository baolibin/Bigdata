package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.fpm.{FPGrowth, FPGrowthModel}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object FPGrowthDemo {
  def main(args: Array[String]) {
    //0 构建Spark对象
    val conf = new SparkConf()
      .setAppName("fpg")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //1 读取样本数据
    val data_path = "D://sparkmllibData/sparkml/mllibdata/sample_fpgrowth.txt"
    val data = sc.textFile(data_path)
    val examples = data.map(_.split(" ")).cache()

    //2 建立模型
    val minSupport = 0.6
    val numPartition = 10
    val model = new FPGrowth()
      .setMinSupport(minSupport)
      .setNumPartitions(numPartition)
      .run(examples)

    //3 打印结果
    println("Number of frequent itemsets:" + model.freqItemsets.count())
    model.freqItemsets.collect().foreach { itemset =>
      println(itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
    }
  }
}
