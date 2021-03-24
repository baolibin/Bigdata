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
    - [26）、Spark部署模式有哪些？]()
    - [27）、Spark的cache后能不能接其它算子？是不是action操作?]()
    - [28）、Spark中reduceByKey是action算子不？reduec呢?]()
    - [29）、Spark中数据本地性是哪个阶段确定的？]()
    - [30）、Spark中RDD的弹性提现在哪里？]()
    - [31）、Spark中容错机制？]()
    - [32）、Spark中RDD的缺陷？]()
    - [33）、Spark中有哪些聚合类的算子？应该避免什么类型的算子?]()
    - [34）、Spark中并行度怎么设置比较合理一些？]()
    - [35）、Spark中数据的位置由谁来管理？]()
    - [36）、Spark中数据本地性有哪几种？]()
    - [37）、Spark如何处理不被序列化的数据？]()
    - [38）、Spark中collect功能是啥？其底层是如何实现的?]()
    - [39）、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？]()
    - [40）、Spark中map和flatmap有啥区别？]()
    - [41）、介绍一下join操作优化经验？]()
    - [42）、Spark有哪些组件？]()
    - [43）、Spark的工作机制？]()
    - [44）、Spark中的宽窄依赖？]()
    - [45）、Spark如何划分stage？]()
    - [46）、spark-submit时候如何引用外部的jar包？]()
    - [47）、Spark中RDD有哪些特性？]()
    - [48）、Spark的一个工作流程？]()
    - [49）、Spark on yarn与standalone区别？]()
    - [50）、Spark优化之内存管理？]()
    - [51）、Spark优化之广播变量？]()
    - [52）、Spark优化之数据本地性？]()
    - [53）、Spark中task有几种类型？]()

* [9.2、Spark SQL](bigdata-project/src/main/doc/sparksql.md)
    - [1）、Spark SQL和Hive区别？Spark SQL一定比Hive快么？]()
    - [2）、Spark SQL有使用过么？在哪些项目中使用过？]()
    - [3）、Spark SQL中UDF使用？]()
    - [4）、SparkSession、SparkContext和SQLContext区别？]()
    - [5）、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？]()
    - [6）、Spark SQL程序调优？]()
    - [6）、Spark SQL运行原理？]()

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

* [9.4、Flink Streaming](bigdata-project/src/main/doc/flinkstreaming.md)
    - [1）、Flink如何保证数据仅且消费一次？](#9.4.1)
    - [2）、Flink如何做checkPoint检查点？分布式快照原理是啥?]()
    - [3）、Flink程序消费过慢如何解决？]()
    - [4）、统计实时流中某一单词出现的总个数（eg：比如一天某商品被点击的PV）？](bigdata-flink/src/main/scala/com/libin/data/flink/streaming/etl/GenCodeFromState.scala)
    - [5）、Flink中时间有几种？]()
    - [6）、Flink中窗口有几种？]()
    - [7）、Flink中state如何理解？状态机制?]()
    - [8）、Flink中Operator是啥？]()
    - [9）、Flink中StreamExecutionEnvironment初始化流程？]()
    - [10）、用过DataStream里面的哪些方法？]()
    - [11）、Flink程序调优？]()
    - [12）、Flink如何解决数据乱序问题？Watermark使用过么?EventTime+Watermark可否解决数据乱序问题?]()
    - [13）、Flink的checkpoint存储有哪些(状态存储)？]()
    - [14）、Flink如何实现exactly-once？]()
    - [15）、海量key去重,双十一场景,滑动窗口长度为1小时,滑动距离为10s,亿级别用户,如何计算UV？]()
    - [16）、Flink的checkpoint和spark streaming比较？]()
    - [17）、Flink CEP编程中当状态没有达到时候,数据会保存在哪里？]()
    - [18）、3种时间语义？]()
    - [19）、Flink面对高峰数据如何处理？]()
    - [20）、Flink程序运行慢如何优化处理？]()
    - [21）、Flink程序延迟高如何解决？]()
    - [22）、Flink如何做容错？]()
    - [23）、Flink有没有重启策略？说说有哪几种?]()
    - [24）、Flink分布式快照原理是什么?]()
    - [25）、Flink的Kafka连接器有什么特别的地方?]()
    - [26）、Flink的内存管理?]()
    - [27）、Flink序列化都有哪些?怎么实现的?]()
    - [28）、Flink的window出现了数据倾斜,如何解决?]()
    - [29）、Flink在使用聚合函数GroupBy、KeyBy、Distinct等函数出现数据热点如何解决?]()
    - [30）、Flink如何处理反压?和spark streaming和storm区别有了解么?]()
    - [31）、Flink的Operator Chains算子链了解么?]()
    - [32）、Flink什么时候会把Operator Chain在一起行程算子链?]()
    - [33）、Flink1.7特性?Flink1.9特性]()
    - [34）、Flink组件栈有哪些?]()
    - [35）、Flink运行需要依赖哪些组件?必须依赖Hadoop么?]()
    - [36）、Flink基础编程模型?]()
    - [37）、Flink集群有哪些角色?各有什么作用?]()
    - [38）、Flink中Task Slot概念?Slot和parallelism区别?]()
    - [39）、Flink中常用算子有哪些?]()
    - [40）、Flink分区策略?]()
    - [41）、Flink并行度如何设置?]()
    - [42）、Flink分布式缓存用过没?如何使用?]()
    - [43）、Flink广播变量,使用时候需要注意什么?]()
    - [44）、Flink Table&SQL熟悉不?TableEnvironment这个类有什么作用?]()
    - [45）、Flink SQL实现原理是什么?如何实现SQL的解析?]()
    - [46）、Flink如何支持流批一体的?]()
    - [47）、Flink如何支如何做到高效的数据转换?]()
    - [48）、Flink如何做内存管理?]()
    - [49）、Flink Job提交流程?]()
    - [50）、Flink的三层图结构是哪几个图?]()
    - [51）、Flink中JobManager在集群中扮演的角色?]()
    - [52）、Flink中JobManager在集群启动中扮演的角色?]()
    - [53）、Flink中TaskManager在集群中扮演的角色?]()
    - [54）、Flink中TaskManager在集群启动时候扮演的角色?]()
    - [55）、Flink计算资源的调度是如何实现的?]()
    - [56）、简述Flink的数据抽象以及数据交换过程?]()
    - [57）、FlinkSQL的实现原理?]()
    - [58）、Flink压测和监控?]()
    - [59）、有了Spark为啥还要用Flink?]()
    - [60）、Flink的应用架构有哪些?]()




* [9.5、Flink Batch](bigdata-project/src/main/doc/flinkbatch.md)
    - [1）、Flink中ExecutionEnvironment初始化流程？]()
    - [2）、用过DataSet里面的哪些方法？]()

* [9.6、Hive](bigdata-project/src/main/doc/hive.md)

* [9.7、HBase](bigdata-project/src/main/doc/hbase.md)

* [9.8、Kafka](bigdata-project/src/main/doc/kafka.md)
    - [1）、Kafka如何保证消息的顺序？]()
    - [2）、Kafka的receiver和direct区别？]()
    - [3）、Kafka和Flink保证仅消费一次？]()




--- 


###### <span id="9.4.1">Flink如何保证数据仅且消费一次？</span>
















