
##### 进行CheckPoint的数据有哪些？
    1、元数据checkpoint
        配置信息：创建的Spark Streaming程序的配置信息，比如SparkConf中的信息。
        DStream的操作信息。
        未处理的batch信息，有些job在排队，还没处理的batch信息。
    2、数据checkpoint
        将实时计算中产生的RDD的数据保存在可靠的存储系统中，比如HDFS。

##### 什么时候启用checkpoint机制？
    1、使用了有状态的转换、比如reduceByKeyAndWindow操作。
    2、作业失败重启

