package com.libin

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose : 降维SVD
  * 奇异值分解(SVD)：一个矩阵分解成带有方向向量的矩阵相乘
  */
object SVD {
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("SVD")
  val sc = new SparkContext(conf)

  def main(args: Array[String]) {
    val data = sc.textFile("D://sparkmllibData/sparkml/mllibdata/svd.txt")
      .map(_.split(" ").map(_.toDouble))
      .map(line => Vectors.dense(line))

    val rm = new RowMatrix(data) //读入行矩阵
    val SVD = rm.computeSVD(2, computeU = true) //进行SVD计算
    //求　SVD 分解的矩阵
    val u = SVD.U
    val s = SVD.s
    val v = SVD.V
    println("SVD.U")
    u.rows.foreach(println)
    println("SVD.s")
    println(s)
    println("SVD.V")
    println(v)
  }
}
