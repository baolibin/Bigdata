package com.libin

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed._
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :分布式行矩阵有：
  * * 行矩阵、带索引的行矩阵、坐标行矩阵、块行矩阵
  */
object DistributedMatrixRow {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("distributedMatrixRow")
    val sc = new SparkContext(conf)

    println("First:RowMatrix ")
    val rdd = sc.textFile("D://sparkmllibData/sparkml/mllibdata/MatrixRow.txt") //创建RDD文件路径
      .map(_.split(' ') //按“ ”分割
      .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转成Vector格式
    val rm = new RowMatrix(rdd) //读入行矩阵
    println(rm.numRows()) //打印列数
    println(rm.numCols()) //打印行数
    rm.rows.foreach(println)

    println("Second:IndexedRow ")
    val rdd2 = sc.textFile("D://sparkmllibData/sparkml/mllibdata/MatrixRow.txt") //创建RDD文件路径
      .map(_.split(' ') //按“ ”分割
      .map(_.toDouble)) //转成Double类型
      .map(line => Vectors.dense(line)) //转化成向量存储
      .map(vd => IndexedRow(vd.size, vd)) //转化格式
    val irm = new IndexedRowMatrix(rdd2) //建立索引行矩阵实例
    println(irm.getClass) //打印类型
    irm.rows.foreach(println) //打印内容数据

    println("Third: CoordinateMatrix ")
    val rdd3 = sc.textFile("D://sparkmllibData/sparkml/mllibdata/MatrixRow.txt") //创建RDD文件路径
      .map(_.split(' ') //按“ ”分割
      .map(_.toDouble)) //转成Double类型
      .map(vue => (vue(0).toLong, vue(1).toLong, vue(2))) //转化成坐标格式
      .map(vue2 => MatrixEntry(vue2 _1, vue2 _2, vue2 _3)) //转化成坐标矩阵格式
    val crm = new CoordinateMatrix(rdd3) //实例化坐标矩阵
    crm.entries.foreach(println) //打印数据
    println(crm.numCols())
    println(crm.numCols())
    println(crm.entries.countApproxDistinct())

  }
}
