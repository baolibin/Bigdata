* [9.3、Spark Streaming](bigdata-project/src/main/doc/sparkstreaming.md)
    - [1）、Spark Streaming如何保证数据仅且消费一次？]()
    - [2）、Spark Streaming中DataFrame和DataSet区别？]()
    - [3）、Spark Streaming如何做checkPoint检查点？]()
    - [4）、Spark Streaming如何设置batch大小？]()
    - [5）、Spark Streaming程序消费过慢如何解决？]()
    - [6）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？]()
    - [7）、Spark Streaming工作流程是怎样的？和Storm以及Flink有什么区别？]()
    - [8）、Spark Streaming输出小文件问题？]()
    - [9）、Spark Streaming中foreachRDD如何使用？]()
    - [10）、Spark Streaming的启动时序图？]()
    - [11）、Spark Streaming程序调优？]()
    - [12）、Spark Streaming窗口大小？每个窗口处理的数据量？]()
    - [13）、Spark Streaming中updateStateByKey和mapWithState的区别与使用？]()
    - [14）、Spark Streaming面对高峰数据如何处理？]()

---
###### [1）、Spark Streaming如何保证数据仅且消费一次？]()
    定期做checkpoint，保存上游消息队列的offset位置信息。

###### [2）、Spark Streaming中DataFrame和DataSet区别？]()
    RDD是分布式的 Java对象的集合。DataFrame是分布式的Row对象的集合，Dataset每一个record存储的是一个强类型值而不是一个Row。
    
    spark 新特性主要增加DataFrame/DataSet、Structured Streaming和Spark Session
    1. DataFrame/DataSet主要替换之前的RDD，主要优势在执行效率、集群间通信、执行优化和GC开销比RDD有优势。
    2. Structured Streaming大部分场景替换之前的Streaming，比之前的优势集中中简洁的模型、一致的API、卓越的性能和Event Time的支持
    3. SparkSession的概念，它为用户提供了一个统一的切入点来使用Spark的各项功能，SparkConf、SparkContext和SQLContext都已经被封装在SparkSession当中。

###### [3）、Spark Streaming如何做checkPoint检查点？]()
###### [4）、Spark Streaming如何设置batch大小？]()
    设置sparkstreaming批处理的时间间隔:val ssc = new StreamingContext(conf, Seconds(5))
    每个Batch Duration时间去提交一次job，如果job的处理时间超过Batch Duration，会使得job无法按时提交。
    随着时间推移，越来越多的作业被拖延，最后导致整个Streaming作业被阻塞，无法做到实时处理数据。

###### [5）、Spark Streaming程序消费过慢如何解决？]()
###### [6）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？]()
###### [7）、Spark Streaming工作流程是怎样的？和Storm以及Flink有什么区别？]()
###### [8）、Spark Streaming输出小文件问题？]()
    原因是sparkstreaming的微批处理模式和DStream(RDD)的分布式(partition)特性导致的。
    sparkstreaming为每个partition启动一个独立的线程来处理数据，一旦文件输出到HDFS，那么这个文件流就关闭了，
    再来一个batch的parttition任务，就再使用一个新的文件流。
    假设，一个batch为10s，每个输出的DStream有32个partition，那么一个小时产生的文件数将会达到(3600/10)*32=11520个之多。
    1、增加batch大小
    2、输出之前重分区
    3、定时批处理任务合并
    4、foreach的append方法追加

###### [9）、Spark Streaming中foreachRDD如何使用？]()
###### [10）、Spark Streaming的启动时序图？]()
###### [11）、Spark Streaming程序调优？]()
###### [12）、Spark Streaming窗口大小？每个窗口处理的数据量？]()
###### [13）、Spark Streaming中updateStateByKey和mapWithState的区别与使用？]()
###### [14）、Spark Streaming面对高峰数据如何处理？]()
