package com.libin.client

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2018/05/02. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object SecondarySort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("secondarySort").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val arr = Array((2, 3), (4, 6), (4, 2), (2, 1))
    val rdd = sc.makeRDD(arr)
    rdd.map(x => (new secondarySortUtils(x._1, x._2), x))
      .sortByKey(ascending = true).map(_._2)
      .collect().foreach(println)
  }
}

/**
  * 继承Ordered和Serializable实现自定义排序key,并使用sortByKey对自定义的key进行排序.
  *
  * @param first  第一列数据
  * @param second 第二列数据
  */
class secondarySortUtils(val first: Int, val second: Int) extends Ordered[secondarySortUtils] with Serializable {
  override def compare(that: secondarySortUtils): Int = {
    if (this.first - that.first != 0) this.first - that.first
    else this.second - that.second
  }
}