package com.libin.graphX.etl

import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}
import org.slf4j.{LoggerFactory, Logger}

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object GparhXShortLength {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("GparhXShortLength").setMaster("local")
    val sc = new SparkContext(conf)

    val logger: Logger = LoggerFactory.getLogger("GparhXShortLength")
    logger.info("GparhXShortLength start ...")

    // 顶点的数据类型是VD:(String,Int)
    val vertexRDD: RDD[(Long, (String, Int))] =
      sc.parallelize(Array(
        (1L, ("Alice", 28)),
        (2L, ("Bob", 27)),
        (3L, ("Charile", 65)),
        (4L, ("David", 42)),
        (5L, ("Eirm", 55)),
        (6L, ("Frank", 50)))
      )
    // 边的数据类型是ED:Int
    val edgeRDD: RDD[Edge[Int]] =
      sc.parallelize(Array(
        Edge(2L, 1L, 7),
        Edge(2L, 4L, 2),
        Edge(3L, 2L, 4),
        Edge(3L, 6L, 3),
        Edge(4L, 1L, 1),
        Edge(5L, 2L, 2),
        Edge(5L, 3L, 8),
        Edge(5L, 6L, 3))
      )

    // 构造图
    val graph: Graph[(String, Int), Int] = Graph(vertexRDD, edgeRDD)
    println("找出5到各个顶点的最短距离")
    val sourceId: VertexId = 5L
    // 源顶点赋值为0,其他顶点赋值为无穷大.
    val initialGraph = graph.mapVertices((id, _) => if (id == sourceId) 0.0 else Double.PositiveInfinity)

    // 开始调用pregel开始计算
    //val sssp = initialGraph.pregel(Double.PositiveInfinity)(
    val sssp = Pregel(initialGraph, Double.PositiveInfinity, Int.MaxValue, EdgeDirection.Either)(
      (id, dist, newDist) => math.min(dist, newDist), //vprog,作用是处理到达顶点的参数,取较小的那个作为顶点的值
      triplet => { //sendMsg,计算权重,如果邻居节点的属性加上边上的距离小于该节点的属性,说明从源节点经过邻居节点到该顶点的距离更小,更新值.
        if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
          Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
        } else {
          Iterator.empty
        }
      },
      (a, b) => math.min(a, b) //mergeMsg,合并到达顶点的所有信息.
    )
    println(sssp.vertices.collect.mkString("\n"))

    /**
      * 运行结果
      * (4,4.0)
      * (1,5.0)
      * (6,3.0)
      * (3,8.0)
      * (5,0.0)
      * (2,2.0)
      */
  }
}
