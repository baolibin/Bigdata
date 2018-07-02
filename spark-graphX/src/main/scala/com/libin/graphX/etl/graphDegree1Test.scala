package com.libin.graphX.etl

import org.apache.spark.graphx.{TripletFields, Graph, Edge, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.mutable

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object graphDegree1Test {
  type vertexType = (String, scala.collection.mutable.HashMap[String, Array[Long]])

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("graphTest").setMaster("local")
    val sc = new SparkContext(conf)
    // 构造测试点数据
    val vertex: RDD[(VertexId, (String, String, Long))] =
      sc.parallelize(Array(
        (1L, ("mid_1", "mid", 1523048521000L)),
        (2L, ("mid_2", "mid", 1523048521001L)),
        (3L, ("mid_3", "mid", 1523048521002L)),

        (4L, ("imei_1", "phone", 1523048521003L)),
        (5L, ("imei_2", "phone", 1523048521004L)),

        (6L, ("pn_1", "pn", 1523048521005L)),
        (7L, ("pn_2", "pn", 1523048521006L)),
        (8L, ("pn_3", "pn", 1523048521007L)),

        (9L, ("tv_1", "tv", 1523048521008L))
      ))
    // 构造测试边数据
    val edge: RDD[Edge[Long]] =
      sc.parallelize(Array(
        Edge(1L, 4L, 1523048521003L),
        Edge(1L, 6L, 1523048521005L),
        Edge(1L, 7L, 1523048521005L),

        Edge(2L, 4L, 1523048521003L),
        Edge(2L, 5L, 1523048521004L),
        Edge(2L, 8L, 1523048521007L),
        Edge(2L, 9L, 1523048521008L),

        Edge(3L, 5L, 1523048521004L)
      ))

    val vertex2: RDD[(VertexId, (String, String, Long))] =
      sc.parallelize(Array(
        (1L, ("mid_1", "mid", 1513048521000L)),
        (2L, ("imei_1", "phone", 1523048521003L)),
        (3L, ("pn_1", "pn", 1523048521005L))
      ))

    val edge2: RDD[Edge[Long]] =
      sc.parallelize(Array(
        Edge(1L, 2L, 1513048521000L),
        Edge(2L, 3L, 1523048521003L)
      ))

    // 构造图
    val graphTest = Graph(vertex2, edge2)
    // 输出图的顶点信息
    graphTest.vertices.foreach(println)
    // 输出图的边信息
    graphTest.edges.foreach(println)
    // 图聚合信息
    graphTest.aggregateMessages[vertexType](
      x => {
        val srcId = x.srcAttr._1
        val srcType = x.srcAttr._2
        val distId = x.dstAttr._1
        val distType = x.dstAttr._2
        x.sendToSrc((srcId, mutable.HashMap(distType -> Array(x.attr))))
        x.sendToDst((distId, mutable.HashMap(srcType -> Array(x.attr))))
      },
      mergeMsg,
      TripletFields.All)
      .foreach {
        case (idLong, (id, typeArrTime)) =>
          print(idLong, id)
          for ((idType, arrTime) <- typeArrTime) {
            print(idType, arrTime.mkString("|"))
          }
          println("\t\t")
          println()
      }
  }

  // 聚合相邻点信息
  def mergeMsg(a: vertexType, b: vertexType): vertexType = {
    require(a._1 == b._1)
    val a_map = a._2
    val b_map = b._2
    a_map.foreach {
      case (idType, vextexId) =>
        val b_arr = b_map.getOrElse(idType, Array[Long]())
        b_map += idType -> (vextexId ++ b_arr).sorted.reverse
    }
    b
  }
}
