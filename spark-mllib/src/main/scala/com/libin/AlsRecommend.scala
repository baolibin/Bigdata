package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating

/**
  * Copyright (c) 2018/09/03. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object AlsRecommend {
  def main(args: Array[String]) {
    //0 构建Spark对象
    val conf = new SparkConf()
      .setAppName("ALS")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //1 读取样本数据
    val data = sc.textFile("D://sparkmllibData/sparkml/mllibdata/test.data")
    val ratings = data.map(_.split(',') match {
      case Array(user, item, rate) =>
        Rating(user.toInt, item.toInt, rate.toDouble)
    })

    //2 使用ALS训练数据建立推荐模型
    val rank = 10
    val numIterations = 20
    val model = ALS.train(ratings, rank, numIterations, 0.01)

    //3从rating中获取user以及product数据集
    val usersProducts = ratings.map {
      case Rating(user, product, rate) =>
        (user, product)
    }
    // 使用推荐模型预对用户和商品进行评分，得到预测评分的数据集
    val predictions =
      model.predict(usersProducts).map {
        case Rating(user, product, rate) =>
          ((user, product), rate)
      }
    // 真实数据和预测数据进行合并
    val ratesAndPreds = ratings.map {
      case Rating(user, product, rate) =>
        ((user, product), rate)
    }.join(predictions)

    val MSE = ratesAndPreds.map {
      case ((user, product), (r1, r2)) =>
        val err = r1 - r2
        err * err
    }.mean()
    println("Mean Squared Error = " + MSE)

    //4 保存/加载模型
    /*model.save(sc, "myModelPath")
  val sameModel = MatrixFactorizationModel.load(sc, "myModelPath")*/
  }
}
