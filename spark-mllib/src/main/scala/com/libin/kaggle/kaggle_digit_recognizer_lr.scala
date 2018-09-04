package com.libin.kaggle

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.{LogisticRegressionModel, LogisticRegressionWithLBFGS, LogisticRegressionWithSGD}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2017/06/25. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object kaggle_digit_recognizer_lr {
  def main(args: Array[String]) {
    //构建Spark对象
    val conf = new SparkConf()
      .setAppName("kaggle_digit_recognizer")
      .setMaster("local[4]")
      .set("spark.driver.memory", "6G")
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

    val model = new LogisticRegressionWithLBFGS()
      .setNumClasses(10)
      .run(training)
    println(model.weights)
    println(model.weights.size)
    //println(model.intercept) //截距

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

    //保存模型
    /** val ModelPath = "E://_deeplearning/Digit-Recognizer-Kaggle-master/data/model"
      *model.save(sc, ModelPath) */
    //val sameModel = LogisticRegressionModel.load(sc, ModelPath)
    //=========================================================================================================
    //读取测试数据 test
    /** val test_data=sc.textFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/test.csv")
      * .map(line=>{
      *line.split(",")
      * })
      * val features=test_data.map(line=>{
      * val f=line.map(p => p.toDouble)
      *Vectors.dense(f)
      * })
      * //将预测结果写入本地磁盘
      * val predictions=model.predict(features).map(p=>p.toInt)
      *predictions.repartition(1).saveAsTextFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/prediction.txt")
      */
  }
}
