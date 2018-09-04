package com.libin

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object PCADemo {
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("PCA")
  val sc = new SparkContext(conf)

  def main(args: Array[String]) {
    val data = sc.textFile("D://sparkmllibData/sparkml/mllibdata/svd.txt")
      .map(_.split(" ").map(_.toDouble))
      .map(line => Vectors.dense(line))

    val rm = new RowMatrix(data)
    val pc = rm.computePrincipalComponents(3)
    //提取主成分，设置主成分个数为３
    val mx = rm.multiply(pc) //创建主成分矩阵

    mx.rows.foreach(println)
  }
}
