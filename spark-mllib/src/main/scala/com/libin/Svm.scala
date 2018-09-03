package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object Svm {
  def main(args: Array[String]) {
    //1 构建Spark对象
    val conf = new SparkConf()
      .setAppName("svm")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    // 读取样本数据1，格式为LIBSVM format
    val data = MLUtils.loadLibSVMFile(sc, "D://sparkmllibData/sparkml/mllibdata/sample_libsvm_data.txt")

    //样本数据划分训练样本与测试样本
    val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
    val training = splits(0).cache()
    val test = splits(1)

    //新建逻辑回归模型，并训练
    val numIterations = 100
    val model = SVMWithSGD.train(training, numIterations)

    //对测试样本进行测试
    val predictionAndLabel = test.map { point =>
      val score = model.predict(point.features)
      (score, point.label)
    }
    val print_predict = predictionAndLabel.take(20)
    println("prediction" + "\t" + "label")
    for (i <- 0 to print_predict.length - 1) {
      println(print_predict(i)._1 + "\t" + print_predict(i)._2)
    }

    // 误差计算
    val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()
    println("Area under ROC = " + accuracy)

    //保存模型
    /*val ModelPath = "D://sparkmllibData/sparkml/mllibdata/svm_model"
    model.save(sc, ModelPath)
    val sameModel = SVMModel.load(sc, ModelPath)*/
  }
}
