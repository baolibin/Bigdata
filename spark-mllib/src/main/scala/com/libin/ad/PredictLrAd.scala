package com.libin.ad

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.LogisticRegressionModel
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
object PredictLrAd {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("FilterFeature").setMaster("local[4]").set("spark.driver.memory", "6G")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    val test_data: RDD[LabeledPoint] = sc.textFile("E://sparkmllibData/ad_test_data.txt")
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

    val ModelPath = "E://sparkmllibData/LogisticRegressionWithLBFGS"
    val lrModel = LogisticRegressionModel.load(sc, ModelPath)

    test_data.map {
      case LabeledPoint(label, features) =>
        val prediction = lrModel.predict(features)
        val instance_id: String = features.toArray(0).toString
        (instance_id, prediction)
    }

  }
}
