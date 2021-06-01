* [9.2、Spark SQL]()
    - [1）、Spark SQL和Hive区别？Spark SQL一定比Hive快么？](#1spark-sqlhivespark-sqlhive)
    - [2）、Spark SQL有使用过么？在哪些项目中使用过？](#2spark-sql)
    - [3）、Spark SQL中UDF使用？](#3spark-sqludf)
    - [4）、SparkSession、SparkContext和SQLContext区别？](#4sparksessionsparkcontextsqlcontext)
    - [5）、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？](#5spark-sql)
    - [6）、Spark SQL程序调优？](#6spark-sql)
    - [7）、Spark SQL运行原理？](#7spark-sql)

---
###### 1、Spark SQL和Hive区别？Spark SQL一定比Hive快么？

###### 2、Spark SQL有使用过么？在哪些项目中使用过？

###### 3、Spark SQL中UDF使用？

###### 4、SparkSession、SparkContext和SQLContext区别？
    SparkContext:驱动程序使用与集群进行连接和通信，它可以帮助执行Spark任务，并与资源管理器(如YARN 或Mesos)进行协调。
    SQLContext:是通往SparkSQL的入口。有了SQLContext，就可以开始处理DataFrame、DataSet等
    HiveContext:是通往hive入口。HiveContext具有SQLContext的所有功能。
    SparkSession:是在Spark 2.0中引入的，替换了旧的SQLContext和HiveContext。

###### 5、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？

###### 6、Spark SQL程序调优？

###### 7、Spark SQL运行原理？
