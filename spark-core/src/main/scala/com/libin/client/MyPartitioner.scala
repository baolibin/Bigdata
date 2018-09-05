package com.libin.client

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * Copyright (c) 2015/05/02. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose : 自定义分区小Demo
  */
object MyPartitioner {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MyPartitioner").setMaster("local[10]")
    val sc = new SparkContext(conf)
    val arr = Array((2, 3), (4, 6), (4, 2), (2, 1), (22, 3), (34, 6),
      (74, 2), (12, 1), (62, 3), (34, 6), (114, 2), (92, 1))
    val rdd = sc.makeRDD(arr)
    rdd.partitionBy(new myPartitioner(10))
      .foreachPartition(x => println(x.toList.mkString(",")))
  }
}

/**
  * 不使用已有的分区策略HashPartitioner和RangePartitioner,自定义分区
  *
  * @param partitions 分区个数
  */
class myPartitioner(partitions: Int) extends Partitioner {
  override def numPartitions: Int = partitions

  override def getPartition(key: Any): Int = key match {
    case null => 0
    case _ =>
      try {
        val curNum = key.asInstanceOf[Int]
        if (curNum < 10) curNum
        else if (curNum < 100) curNum / numPartitions
        else 0
      } catch {
        case e: Exception => 0
      }
  }
}