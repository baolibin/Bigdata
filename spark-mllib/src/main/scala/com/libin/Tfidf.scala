package com.libin

import org.apache.spark.mllib.feature.{IDFModel, IDF, HashingTF}
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object Tfidf {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf()
      .setMaster("local")
      .setAppName("tf_idf")
    val sc: SparkContext = new SparkContext(conf)
    //读取数据
    val document = sc.textFile("D://sparkmllibData/sparkml/mllibdata/tf_idf.txt").map(_.split("\t").toSeq)
    //创建TF计算实例
    val hashingTF = new HashingTF()
    //计算文档TF值
    val tf: RDD[Vector] = hashingTF.transform(document).cache()
    tf.foreach(println)
    //创建IDF实例并计算
    val idf: IDFModel = new IDF().fit(tf)
    println(idf)
    //计算TF_IDF词频
    val tf_idf: RDD[Vector] = idf.transform(tf)
    tf_idf.foreach(println)
  }
}
