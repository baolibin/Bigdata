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
###### [5）、Spark Streaming程序消费过慢如何解决？]()
###### [6）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？]()
###### [7）、Spark Streaming工作流程是怎样的？和Storm以及Flink有什么区别？]()
###### [8）、Spark Streaming输出小文件问题？]()
###### [9）、Spark Streaming中foreachRDD如何使用？]()
###### [10）、Spark Streaming的启动时序图？]()
###### [11）、Spark Streaming程序调优？]()
###### [12）、Spark Streaming窗口大小？每个窗口处理的数据量？]()
###### [13）、Spark Streaming中updateStateByKey和mapWithState的区别与使用？]()
###### [14）、Spark Streaming面对高峰数据如何处理？]()
