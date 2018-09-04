package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import breeze.linalg._
import breeze.numerics._
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object RowmatriTest01 {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("rowmatri_test01").setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    // 3.6 分布式矩阵
    // 3.6.2 行矩阵（RowMatrix）
    val rdd1 = sc.parallelize(Array(Array(1.0, 2.0, 3.0, 4.0), Array(2.0, 3.0, 4.0, 5.0), Array(3.0, 4.0, 5.0, 6.0))).map(f => Vectors.dense(f))
    val RM = new RowMatrix(rdd1)
    val simic1 = RM.columnSimilarities(0.5)
    val simic2 = RM.columnSimilarities()
    val simic3 = RM.computeColumnSummaryStatistics()
    simic3.max
    simic3.min
    simic3.mean
    val cc1 = RM.computeCovariance
    val cc2 = RM.computeGramianMatrix
    val pc1 = RM.computePrincipalComponents(3)
    val svd = RM.computeSVD(4, true)
    val U = svd.U
    U.rows.foreach(println)
    val s = svd.s
    val V = svd.V
  }
}
