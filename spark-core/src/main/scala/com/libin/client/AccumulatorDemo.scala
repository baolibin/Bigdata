package com.libin.client

import org.apache.spark.{AccumulatorParam, SparkConf, SparkContext}

/**
  * Copyright (c) 2016/11/02. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object AccumulatorDemo {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("AccumulatorDemo")
    val sc: SparkContext = new SparkContext(conf)

    val arrAccu = Array(0L, 0L, 0L, 0L, 0L)
    val accumulatorArr = sc.accumulator(arrAccu, "HADOOP")(MyAcculumatorParam)

    val accumulatorMl = sc.accumulator(0, "ML")
    val accumulatorDl = sc.accumulator(0L, "DL")
    val arr = Array("ML", "DL", "CNN", "RNN", "ML", "HADOOP", "SPARK", "ML")
    for (i <- 0 to arr.length - 1) {
      if (arr(i).equals("ML")) {
        accumulatorMl += 1
      } else if (arr(i).equals("DL")) {
        accumulatorDl += 1
      } else if (arr(i).equals("HADOOP")) {
        accumulatorArr += Array(1L, 1L, 1L, 1L, 1L)
      }
    }
    println("ML=" + accumulatorMl.name.get + "、" + accumulatorMl.value)
    println("DL=" + accumulatorDl.name.get + "、" + accumulatorDl.value)
    println("HADOOP=" + accumulatorArr.name.get + "、" + accumulatorArr.value.mkString(","))
  }

  object MyAcculumatorParam extends AccumulatorParam[Array[Long]] {
    override def addInPlace(r1: Array[Long], r2: Array[Long]): Array[Long] = {
      r1.zip(r2).map(x => x._1 + x._2)
    }

    def zero(initialValue: Array[Long]): Array[Long] = {
      new Array[Long](initialValue.length)
    }
  }
}
