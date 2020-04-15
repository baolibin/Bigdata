package com.libin.etl.loader.data

import com.libin.common.SparkJobBase
import com.libin.etl.utils.LoadUtils.stu
import com.libin.etl.utils.PathUtils
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row

/**
 * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
 * Authors: libin <2578858653@qq.com>
 *
 * Purpose :
 */

object RddBuilder extends SparkJobBase {

    /**
     * 返回RDD类型
     *
     * @param sc SparkContext
     * @return
     */
    def createRdd(sc: SparkContext): RDD[stu] = {
        val arr: Array[stu] = Array(stu("xiaoming", 22, 175),
            stu("xiaoli", 18, 161),
            stu("xiaoqiang", 26, 198),
            stu("xiaohong", 18, 158))
        sc.makeRDD(arr)
    }

    /**
     * 读取指定路径下面，指定字段的数据
     *
     * @param path   数据路径
     * @param date   分区时间
     * @param isTest 是否测试
     * @param cols   列字段集合
     */
    def loadDwsBigDataDeviceProfileDBySql(path: String,
                                          date: String,
                                          isTest: Boolean = false,
                                          cols: List[String]): RDD[Row] = {
        createSparkSessionLocal()
                .read
                .parquet(PathUtils.pathAssemble(path, date))
                .selectExpr(cols: _*).rdd
    }
}
