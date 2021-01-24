package com.libin.etl.dumper

import com.libin.common.SparkJobBase
import com.libin.etl.jobs.DumperJob.{FeatureDataStruct, getSortedColumns}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.json.JSONObject


/**
  * Copyright (c) 2021/1/24. libin Inc. All Rights Reserved.
  * Authors: libin <libin>
  * <p>
  * Purpose : 
  */
object JsonDumper extends SparkJobBase {

    /**
      * RDD数据转换存储成JSON格式
      *
      * @param sparkSession SparkSession对象实例
      * @param featureRdd   需要转换的数据
      * @param path         存储的路径
      * @param partitions   落盘分区数
      */
    def processRDDJson(sparkSession: SparkSession,
                       featureRdd: RDD[FeatureDataStruct],
                       path: String,
                       partitions: Int): RDD[String] = {
        val (columns, sortedFeatureId) = getSortedColumns(featureRdd)
        featureRdd.keyBy(_.userId)
                .groupByKey()
                .map {
                    case (uid, iter) =>
                        // featureId到featureValue映射
                        val struts = iter.toList.map(x => (x.featureId, s"${x.featureKey}${separator}${x.featureValue}")).toMap
                        JsonDumper.transStructToJson(uid, sortedFeatureId, struts).toString()
                }.repartition(partitions)
    }

    /**
      * 用户user_id对应的特征摊平,缺省值的特征用None填充.
      */
    def transStructToJson(uid: String,
                          featureIdList: List[Int],
                          struts: Map[Int, String]): JSONObject = {
        val json = new JSONObject()
        json.put("user_id", uid)
        for (i <- featureIdList.indices) {
            if (struts.contains(featureIdList(i))) {
                val Array(featureKey: String, featureValue: String) = struts(featureIdList(i)).split(separator)
                json.put(featureKey, featureValue)
            }
        }
        json
    }

}
