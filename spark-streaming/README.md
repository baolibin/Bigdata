
##### spark streaming

* [官网](http://spark.apache.org/streaming/)
* [文档](http://spark.apache.org/docs/latest/streaming-programming-guide.html)
* [Spark Streaming2.2文档](https://spark.apache.org/docs/2.2.0/streaming-programming-guide.html)

#####
    spark streaming基本原理为将输入数据流以时间片为单位进行拆分，然后以批处理的方式处理每个时间片的数据。

![实时数据流](./src/main/image/1.png)

![数据流处理](./src/main/image/2.png)

    Spark Streaming使用DSTream来表示一个连续的数据流。
    DSTream被表示为一系列连续的RDDs,其中每个RDD包含来自一定时间间隔的数据。
    