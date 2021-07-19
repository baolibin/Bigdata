package com.libin.onehot

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.json.JSONObject

/**
  *  给女朋友写的代码
  */
object DataParse extends Serializable {

    def main(args: Array[String]): Unit = {

        val conf = new SparkConf().setMaster("local").setAppName("DataParse")
        val sc = new SparkContext(conf)

        val pathFile = "/home/baolibin/user_profile.txt"
        val rddFile: RDD[String] = sc.textFile(pathFile)

        val res: RDD[String] = rddFile.map {
            x =>
                val lineJson = new JSONObject()
                val resJson = new JSONObject()
                val sp = x.split("\\s+")
                val userid = sp(0)
                process(resJson, sp(2), "xqxl")
                //.split("\u0002").head.split("\u0001") //学历
                process(resJson, sp(3), "flrpj")
                process(resJson, sp(4), "flccz")
                process(resJson, sp(5), "flbt")
                process(resJson, sp(6), "flhj")
                process(resJson, sp(7), "fljq")
                process(resJson, sp(8), "flbx")
                process(resJson, sp(9), "flxz")
                process(resJson, sp(10), "fljl")
                lineJson.put(userid, resJson.toString()).toString()
        }

        res.collect().foreach(println)

    }

    // 针对每一个用户画像的数据，进行拆分，组合成json形式
    def process(resJson: JSONObject, stringArgs: String, typeArgs: String): Unit = {
        val tmp = stringArgs.split("\u0002")
        if (typeArgs.trim.equals("xqxl")) {
            val xqxl_1 = tmp.head.split("\u0001")
            resJson.put(xqxl_1.head, xqxl_1.last)
        }
        if (stringArgs.trim.equals("-")) {
            resJson.put("-", "null")
        } else {
            for (arr <- tmp) {
                val tmp1 = arr.split("\u0001")
                resJson.put(tmp1.head, tmp1.last)
            }
        }
    }

}
