package com.libin.kaggle

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.{RandomForest, GradientBoostedTrees}
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.{SparkContext, SparkConf}

import scala.language.postfixOps

/**
  * Copyright (c) 2017/06/26. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object kaggle_digit_recognizer_rf {
  def main(args: Array[String]) {
    //构建Spark对象
    val conf = new SparkConf()
      .setAppName("kaggle_digit_recognizer_rf")
      .setMaster("local[4]")
      .set("spark.driver.memory", "7G")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //加载训练数据，一共42000个样本，28*28的图片
    val train_data = sc.textFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/train.csv")
      .filter(x => !x.split(",")(0).equals("label"))
      .map(line => {
        val spl = line.split(",")
        val label = spl(0).toInt
        val features = spl.slice(1, spl.size).map(line => line.toDouble)
        LabeledPoint(label, Vectors.dense(features))
      })
    //.foreach(println)
    val splits = train_data.randomSplit(Array(0.8, 0.2), seed = 11L)
    val training = splits(0) //训练集
    val test = splits(1) //验证集
    //training.foreach(println)

    val numClasses = 10 //分类数量
    val categoricalFeaturesInfo = Map[Int, Int]() //设定输入格式
    val numTrees = 200 // 随机森林中决策树的数目
    val featureSubSetStrategy = "auto" //设置属性在节点计算数,自动决定每个节点的属性数 Supported: "auto", "all", "sqrt", "log2", "onethird".
    val impurity = "gini" //设定信息增益计算方式 Supported values: "gini" (recommended) or "entropy".
    val maxDepth = 12 //最大深度
    val maxBins = 3 // 设定分割数据集

    /**
      * 建立模型 分类
      */
    val model = RandomForest.trainClassifier(training, numClasses, categoricalFeaturesInfo, numTrees,
      featureSubSetStrategy, impurity, maxDepth, maxBins
    )
    model.trees.foreach(println) //打印每棵树信息
    println("model.numTrees:" + model.numTrees)
    println("model.algo:" + model.algo)
    println("model.trees:" + model.trees)

    //对测试样本进行测试
    val predictionAndLabels = test.map {
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label)
    }
    val metrics = new MulticlassMetrics(predictionAndLabels)
    val precision = metrics.precision
    val recall = metrics.recall
    println("精确率：" + precision)
    println("召回率：" + recall)

    //读取测试数据
    val test_data = sc.textFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/test.csv")
      .filter(x => !x.split(",")(0).equals("pixel0"))
      .map(line => {
        line.split(",")
      })
    val features = test_data.map(line => {
      val f = line.map(p => p.toDouble)
      Vectors.dense(f)
    })
    //测试结果写入本地
    val predictions = model.predict(features).map(p => p.toInt)
    predictions.repartition(1).saveAsTextFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/prediction_rf")
  }
}
