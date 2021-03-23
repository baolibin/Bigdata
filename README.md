#### 大数据分析处理相关框架
    常用的大数据相关处理技术框架

## 1、Hadoop
    Hadoop是一个由Apache基金会所开发的分布式系统基础架构。
* [1.1、Hadoop学习入口](bigdata-hadoop)  
* [1.2、MapReduce相关API操作](bigdata-hadoop/src/main/java/com/libin/api/mapreduce)  
* [1.3、HDFS相关API操作](bigdata-hadoop/src/main/java/com/libin/api/hdfs) 
* [1.4、YARN资源调度器](bigdata-hadoop/src/main/java/com/libin/doc/yarn) 

## 2、Spark
    Spark是基于内存计算的大数据并行计算框架，可用于构建大型的、低延迟的数据分析应用程序
* [Spark-Core](spark-core)  
* [Spark-Streaming](bigdata-spark-streaming)  
* [Spark-Sql](bigdata-spark-sql)  
* [Spark-GraphX](spark-graphx)  
* [Spark-MLlib](spark-mllib)  

## 3、Flink
    Apache Flink是由Apache软件基金会开发的开源流处理框架，其核心是用Java和Scala编写的分布式流数据流引擎。
    Flink以数据并行和流水线方式执行任意流数据程序，Flink的流水线运行时系统可以执行批处理和流处理程序。
* [Flink](bigdata-flink)  

## 4、Hadoop生态圈一些其它技术框架
* [Hive](bigdata-hive/README.md)  
* [Hbase](bigdata-hbase/README.md)  
* [Kafka](bigdata-kafka/README.md)  
* [Doris](bigdata-doris/README.md)  
* [Druid](bigdata-druid/README.md)  
* [Talos](bigdata-info/src/main/java/com/libin/talos/README.md) 
* [Pegasus](bigdata-info/src/main/java/com/libin/pegasus/README.md) 
* [Griffin](bigdata-info/src/main/java/com/libin/griffin/README.md)
* [ElasticSearch](bigdata-info/src/main/java/com/libin/elasticsearch/README.md)
* [Oozie](bigdata-info/src/main/java/com/libin/oozie/README.md)  

## 5、大数据相关技能前奏
* [Scala]()  
* [Java]()  
* [SQL]()  
* [Maven]()  
* [Git]()  
* [Linux Shell]()  
* [大数据算法]()  
* [大数据相关计算机理论知识]()  
* [大数据产品技能]()  
* [大数据应用场景]()  
* [大数据相关硬件知识]()  

## 6、大数据一些相关项目
* [用户画像](bigdata-project/src/main/java/userProfile/readme.md)  
* [Id-Mapping](bigdata-project/src/main/java/idmapping/readme.md)  
* [数据仓库](bigdata-project/src/main/java/dataWarehouse/readme.md)  
* [实时仓库](bigdata-project/src/main/java/realTimeWarehouse/readme.md)  
* [特征系统](bigdata-project/src/main/java/featureEngineering/readme.md)  

## 7、数据相关知识点
* [数据产生]()  
* [数据收集]()
* [数据压缩]()
* [数据加解密]()
* [数据计算处理]()
* [数据存储]()
* [数据延迟]()
* [数据质量]()
* [数据治理]()
* [数据监控]()
* [数据可视化]()
* [数据字典]()
* [数据血缘]()      
* [数据分析]()      
* [数据建模]()      
* [数据集市]()       
* [数据仓库]()           

