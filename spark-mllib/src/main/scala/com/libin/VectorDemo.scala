package com.libin

import org.apache.spark.mllib.linalg.{Matrices, Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object VectorDemo {
  def main(args: Array[String]) {
    //建立密集向量
    val vd: Vector = Vectors.dense(9, 5, 2, 7)
    val pos = LabeledPoint(1, vd)
    println(pos.features)
    println(pos.label)
    //println(vd(2))
    //建立稀疏向量
    val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(9, 5, 2, 7))
    val neg = LabeledPoint(0, vs)
    println(neg.features)
    println(neg.label)
    //println(vs(2))

    /*val conf: SparkConf = new SparkConf()
      .setAppName("vector")
      .setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    val mu = MLUtils.loadLibSVMFile(sc,"D://sparkmllibData/sparkml/mllibdata/vectors.txt")
    mu.foreach(println)*/
    //本地矩阵
    val mx = Matrices.dense(2, 3, Array(1, 2, 3, 4, 5, 6))
    println(mx)
  }
}
