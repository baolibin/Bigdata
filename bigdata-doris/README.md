
##### 1、Doris简介
    Doris（原百度 Palo）是一款基于大规模并行处理技术的分布式 SQL 数据库，由百度在2017年开源，2018年进入 Apache 孵化器。

    Doris 的主要特性
    1. 兼容 MySQL 协议，支持包括多表 Join、子查询、窗口函数、CTE 在内的丰富的 SQL 语法。支持诸多常见 BI 报表系统，能极大降低用
    户的学习和迁移成本。
    
    2. 支持高并发点查询和高吞吐的多维分析查询场景。通过分区裁剪、预聚合、谓词下推、向量化执行等技术，以及高效的列式存储引擎即数
    据压缩算法，满足不同业务场景下的延迟和吞吐需求。
    
    3. 特有的数据预聚合功能。支持预聚合表和基准表同步原子更新，为报表场景提供更快速的查询响应。
    
    4. 提供强大的扩展性和高可用特性。所有数据都采用多副本的方式保证数据的高可靠，同时提供全自动的副本选择、均衡和修复功能，为用
    户提供7*24小时的高可用数据库系统。
    
    5. 提供友好的在线表结构变更功能，能有效应对业务上的需求变化。
    
    6. 提供两级数据划分功能以及分层存储功能。用户可以更灵活地对数据进行管理和维护。
    
    Doris 在百度内部已应用于包括百度凤巢、百度统计等200多个业务线。最大单一业务数据量超过500 TB。同时在百度公有云和 toB 业务
    中也获得了高度认可。自开源以来，已有包括小米、美团、搜狐、新浪微博、瓜子、链家、上海绎维、零售魔方、量化派在内的十多家公司
    将 Doris 使用在生产环境中。

##### 2、Doris学习资料
* [Doris中文官网](http://doris.apache.org/master/zh-CN/)
* [Doris官网中文文档](http://doris.apache.org/master/zh-CN/installing/compilation.html)
* [Doris官网](http://doris.apache.org/)
* [Doris文档](http://doris.apache.org/documentation/cn/index.html)
* [GitHub代码-百度](https://github.com/baidu-doris/incubator-doris)
* [GitHub代码-Apache](https://github.com/apache/incubator-doris)
* [GitHub - wiki](https://github.com/apache/incubator-doris/wiki)


##### 2、Doris相关操作
* [Doris读写操作](src/main/java/README.md)


