package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.evaluation.RegressionMetrics
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.linalg.Vectors

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  * 关于正则化手段
  * 线性回归同样可以采用正则化手段，其主要目的就是防止过拟合。
  * 当采用L1正则化时，则变成了Lasso Regresion；当采用L2正则化时，则变成了Ridge Regression；线性回归未采用正则化手段。
  * 通常来说，在训练模型时是建议采用正则化手段的，特别是在训练数据的量特别少的时候，若不采用正则化手段，过拟合现象会非常严重。
  * L2正则化相比L1而言会更容易收敛（迭代次数少），但L1可以解决训练数据量小于维度的问题（也就是n元一次方程只有不到n个表达式，这种情况下是多解或无穷解的）。
  */
object LinearRegression {
  def main(args: Array[String]) {
    // 构建Spark对象
    val conf = new SparkConf()
      .setAppName("LinearRegressionWithSGD")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //读取样本数据
    val data_path1 = "D://sparkmllibData/sparkml/mllibdata/lpsaTest.data"
    val data = sc.textFile(data_path1)
    val inputdata = data.map { line =>
      val parts = line.split('\t')
      val fea = Array(parts(0), parts(1), parts(2), parts(3), parts(4), parts(5), parts(6), parts(7))
      LabeledPoint(parts(8).toDouble, Vectors.dense(fea.map(_.toDouble)))
      //LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(" ").map(_.toDouble)))
    }.cache()
    //(15.0,[1.0,0.455,0.365,0.095,0.514,0.2245,0.101,0.15])
    //val numinputdata = inputdata.count()

    // 新建线性回归模型，并设置训练参数
    val numIterations = 1000
    val stepSize = 2
    val miniBatchFraction = 1
    val regParam = 0.01
    val model = LinearRegressionWithSGD.train(inputdata, numIterations, stepSize, miniBatchFraction)
    println("linear训练模型权重：" + model.weights)
    //println("linear训练模型偏置："+model.intercept)
    val model1 = LassoWithSGD.train(inputdata, numIterations, stepSize, regParam, miniBatchFraction)
    println("lasso训练模型权重：" + model1.weights)
    //println("lasso训练模型偏置："+model1.intercept)
    val model2 = RidgeRegressionWithSGD.train(inputdata, numIterations, stepSize, regParam, miniBatchFraction)
    println("ridge训练模型权重：" + model2.weights)
    //println("linear训练模型偏置："+model2.intercept)

    // linear对样本进行测试
    println("==================linear对样本进行测试==================")
    val prediction = model.predict(inputdata.map(_.features))
    val predictionAndLabel = prediction.zip(inputdata.map(_.label))
    val print_predict = predictionAndLabel.take(20)
    println("预测值" + "\t" + "实际值")
    for (i <- 0 until print_predict.length) {
      //println(print_predict(i)._1 + "\t" + print_predict(i)._2)
    }
    // lasso对样本进行测试
    println("==================lasso对样本进行测试==================")
    val prediction1 = model1.predict(inputdata.map(_.features))
    val predictionAndLabel1 = prediction1.zip(inputdata.map(_.label))
    val print_predict1 = predictionAndLabel1.take(20)
    println("预测值" + "\t" + "实际值")
    for (i <- 0 until print_predict1.length) {
      //println(print_predict1(i)._1 + "\t" + print_predict1(i)._2)
    } // linear对样本进行测试
    println("==================ridge对样本进行测试==================")
    val prediction2 = model2.predict(inputdata.map(_.features))
    val predictionAndLabel2 = prediction2.zip(inputdata.map(_.label))
    val print_predict2 = predictionAndLabel2.take(20)
    println("预测值" + "\t" + "实际值")
    for (i <- 0 until print_predict2.length) {
      //println(print_predict2(i)._1 + "\t" + print_predict2(i)._2)
    }
    //预测值：f
    //实际值 y  , 平均 y`
    //平均绝对误差 MAE: 1/n∑|f - y|   预测值与真实值之间平均相差多大
    //均方误差 MSE  ∑(f - y)^2/n    该统计参数是预测数据和原始数据对应点误差的平方和的均值，也就是SSE/n
    //均方根误差 RMSE √[∑(f - y)^2/n]   MSE的平方根
    //确定系数 R-squared
    //SSR:预测数据与原始数据均值之差的平方和  ∑(f - y`)^2
    //SST:原始数据和均值之差的平方和  ∑(y - y`)^2
    //SSE:预测数据和原始数据的平方和  ∑(f - y)^2
    //SST=SSE+SSR     R-squared=SSR/SST 或 1-SSE/SST
    //正常取值范围为[0 1]，越接近1，表明方程的变量对y的解释能力越强，这个模型对数据拟合的也较好
    println("==================linear对样本进行测试==================")
    val metrics = new RegressionMetrics(predictionAndLabel)
    //println(metrics.explainedVariance) //解释方差
    println("平均绝对误差 MAE:" + metrics.meanAbsoluteError) //平均绝对误差 MAE
    //println("均方误差 MSE:"+metrics.meanSquaredError) //均方误差 MSE
    println("均方根误差 RMSE:" + metrics.rootMeanSquaredError) //均方根误差 RMSE
    println("R-squared:" + metrics.r2) //确定系数 R-squared误差取值范围为0到1，这个值越接近1说明模型的拟合度越好。
    println("==================lasso对样本进行测试==================")
    val metrics1 = new RegressionMetrics(predictionAndLabel1)
    println("平均绝对误差 MAE:" + metrics1.meanAbsoluteError) //平均绝对误差 MAE
    //println("均方误差 MSE:"+metrics1.meanSquaredError) //均方误差 MSE
    println("均方根误差 RMSE:" + metrics1.rootMeanSquaredError) //均方根误差 RMSE
    println("R-squared:" + metrics1.r2) //确定系数 R-squared误差取值范围为0到1，这个值越接近1说明模型的拟合度越好。
    println("==================ridge对样本进行测试==================")
    val metrics2 = new RegressionMetrics(predictionAndLabel2)
    println("平均绝对误差 MAE:" + metrics2.meanAbsoluteError) //平均绝对误差 MAE
    //println("均方误差 MSE:"+metrics2.meanSquaredError) //均方误差 MSE
    println("均方根误差 RMSE:" + metrics2.rootMeanSquaredError) //均方根误差 RMSE
    println("R-squared:" + metrics2.r2) //确定系数 R-squared误差取值范围为0到1，这个值越接近1说明模型的拟合度越好。

    // 计算测试误差
    /*val loss = predictionAndLabel.map {
      case (p, l) =>
        val err = p - l
        err * err
    }.reduce(_ + _)
    val rmse = math.sqrt(loss / numinputdata)
    println("均方根误差:"+rmse)*/
    // 模型保存
    /*val ModelPath = "D://sparkmllibData/sparkml/mllibdata/LinearRegressionModel"
    model.save(sc, ModelPath)
    val sameModel = LinearRegressionModel.load(sc, ModelPath)*/
  }
}
