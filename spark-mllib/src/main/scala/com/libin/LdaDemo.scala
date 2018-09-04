package com.libin

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.LDA
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.clustering.DistributedLDAModel

/**
  * Copyright (c) 2018/09/04. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  * <p>
  * Purpose :
  */
object LdaDemo {
  def main(args: Array[String]) {
    //0 构建Spark对象
    val conf = new SparkConf()
      .setAppName("lda")
      .setMaster("local")
    val sc = new SparkContext(conf)
    Logger.getRootLogger.setLevel(Level.WARN)

    //1 加载数据，返回的数据格式为：documents: RDD[(Long, Vector)]
    // 其中：Long为文章ID，Vector为文章分词后的词向量
    // 可以读取指定目录下的数据，通过分词以及数据格式的转换，转换成RDD[(Long, Vector)]即可
    val data = sc.textFile("D://sparkmllibData/sparkml/mllibdata/sample_lda_data.txt")
    val parsedData = data.map(s => Vectors.dense(s.trim.split(' ').map(_.toDouble)))
    // Index documents with unique IDs
    val corpus = parsedData.zipWithIndex.map(_.swap).cache()

    //2 建立模型，设置训练参数，训练模型
    /**
      * k: 主题数，或者聚类中心数
      * TopicConcentration：主题分布的超参数(Dirichlet分布的参数)，必需>1.0，值越大，推断出的分布越平滑
      * DocConcentration：文章分布的超参数(Dirichlet分布的参数)，必需>1.0，值越大，推断出的分布越平滑
      * MaxIterations：迭代次数，需充分迭代，至少20次以上
      * setSeed：随机种子
      * CheckpointInterval：迭代计算时检查点的间隔
      * Optimizer：优化计算方法，目前支持"em", "online" ，em方法更占内存，迭代次数多内存可能不够会抛出stack异常
      */
    val ldaModel = new LDA().
      setK(3).
      setDocConcentration(5).
      setTopicConcentration(5).
      setMaxIterations(20).
      setSeed(0L).
      setCheckpointInterval(10).
      setOptimizer("em").
      run(corpus)

    //3 模型输出，模型参数输出，结果输出
    println("Learned topics (as distributions over vocab of " + ldaModel.vocabSize + " words):")
    // 主题分布
    val topics = ldaModel.topicsMatrix
    for (topic <- Range(0, 3)) {
      print("Topic " + topic + ":")
      for (word <- Range(0, ldaModel.vocabSize)) {
        print(" " + topics(word, topic));
      }
      println()
    }

    // 主题分布排序
    ldaModel.describeTopics(4)
    // 文档分布
    val distLDAModel = ldaModel.asInstanceOf[DistributedLDAModel]
    distLDAModel.topicDistributions.collect

  }
}
