# 图处理框架学习

### Spark GraphX
    简介：Apacha的基于RDD的分布式图计算框架
    开发者：GraphX最先是伯克利AMPLAB的一个分布式图计算框架项目，后来整合到Spark中成为一个核心组件
    主要语言：Scala
    项目构建工具：Maven
    是否开源：Yes
    社区：社区活跃
* [Spark GraphX官网介绍文档](http://spark.apache.org/docs/2.2.0/graphx-programming-guide.html#graph-operators)
* [Spark GraphX官网文档(中文)](http://spark.apachecn.org/docs/cn/2.2.0/graphx-programming-guide.html)
* [Spark GraphX官网API文档](http://spark.apache.org/docs/2.2.0/api/scala/index.html#org.apache.spark.graphx.util.GraphGenerators$)
* [Github地址](https://github.com/apache/spark/tree/master/graphx)

### GraphFrames
    简介：基于DataFrame的spark sql查询，可以与GraphX互操作
    开发者：Databricks和UC Berkeley及MIT一起为Apache Spark设计了一个图处理库
    主要语言：Scala
    项目构建工具：sbt
    是否开源：Yes
    社区：社区活跃
* [GraphFrames官网](http://graphframes.github.io/user-guide.html)
* [GraphFrames官网API文档](http://graphframes.github.io/api/scala/index.html#org.graphframes.package)
* [Github地址](https://github.com/graphframes/graphframes)

### GraphLab
    简介：由CMU在2009年开始的一个C++项目，一个面向大规模机器学习/图计算的分布式内存计算框架
    开发者：卡内基·梅隆大学
    主要语言：C++
    是否开源：Yes
    社区：社区活跃
* [GraphLab官网](https://turi.com/)
* [GraphLab官网API文档](https://turi.com/products/create/docs/)
* [Github地址](https://github.com/turi-code/GraphLab-Create-SDK)

### Giraph
    简介：Apache Giraph 是一个可伸缩的分布式迭代图处理系统，基于Hadoop的开源图计算框架
    开发者：最早出自雅虎。雅虎在开发Giraph时采用了Google工程师2010年发表的论文《Pregel：大规模图表处理系统》中的原理。 后来，雅虎将Giraph捐赠给Apache软件基金会
    主要语言：Java
    项目构建工具：Maven
    是否开源：Yes
    社区：社区活跃
* [GraphLab官网](http://giraph.apache.org/)
* [GraphLab官网API文档](http://giraph.apache.org/apidocs/index.html)
* [Github地址](https://github.com/apache/giraph)

### PowerGraph
    简介：分布式自然图并行计算框架,PowerGraph后来被集成到GraphLab后只是GraphLab一个主要的底层框架
    开发者：Carnegie Mellon University (卡内基·梅隆大学)
    主要语言：C++
    是否开源：Yes
    社区：一般
* [PowerGraph官网](http://giraph.apache.org/)
* [PowerGraph官网API文档](http://giraph.apache.org/apidocs/index.html)
* [Github地址](https://github.com/apache/giraph)

### GraphLite
    简介：同步图计算框架
    主要语言：Python、C++ 
    是否开源：Yes
    社区：一般
* [GraphLite官网](http://graphlite.readthedocs.io/en/latest)
* [GraphLite官网API文档](http://eugene-eeo.github.io/graphlite/api.html)
* [Github地址](https://github.com/eugene-eeo/graphlite)

### Hama
    简介： Hame是Google Pregel的开源实现，与Hadoop适合于分布式大数据处理不同，Hama主要用于分布式的矩阵、graph、网络算法的计算。 简单说，Hama是在HDFS上实现的BSP(Bulk Synchronous Parallel)计算框架，弥补Hadoop在计算能力上的不足
    开发者：Apache
    主要语言：Java
    项目构建工具：Maven
    是否开源：Yes
    社区：一般
* [Hama官网](https://hama.apache.org/)
* [Hama官网API文档](http://hama.apache.org/getting_started_with_hama.html)
* [Github地址](https://github.com/apache/hama)

### Pregel
    简介： Google图算法引擎。基于BSP模型实现的并行图处理系统
    开发者：google
    主要语言：C++
