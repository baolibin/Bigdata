package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.linalg.{Matrices, Vectors}
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.mllib.util.KMeansDataGenerator
import org.apache.spark.mllib.util.LinearDataGenerator
import org.apache.spark.mllib.util.LogisticRegressionDataGenerator
import org.apache.spark.mllib.random.RandomRDDs._

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object StatisticsDemo {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("rdd_test02")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    // 2.2节
    // 2.2.1 列统计汇总
    val data_path = "D://sparkmllibData/sparkml/mllibdata/sample_stat.txt"
    val data = sc.textFile(data_path).map(_.split("\t")).map(f => f.map(f => f.toDouble))
    val data1 = data.map(f => Vectors.dense(f))
    val stat1 = Statistics.colStats(data1)
    println("max=" + stat1.max) //最大数值单位
    println("min=" + stat1.min) //最小数值
    println("mean=" + stat1.mean) //平均值
    println("variance=" + stat1.variance) //方差
    println("normL1=" + stat1.normL1) //L1范数
    println("normL2=" + stat1.normL2)
    println("normL2=" + stat1.numNonzeros) //不包含0的个数


    // 2.2.2 相关系数
    val corr1 = Statistics.corr(data1, "pearson")
    val corr2 = Statistics.corr(data1, "spearman")
    val x1 = sc.parallelize(Array(1.0, 2.0, 3.0, 4.0))
    val y1 = sc.parallelize(Array(5.0, 6.0, 6.0, 6.0))
    val corr3 = Statistics.corr(x1, y1, "pearson")
    val corr4 = Statistics.corr(x1, y1, "spearman")
    println("corr1=" + corr1)
    println("corr2=" + corr2)
    println("corr3=" + corr3)
    println("corr4=" + corr4)

    //分层抽样
    val fractions: Map[Int, Double] = (List((1, 0.2), (2, 0.8))).toMap
    val dataSample = sc.textFile("D://sparkmllibData/sparkml/mllibdata/sample.txt")
      .map(row => {
        if (row.length == 3)
          (row, 1)
        else (row, 2)
      }).map(line => (line._2, line._1))
    /* fractions表示在层1抽0.2，在层2中抽0.8
       withReplacement false表示不重复抽样
       0表示随机的seed*/
    val approxSample = dataSample.sampleByKey(withReplacement = false, fractions, 0)
    approxSample.foreach(println)


    //2.2.3 卡方检验
    val v1 = Vectors.dense(43.0, 9.0)
    val v2 = Vectors.dense(44.0, 4.0)
    val c1 = Statistics.chiSqTest(v1, v2)
    println("c1=" + c1)
    /* mtx作为单向量，调用MLlib卡方检验对其进行处理，
       其后建立一个矩阵数据组对其进行处理*/
    val mtx = Matrices.dense(3, 2, Array(1, 3, 5, 2, 4, 6))
    val mtxResult = Statistics.chiSqTest(mtx)
    println("mtxResult=" + mtxResult)

    /**
      * 随机数
      */
    println("normalRDD:")
    val randomNum = normalRDD(sc, 3)
    randomNum.foreach(println)
    println("uniformRDD:")
    uniformRDD(sc, 3).foreach(println)
    println("poissonRDD:")
    poissonRDD(sc, 5, 10).foreach(println)
    println("exponentialRDD:")
    exponentialRDD(sc, 7, 10).foreach(println)
    println("gammaRDD:")
    gammaRDD(sc, 3, 3, 10).foreach(println)

    // 2.3节
    // 2.3.2 生成样本
    val KMeansRDD = KMeansDataGenerator.generateKMeansRDD(sc, 40, 5, 3, 1.0, 2)
    KMeansRDD.count()
    println("KMeansRDD=")
    KMeansRDD.take(5).flatMap(line => (line)).foreach(println)

    val LinearRDD = LinearDataGenerator.generateLinearRDD(sc, 40, 3, 1.0, 2, 0.0)
    LinearRDD.count()
    println("LinearRDD=")
    LinearRDD.take(5).foreach(println)

    val LogisticRDD = LogisticRegressionDataGenerator.generateLogisticRDD(sc, 40, 3, 1.0, 2, 0.5)
    LogisticRDD.count()
    println("LogisticRDD=")
    LogisticRDD.take(5).foreach(println)

  }
}
