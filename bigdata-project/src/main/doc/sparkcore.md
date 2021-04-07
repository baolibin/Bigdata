* [Spark Core知识点]()
    - [1）、Spark作业提交流程？](#1、Spark作业提交流程？)
    - [2）、Spark的内存模型？](#2、Spark的内存模型？)
    - [3）、SparkContext创建流程？源码级别？](#3、SparkContext创建流程？源码级别？)
    - [4）、简述Spark个版本区别？1.x与2.x？](#4、简述Spark个版本区别？1.x与2.x？)
    - [5）、使用Spark中遇到过哪些问题？如何解决的？](#5、使用Spark中遇到过哪些问题？如何解决的？)
    - [6）、Spark的Shuffle过程？ 和MR Shuffle区别？](#6、Spark的Shuffle过程？和MR Shuffle区别？)
    - [7）、Spark中的数据倾斜问题有啥好的解决方案？](#7、Spark中的数据倾斜问题有啥好的解决方案？)
    - [8）、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？](#8、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？)
    - [9）、Spark On Yarn作业执行流程？yarn-client和yarn-cluster的区别？](#9sparkonyarnyarn-clientyarn-cluster)
    - [10）、Spark中Job、Task、RDD、DAG、Stage的理解？](#10、Spark中Job、Task、RDD、DAG、Stage的理解？)
    - [11）、Spark中RDD如何通过记录更新的方式容错？](#11、Spark中RDD如何通过记录更新的方式容错？)
    - [12）、Spark常用调优方法？](#12、Spark常用调优方法？)
    - [13）、Spark中宽依赖和窄依赖如何理解？](#13、Spark中宽依赖和窄依赖如何理解？)
    - [14）、Spark中Job和Task如何理解？](#14、Spark中Job和Task如何理解？)
    - [15）、Spark中Transformation和action区别是什么？列举出常用的方法？](#15、Spark中Transformation和action区别是什么？列举出常用的方法？)
    - [16）、Spark中persist()和cache()的区别？](#16、Spark中persist()和cache()的区别？)
    - [17）、Spark中map和mapPartitions的区别？](#17、Spark中map和mapPartitions的区别？)
    - [18）、Spark中Worker和Executor的异同？](#18、Spark中Worker和Executor的异同？)
    - [19）、Spark中提供的2中共享变量是啥？](#19、Spark中提供的2中共享变量是啥？)
    - [20）、菲波那切数列可以用Spark做出来么？](#20、菲波那切数列可以用Spark做出来么？)
    - [21）、看过哪些Spark源码？](#21、看过哪些Spark源码？)
    - [22）、Spark通信机制？](#22、Spark通信机制？)
    - [23）、Spark的存储级别有哪些？](#23、Spark的存储级别有哪些？)
    - [24）、Spark序列化模式有哪些？](#24、Spark序列化模式有哪些？)
    - [25）、Spark使用到的安全协议有哪些？](#25、Spark使用到的安全协议有哪些？)
    - [26）、Spark部署模式有哪些？](#26、Spark部署模式有哪些？)
    - [27）、Spark的cache后能不能接其它算子？是不是action操作?](#27、Spark的cache后能不能接其它算子？是不是action操作?)
    - [28）、Spark中reduceByKey是action算子不？reduec呢?](#28、Spark中reduceByKey是action算子不？reduec呢?)
    - [29）、Spark中数据本地性是哪个阶段确定的？](#29、Spark中数据本地性是哪个阶段确定的？)
    - [30）、Spark中RDD的弹性提现在哪里？](#30、Spark中RDD的弹性提现在哪里？)
    - [31）、Spark中容错机制？](#31、Spark中容错机制？)
    - [32）、Spark中RDD的缺陷？](#32、Spark中RDD的缺陷？)
    - [33）、Spark中有哪些聚合类的算子？应该避免什么类型的算子?](#33、Spark中有哪些聚合类的算子？应该避免什么类型的算子?)
    - [34）、Spark中并行度怎么设置比较合理一些？](#34、Spark中并行度怎么设置比较合理一些？)
    - [35）、Spark中数据的位置由谁来管理？](#35、Spark中数据的位置由谁来管理？)
    - [36）、Spark中数据本地性有哪几种？](#36、Spark中数据本地性有哪几种？)
    - [37）、Spark如何处理不被序列化的数据？](#37、Spark如何处理不被序列化的数据？)
    - [38）、Spark中collect功能是啥？其底层是如何实现的?](#38、Spark中collect功能是啥？其底层是如何实现的?)
    - [39）、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？](#39、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？)
    - [40）、Spark中map和flatmap有啥区别？](#40、Spark中map和flatmap有啥区别？)
    - [41）、介绍一下join操作优化经验？](#41、介绍一下join操作优化经验？)
    - [42）、Spark有哪些组件？](#42、Spark有哪些组件？)
    - [43）、Spark的工作机制？](#43、Spark的工作机制？)
    - [44）、Spark中的宽窄依赖？](#44、Spark中的宽窄依赖？)
    - [45）、Spark如何划分stage？](#45、Spark如何划分stage？)
    - [46）、spark-submit时候如何引用外部的jar包？](#46spark-submitjar)
    - [47）、Spark中RDD有哪些特性？](#47、Spark中RDD有哪些特性？)
    - [48）、Spark的一个工作流程？](#48、Spark的一个工作流程？)
    - [49）、Spark on yarn与standalone区别？](#49、Spark on yarn与standalone区别？)
    - [50）、Spark优化之内存管理？](#50、Spark优化之内存管理？)
    - [51）、Spark优化之广播变量？](#51、Spark优化之广播变量？)
    - [52）、Spark优化之数据本地性？](#52、Spark优化之数据本地性？)
    - [53）、Spark中task有几种类型？](#53、Spark中task有几种类型？)

---
###### 1、Spark作业提交流程？
    
###### 2、Spark的内存模型？

###### 3、SparkContext创建流程？源码级别？

###### 4、简述Spark个版本区别？1.x与2.x？

###### 5、使用Spark中遇到过哪些问题？如何解决的？

###### 6、Spark的Shuffle过程？和MR Shuffle区别？

###### 7、Spark中的数据倾斜问题有啥好的解决方案？

###### 8、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？

###### 9、SparkOnYarn作业执行流程？yarn-client和yarn-cluster的区别？

###### 10、Spark中Job、Task、RDD、DAG、Stage的理解？

###### 11、Spark中RDD如何通过记录更新的方式容错？

###### 12、Spark常用调优方法？

###### 13、Spark中宽依赖和窄依赖如何理解？

###### 14、Spark中Job和Task如何理解？

###### 15、Spark中Transformation和action区别是什么？列举出常用的方法？

###### 16、Spark中persist()和cache()的区别？

###### 17、Spark中map和mapPartitions的区别？

###### 18、Spark中Worker和Executor的异同？

###### 19、Spark中提供的2中共享变量是啥？

###### 20、菲波那切数列可以用Spark做出来么？

###### 21、看过哪些Spark源码？

###### 22、Spark通信机制？

###### 23、Spark的存储级别有哪些？

###### 24、Spark序列化模式有哪些？

###### 25、Spark使用到的安全协议有哪些？

###### 26、Spark部署模式有哪些？

###### 27、Spark的cache后能不能接其它算子？是不是action操作?

###### 28、Spark中reduceByKey是action算子不？reduec呢?

###### 29、Spark中数据本地性是哪个阶段确定的？

###### 30、Spark中RDD的弹性提现在哪里？

###### 31、Spark中容错机制？

###### 32、Spark中RDD的缺陷？

###### 33、Spark中有哪些聚合类的算子？应该避免什么类型的算子?

###### 34、Spark中并行度怎么设置比较合理一些？

###### 35、Spark中数据的位置由谁来管理？

###### 36、Spark中数据本地性有哪几种？

###### 37、Spark如何处理不被序列化的数据？

###### 38、Spark中collect功能是啥？其底层是如何实现的?

###### 39、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？

###### 40、Spark中map和flatmap有啥区别？

###### 41、介绍一下join操作优化经验？

###### 42、Spark有哪些组件？

###### 43、Spark的工作机制？

###### 44、Spark中的宽窄依赖？

###### 45、Spark如何划分stage？

###### 46、spark-submit时候如何引用外部的jar包？

###### 47、Spark中RDD有哪些特性？

###### 48、Spark的一个工作流程？

###### 49、Spark on yarn与standalone区别？

###### 50、Spark优化之内存管理？

###### 51、Spark优化之广播变量？

###### 52、Spark优化之数据本地性？

###### 53、Spark中task有几种类型？

