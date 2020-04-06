package com.libin.etl.loader.data

import com.libin.etl.loader.utils.loadUtils.stu
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object rddBuilder {


  /**
    * 返回RDD类型
    * @param sc  SparkContext
    * @return
    */
  def createRdd(sc: SparkContext): RDD[stu] = {
    val arr: Array[stu] = Array(stu("xiaoming", 22, 175),
      stu("xiaoli", 18, 161),
      stu("xiaoqiang", 26, 198),
      stu("xiaohong", 18, 158))
    sc.makeRDD(arr)
  }
}
