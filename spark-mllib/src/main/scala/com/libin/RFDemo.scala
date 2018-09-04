package com.libin

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.{ml, SparkConf, SparkContext}
import org.apache.spark.mllib.util.MLUtils

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose : 随机森林
  */
object RFDemo {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("RF")
    val sc = new SparkContext(conf)

    val data = MLUtils.loadLibSVMFile(sc, "D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt")

    val numClasses = 2 //分类数量
    val categoricalFeaturesInfo = Map[Int, Int]()
    //设定输入格式
    val numTrees = 3 // 随机森林中决策树的数目
    val featureSubSetStrategy = "auto" //设置属性在节点计算数,自动决定每个节点的属性数 Supported: "auto", "all", "sqrt", "log2", "onethird".
    val impurity = "gini" //设定信息增益计算方式 Supported values: "gini" (recommended) or "entropy".
    val maxDepth = 5 //最大深度
    val maxBins = 3 // 设定分割数据集

    /**
      * 建立模型 分类
      */
    val model = RandomForest.trainClassifier(data, numClasses, categoricalFeaturesInfo, numTrees,
      featureSubSetStrategy, impurity, maxDepth, maxBins
    )
    model.trees.foreach(println) //打印每棵树信息
    println(model.numTrees)
    println(model.algo)

    /**
      * 建立模型 回归
      */
    val data_path1 = "D://sparkmllibData/sparkml/mllibdata/lpsa.data"
    val data2 = sc.textFile(data_path1)
    val inputdata = data2.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(" ").map(_.toDouble)))
    }.cache()

    val impurity2 = "variance"
    val seed = 11
    val model2 = RandomForest.trainRegressor(inputdata, categoricalFeaturesInfo, numTrees,
      featureSubSetStrategy, impurity2, maxDepth, maxBins, seed)
    model2.trees.foreach(println) //打印每棵树信息
    println(model2.numTrees)
    println(model2.algo)
  }
}
