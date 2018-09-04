package com.libin.ad

import java.text.DecimalFormat

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object TrainFeatureLrAd {
  def main(args: Array[String]): Unit = {
    @transient
    val conf = new SparkConf().setAppName("FilterFeature").setMaster("local[6]").set("spark.driver.memory", "6G")
    @transient
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    val train_data: RDD[LabeledPoint] = sc.textFile("E://sparkmllibData/ad_train_data.txt")
      .flatMap(line => {
        val spl = line.split("\t")
        if (spl(0) != "label") {
          val label = spl(0).toInt
          val instance_id = spl(1).toDouble
          val click_time = spl(2).toDouble
          val ad_id = spl(3).toDouble
          val user_id = spl(4).toDouble
          val position_id = spl(5).toDouble
          val miui_version = spl(7).toDouble
          val android_version = spl(8).toDouble
          val advertiser_id = spl(9).toDouble
          val compaign_id = spl(10).toDouble
          val app_id = spl(11).toDouble
          val age = spl(12).toDouble
          val gender = spl(13).toDouble
          val province = spl(15).toDouble
          val city = spl(16).toDouble
          val device_info = spl(17).toDouble
          //val features = spl.slice(1, spl.size).map(line => line.toDouble)
          Some(LabeledPoint(label, Vectors.dense(Array(instance_id, click_time, ad_id, user_id, position_id, miui_version, android_version,
            advertiser_id, compaign_id, app_id, age, gender, province, city, device_info))))
        } else {
          None
        }
      }) //.foreach(println)
    val splits = train_data.randomSplit(Array(0.8, 0.2), seed = 11L)
    val training = splits(0) //train_data
    val test = splits(1) //test_data

    val model = new LogisticRegressionWithLBFGS()
      .setNumClasses(2)
      .run(training)
    println(model.weights)
    println(model.weights.size)
    //prediction
    val predictionAndLabels = test.map {
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label)
    }
    val metrics = new MulticlassMetrics(predictionAndLabels)
    val precision = metrics.precision
    val recall = metrics.recall
    println("precision:" + precision)
    println("recall:" + recall)

    val ModelPath = "E://sparkmllibData/LogisticRegressionWithLBFGS"
    model.save(sc, ModelPath)

    val test_feature_data = sc.textFile("E://sparkmllibData/ad_test_data.txt")
      .flatMap(line => {
        val spl = line.split("\t")
        if (spl(0) != "label") {
          val instance_id = spl(1).toDouble
          val click_time = spl(2).toDouble
          val ad_id = spl(3).toDouble
          val user_id = spl(4).toDouble
          val position_id = spl(5).toDouble
          val miui_version = spl(7).toDouble
          val android_version = spl(8).toDouble
          val advertiser_id = spl(9).toDouble
          val compaign_id = spl(10).toDouble
          val app_id = spl(11).toDouble
          val age = spl(12).toDouble
          val gender = spl(13).toDouble
          val province = spl(15).toDouble
          val city = spl(16).toDouble
          val device_info = spl(17).toDouble
          //val features = spl.slice(1, spl.size).map(line => line.toDouble)
          Some(Vectors.dense(Array(instance_id, click_time, ad_id, user_id, position_id, miui_version, android_version,
            advertiser_id, compaign_id, app_id, age, gender, province, city, device_info)))
        } else {
          None
        }
      }) //.foreach(println)
    val df: DecimalFormat = new DecimalFormat("#.#")

    test_feature_data.map {
      features =>
        val score = model.predict(features).toInt
        val instance_id: Double = features.toArray(0)
        val result: String = df.format(instance_id)
        result + "," + score
    }.repartition(1)
      .saveAsTextFile("E://sparkmllibData/test.txt")
  }
}
