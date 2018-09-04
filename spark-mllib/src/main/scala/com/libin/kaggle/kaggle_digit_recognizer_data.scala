package com.libin.kaggle

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Copyright (c) 2017/06/26. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object kaggle_digit_recognizer_data {
  def main(args: Array[String]) {
    //构建Spark对象
    val conf = new SparkConf()
      .setAppName("kaggle_digit_recognizer_data")
      .setMaster("local")
      .set("spark.driver.memory", "2G")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //将预测结果转换成kaggle识别的格式 ， 第一行还要加上 ImageId,Label
    var count = 0
    val train_data = sc.textFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/prediction_rf/part-00000")
      .map(line => {
        count += 1
        count + "," + line
      })
      .repartition(1).saveAsTextFile("E://_deeplearning/Digit-Recognizer-Kaggle-master/data/result_rf")

  }
}
