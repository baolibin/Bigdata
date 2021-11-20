
* [SparkStreaming]()
    - [1、SparkStreaming原理概述？]()
    - [2、SparkStreaming使用场景？]()
        - [1.1、SparkStreaming必会知识点？[点击跳转]](../bigdata-project/src/main/doc/sparkstreaming.md)
    - [3、SparkStreaming如何使用？]()
        - [3.1、SparkStreaming文档？[点击跳转]](src/main/doc)
        - [3.2、SparkStreaming示例代码？[点击跳转]](src/main/scala/com/libin/data/streaming)
    - [4、SparkStreaming调优？]()
    - [5、SparkStreaming源码？]()
    - [6、SparkStreaming学习资料？]()

###### [1、SparkStreaming原理概述？]()
    spark streaming基本原理为将输入数据流以时间片为单位进行拆分，然后以批处理的方式处理每个时间片的数据。
![实时数据流](./src/main/image/1.png)
![数据流处理](./src/main/image/2.png)

    Spark Streaming使用DSTream来表示一个连续的数据流。
    DSTream被表示为一系列连续的RDDs,其中每个RDD包含来自一定时间间隔的数据。

###### [2、SparkStreaming使用场景？]()

###### [3、SparkStreaming如何使用？]()

###### [4、SparkStreaming调优？]()

###### [5、SparkStreaming源码？]()

###### [6、SparkStreaming学习资料？]()
* [6.1、官网](http://spark.apache.org/streaming/)
* [6.2、文档](http://spark.apache.org/docs/latest/streaming-programming-guide.html)
* [6.3、Spark Streaming2.2文档](https://spark.apache.org/docs/2.2.0/streaming-programming-guide.html)
