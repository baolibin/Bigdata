package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering._
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object KMeans {
  def main(args: Array[String]) {
    //1 构建Spark对象
    val conf = new SparkConf()
      .setAppName("KMeans")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    // 读取样本数据1，格式为LIBSVM format
    val data = sc.textFile("E://sparkmllibData/kMeans_demo/testSet.txt")
    //val data = sc.textFile("D://sparkmllibData/sparkml/mllibdata/kmeans_data.txt")
    val parsedData = data.map(s => Vectors.dense(s.split('\t').map(_.toDouble))).cache()

    // 新建KMeans聚类模型，并训练
    val initMode = "k-means"
    //val initMode = "k-means++"
    //val initMode = "k-means||"
    val numClusters = 5
    val numIterations = 100

    val model = new KMeans()
      .setInitializationMode(initMode)
      .setK(numClusters)
      .setMaxIterations(numIterations)
      .run(parsedData)
    val centers = model.clusterCenters
    println("centers")
    for (i <- 0 to centers.length - 1) {
      println(centers(i)(0) + "\t" + centers(i)(1))
    }
    // 误差计算
    val WSSSE = model.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + WSSSE)

    //保存模型
    /*val ModelPath = "D://sparkmllibData/sparkml/mllibdata/KMeans_Model"
    model.save(sc, ModelPath)
    val sameModel = KMeansModel.load(sc, ModelPath)*/

  }
}
