package com.libin.graphX.etl

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Graph, Edge, VertexId}
import org.apache.spark.rdd.RDD
import org.slf4j.{LoggerFactory, Logger}

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object GraphXProcessor {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("joinVertexDemo").setMaster("local")
    val sc = new SparkContext(conf)

    val logger: Logger = LoggerFactory.getLogger("GraphProcessor")
    logger.info("GraphProcessor start ...")

    val vertexRdd: RDD[(VertexId, (String, String, Long))] =
      sc.parallelize(Array(
        (1L, ("mid_1", "mid", 1513048521000L)),
        (2L, ("imei_1", "phone", 1523048521003L)),
        (3L, ("pn_1", "pn", 1523048521005L))
      ))

    val edgeRdd: RDD[Edge[Long]] =
      sc.parallelize(Array(
        Edge(1L, 2L, 1513048521000L),
        Edge(2L, 3L, 1523048521003L)
      ))

    // 构造图
    val graphTest = Graph(vertexRdd, edgeRdd)
    // 输出图的顶点信息
    graphTest.vertices.foreach(println)

    val addAttrRdd = sc.makeRDD(Array((1L, 1L), (3L, 3L), (5L, 5L)))

    graphTest.mapVertices((_, attr) => attr._3).joinVertices(addAttrRdd)((_, _, newAttr) => newAttr)
      .vertices.foreach(println)
    /**
      * 操作joinVertices输出结果.
      * (1,1)
      * (3,3)
      * (2,1523048521003)
      */

    graphTest.mapVertices((_, attr) => attr._3).outerJoinVertices(addAttrRdd)((_, _, newAttr) => newAttr)
      .vertices.foreach(println)
    /**
      * 操作outerJoinVertices输出结果.
      * (1,Some(1))
      * (3,Some(3))
      * (2,None)
      */
  }
}
