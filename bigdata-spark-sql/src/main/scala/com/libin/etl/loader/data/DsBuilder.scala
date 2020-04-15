package com.libin.etl.loader.data

import com.libin.etl.utils.LoadUtils.stu
import org.apache.spark.sql.{Dataset, SparkSession}

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

object DsBuilder {

    /**
     * 读取配置文件中的数据创建Dataset
     *
     * @param ss       SparkSession
     * @param fileName 文件名字
     * @return 返回DataSet数据集
     */
    def readJsonToDs(ss: SparkSession, fileName: String): Dataset[stu] = {
        import ss.implicits._
        val url: String = this.getClass.getClassLoader.getResource(fileName).toString
        ss.read.json(url).as[stu]
    }

    /**
     * 使用Seq+toDf创建DataSet
     *
     * @param ss SparkSession
     * @return 返回DataSet数据集
     */
    def createDsBySeq(ss: SparkSession): Dataset[stu] = {
        import ss.implicits._
        Seq(stu("xiaoming", 22, 175), stu("xiaoli", 18, 161), stu("xiaoqiang", 26, 198), stu("xiaohong", 18, 158))
                .toDS()
    }
}
