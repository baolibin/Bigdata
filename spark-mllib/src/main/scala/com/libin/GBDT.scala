package com.libin

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object GBDT {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("GBDT")
    val sc = new SparkContext(conf)

    /**
      * GBDT用于分类
      */
    val data = MLUtils.loadLibSVMFile(sc, "D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt")
    val splits = data.randomSplit(Array(0.7, 0.3))
    val (trainingData, testData) = (splits(0), splits(1))

    val boostingStrategy = BoostingStrategy.defaultParams("Classification")
    boostingStrategy.setNumIterations(3)
    boostingStrategy.treeStrategy.setNumClasses(2)
    boostingStrategy.treeStrategy.setMaxDepth(5)
    val model = GradientBoostedTrees.train(trainingData, boostingStrategy)

    val labelAndPreds = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }

    val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
    println("Test Error = " + testErr)
    println("Learned classification GBT model:\n" + model.toDebugString)
    println("data.count:" + data.count())
    println("trainingData.count:" + trainingData.count())
    println("testData.count:" + testData.count())
    println("model.algo:" + model.algo)
    println("model.trees:" + model.trees)
    println("model.treeWeights:" + model.treeWeights)
    println("labelAndPreds")
    labelAndPreds.take(10).foreach(println)

    /**
      * GBDT用于回归
      */
    val data_path1 = "D://sparkmllibData/sparkml/mllibdata/lpsa.data"
    val data2 = sc.textFile(data_path1)
    val inputdata = data2.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(" ").map(_.toDouble)))
    }.cache()
    val boostingStrategy2 = BoostingStrategy.defaultParams("Regression")
    boostingStrategy2.setNumIterations(3)
    boostingStrategy2.treeStrategy.setMaxDepth(5)

    val model2 = GradientBoostedTrees.train(inputdata, boostingStrategy2)
    val labelsAndPredictions2 = testData.map { point =>
      val prediction2 = model2.predict(point.features)
      (point.label, prediction2)
    }
    val testMSE = labelsAndPredictions2.map { case (v, p) => math.pow((v - p), 2) }.mean()
    println("Test2 Mean Squared Error = " + testMSE)
    println("Learned regression GBT model2:\n" + model2.toDebugString)
    println("model2.algo:" + model2.algo)
  }
}
