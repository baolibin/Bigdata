package com.libin

import org.apache.log4j.{Level, Logger}
//import org.apache.spark.examples.mllib.BinaryClassification
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{LogisticRegressionWithSGD, LogisticRegressionWithLBFGS, LogisticRegressionModel}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object LogisticRegression {
  def main(args: Array[String]) {
    //构建Spark对象
    val conf = new SparkConf()
      .setAppName("logistic_regression")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    // 读取样本数据1，格式为LIBSVM format
    val data = MLUtils.loadLibSVMFile(sc, "D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt")

    //非loadLibSVMFile读取
    val data2 = sc.textFile("D://sparkmllibData/sparkml/mllibdata/sample_naive_bayes_data.txt")
      .map(line => {
        val spl = line.split(",")
        LabeledPoint(spl(0).toDouble, Vectors.dense(spl(1).split(" ").map(_.toDouble)))
      })
    data2.foreach(println)
    //样本数据划分训练样本与测试样本
    //val splits = data.randomSplit(Array(0.7, 0.3), seed = 11L)
    val splits = data2.randomSplit(Array(0.7, 0.3), seed = 11L)
    val training = splits(0).cache()
    val test = splits(1)

    //每个样本的非缺省特征数不一样
    /** sc.textFile("D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt").map(line=>{
      * line.split(" ").length
      * }).foreach(println) */
    /**
      * 1、 SGD
      */
    val numIterations = 100
    val stepSize = 2
    val miniBatchFraction = 0.5
    val model_SGD = LogisticRegressionWithSGD.train(training, numIterations, stepSize, miniBatchFraction)
    println(model_SGD.weights)
    //println(model2.intercept)
    val predictionAndLabels_SGD = test.map {
      case LabeledPoint(label, features) =>
        val prediction_SGD = model_SGD.predict(features)
        (prediction_SGD, label)
    }

    val metrics_SGD = new MulticlassMetrics(predictionAndLabels_SGD)
    val precision_SGD = metrics_SGD.precision
    val recall_SGD = metrics_SGD.recall
    val fMeasure_SGD = metrics_SGD.fMeasure
    println("LogisticRegressionWithLBFGS-precision = " + precision_SGD)
    println("LogisticRegressionWithLBFGS-recall = " + recall_SGD)
    println("LogisticRegressionWithLBFGS-fMeasure = " + fMeasure_SGD)
    val metrics_SGD_auc = new BinaryClassificationMetrics(predictionAndLabels_SGD)
    println("LogisticRegressionWithSGD-areaUnderROC = " + metrics_SGD_auc.areaUnderROC())
    println("LogisticRegressionWithSGD-areaUnderPR = " + metrics_SGD_auc.areaUnderPR())

    //保存模型
    /** val ModelPath = "D://sparkmllibData/sparkml/mllibdata/logistic_regression_model"
      * model.save(sc, ModelPath)
      * val sameModel = LogisticRegressionModel.load(sc, ModelPath) */

    //===========================================================================================================
    println("===========================================================================================================")
    /**
      * 2、 LBFGS
      */
    //新建逻辑回归模型，并训练   L2正则化
    //二分
    val model = new LogisticRegressionWithLBFGS()
      .setNumClasses(2)
      .run(training)
    println(model.weights)
    println(model.weights.size)
    println(model.intercept)
    //println(model.intercept)

    //对测试样本进行测试
    val predictionAndLabels = test.map {
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label)
    }

    val print_predict = predictionAndLabels.take(20)
    println("LogisticRegressionWithLBFGS--prediction" + "\t" + "label")
    for (i <- 0 to print_predict.length - 1) {
      // println(print_predict(i)._1 + "\t" + print_predict(i)._2)
    }
    val print_predict2 = predictionAndLabels_SGD.take(20)
    println("LogisticRegressionWithSGD--prediction" + "\t" + "label")
    for (i <- 0 to print_predict2.length - 1) {
      // println(print_predict2(i)._1 + "\t" + print_predict2(i)._2)
    }

    // 误差计算
    val metrics = new MulticlassMetrics(predictionAndLabels)
    val precision = metrics.precision
    val recall = metrics.recall
    val fMeasure = metrics.fMeasure
    println("LogisticRegressionWithLBFGS-precision = " + precision)
    println("LogisticRegressionWithLBFGS-recall = " + recall)
    println("LogisticRegressionWithLBFGS-fMeasure = " + fMeasure)
  }
}