## 8、大数据学习相关资料
#####  8.1、大数据学习网站
* [Apache官网](http://www.apache.org/)
* [DataFunTalk知乎](https://www.zhihu.com/org/datafuntalk/posts)
* [美团大数据](https://tech.meituan.com/tags/%E5%A4%A7%E6%95%B0%E6%8D%AE.html)
* [InfoQ大数据](https://www.infoq.cn/topic/bigdata)

##### 8.2、大数据学习书籍       
* [Hadoop权威指南]()       
* [Hadoop技术内幕（3本）]()       
* [Hadoop实战]()

##### 8.3、大数据论文
* [The Google File System (HDFS)]()
* [BigTable (HBase)]()
* [MapReduce]()
* [Spark]()

## 9、必会知识点
* [9.1、Spark Core](bigdata-project/src/main/doc/sparkcore.md)
    - [1）、Spark作业提交流程？]()
    - [2）、Spark的内存模型？]()
    - [3）、SparkContext创建流程？源码级别？]()
    - [4）、简述Spark个版本区别？1.x与2.x？]()
    - [5）、使用Spark中遇到过哪些问题？如何解决的？]()
    - [6）、Spark的Shuffle过程？ 和MR Shuffle区别？]()
    - [7）、Spark中的数据倾斜问题有啥好的解决方案？]()
    - [8）、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？]()
    - [9）、Spark On Yarn作业执行流程？yarn-client和yarn-cluster的区别？]()
    - [10）、Spark中Job、Task、RDD、DAG、Stage的理解？]()
    - [11）、Spark中RDD如何通过记录更新的方式容错？]()
    - [12）、Spark常用调优方法？]()
    - [13）、Spark中宽依赖和窄依赖如何理解？]()
    - [14）、Spark中Job和Task如何理解？]()
    - [15）、Spark中Transformation和action区别是什么？列举出常用的方法？]()
    - [16）、Spark中persist()和cache()的区别？]()
    - [17）、Spark中map和mapPartitions的区别？]()
    - [18）、Spark中Worker和Executor的异同？]()
    - [19）、Spark中提供的2中共享变量是啥？]()
    - [20）、菲波那切数列可以用Spark做出来么？]()
    - [21）、看过哪些Spark源码？]()
    - [22）、Spark通信机制？]()
    - [23）、Spark的存储级别有哪些？]()
    - [24）、Spark序列化模式有哪些？]()
    - [25）、Spark使用到的安全协议有哪些？]()
    - [26）、Spark不是模式有哪些？]()

* [9.2、Spark SQL](bigdata-project/src/main/doc/sparksql.md)
    - [1）、Spark SQL和Hive区别？Spark SQL一定比Hive快么？]()
    - [2）、Spark SQL有使用过么？在哪些项目中使用过？]()
    - [3）、Spark SQL中UDF使用？]()
    - [4）、SparkSession、SparkContext和SQLContext区别？]()
    - [5）、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？]()
    - [6）、Spark SQL程序调优？]()

* [9.3、Spark Streaming](bigdata-project/src/main/doc/sparkstreaming.md)
    - [1）、Spark Streaming如何保证数据仅且消费一次？]()
    - [2）、Spark Streaming中DataFrame和DataSet区别？]()
    - [3）、Spark Streaming如何做checkoutPoint检查点？]()
    - [4）、Spark Streaming如何设置batch大小？]()
    - [5）、Spark Streaming程序消费过慢如何解决？]()
    - [6）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？]()
    - [7）、Spark Streaming工作流程是怎样的？和Storm以及Flink有什么区别？]()
    - [8）、Spark Streaming输出小文件问题？]()
    - [9）、Spark Streaming中foreachRDD如何使用？]()
    - [10）、Spark Streaming的启动时序图？]()
    - [11）、Spark Streaming程序调优？]()

* [9.4、Flink Streaming](bigdata-project/src/main/doc/flinkstreaming.md)
    - [1）、Flink如何保证数据仅且消费一次？]()
    - [2）、Flink如何做checkoutPoint检查点？]()
    - [3）、Flink程序消费过慢如何解决？]()
    - [4）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？](bigdata-flink/src/main/scala/com/libin/data/flink/streaming/etl/GenCodeFromState.scala)
    - [5）、Flink中时间有几种？]()
    - [6）、Flink中窗口有几种？]()
    - [7）、Flink中state如何理解？]()
    - [8）、Flink中Operator是啥？]()
    - [9）、Flink中StreamExecutionEnvironment初始化流程？]()
    - [10）、用过DataStream里面的哪些方法？]()
    - [11）、Flink程序调优？]()

* [9.5、Flink Batch](bigdata-project/src/main/doc/flinkbatch.md)
    - [1）、Flink中ExecutionEnvironment初始化流程？]()
    - [2）、用过DataSet里面的哪些方法？]()

* [9.6、Hive](bigdata-project/src/main/doc/hive.md)

* [9.7、HBase](bigdata-project/src/main/doc/hbase.md)

* [9.8、Kafka](bigdata-project/src/main/doc/kafka.md)
    - [1）、Kafka如何保证消息的顺序？]()
