package com.libin

import org.apache.spark.mllib.classification.LogisticRegressionModel
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.optimization.{LBFGS, LogisticGradient, SquaredL2Updater}
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object LBFGSExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("LBFGSExample").setMaster("local")
    val sc = new SparkContext(conf)
    val data = MLUtils.loadLibSVMFile(sc, "D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt")
    val numFeatures = data.take(1)(0).features.size

    val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)

    // Append 1 into the training data as intercept.
    val training = splits(0).map(x => (x.label, MLUtils.appendBias(x.features))).cache()

    val test = splits(1)

    // Run training algorithm to build the model
    val numCorrections = 10
    val convergenceTol = 1e-4
    val maxNumIterations = 20
    val regParam = 0.1
    val initialWeightsWithIntercept = Vectors.dense(new Array[Double](numFeatures + 1))

    val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
      training,
      new LogisticGradient(),
      new SquaredL2Updater(),
      numCorrections,
      convergenceTol,
      maxNumIterations,
      regParam,
      initialWeightsWithIntercept)

    val model = new LogisticRegressionModel(
      Vectors.dense(weightsWithIntercept.toArray.slice(0, weightsWithIntercept.size - 1)),
      weightsWithIntercept(weightsWithIntercept.size - 1))
    // Clear the default threshold.
    model.clearThreshold()
    // Compute raw scores on the test set.
    val scoreAndLabels = test.map { point =>
      val score = model.predict(point.features)
      (score, point.label)
    }
    // Get evaluation metrics.
    val metrics = new BinaryClassificationMetrics(scoreAndLabels)
    val auROC = metrics.areaUnderROC()

    println("Loss of each step in training process")
    loss.foreach(println)
    println("Area under ROC = " + auROC)
  }
}
