package com.libin.etl.processor

import com.libin.common.SparkJobBase
import org.joda.time.DateTime

/**
  * Copyright (c) 2018/7/1. xixi Inc. All Rights Reserved.
  * Authors: libin <2578858653@qq.com>
  *
  * Purpose :
  */

object OpProcessor extends SparkJobBase {
    val path = s"D:\\tmp\\sparksql_${new DateTime().getMillis}"

    def main(args: Array[String]): Unit = {
        val spark = createSparkSessionLocal()
        import spark.implicits._

        // Create a simple DataFrame, store into a partition directory
        val squaresDF = spark.sparkContext.makeRDD(Array(1)).map {
            i =>
                (i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i, i) //, i, i, i, i, i, i, i, i, i)
        }.toDF("value", "square1", "square2", "square3", "square4", "square5", "square6", "square7", "square8", "square9",
            "square10", "square11", "12", "square13", "square14", "square15", "square16", "square17", "square18", "square19", "square20" /*,
            "square21", "square22", "square23", "square24", "square25", "square26", "square27", "square28", "square29"*/)
        print(squaresDF.schema)
        print(squaresDF.show())
        squaresDF.write.parquet(path)
    }
}
