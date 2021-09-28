* [9.15、Doris](bigdata-project/src/main/doc/doris.md)
    - [1）、Doris数据模型？]()
    - [2）、Doris底层存储原理？]()
    - [3）、MPP引擎的选型？]()
    - [4）、Doris简介？]()
    - [5）、Doris适用场景？]()  
    - [6）、Doris的查询规划过程？]()  
    - [7）、Doris的Rollup？]()  
    - [8）、Doris的写入方式？]()  
    - [9）、Doris优缺点？]()  
    - [10）、Doris查询方式？]()  
    - [11）、Doris的调度？]()  
    - [12）、Doris的数据划分？]()  
    - [13）、Doris的Bitmap去重？]()  

---
###### [0）、Doris资料网址？]()
* [Doris GitHub地址](https://github.com/apache/incubator-doris/wiki)
* [Doris 官网地址](http://doris.apache.org/master/zh-CN/)

###### [1）、Doris数据模型？]()
    在 Doris 中，数据以表（Table）的形式进行逻辑上的描述。
    一张表包括行（Row）和列（Column）。Row 即用户的一行数据。Column 用于描述一行数据中不同的字段。
    Column 可以分为两大类：Key 和 Value。从业务角度看，Key 和 Value 可以分别对应维度列和指标列。
    
    Doris 的数据模型主要分为3类:
    Aggregate（聚合模型）
    Uniq（唯一主键）
    Duplicate（冗余模型）

    1.1、Aggregate（聚合模型）
    表中的列按照是否设置了 AggregationType，分为 Key (维度列) 和 Value（指标列）。
    没有设置 AggregationType 的，如 user_id、date、age ... 等称为 Key，而设置了 AggregationType 的称为 Value。
    当我们导入数据时，对于 Key 列相同的行会聚合成一行，而 Value 列会按照设置的 AggregationType 进行聚合。 
    AggregationType 目前有以下四种聚合方式：
    SUM：求和，多行的 Value 进行累加。
    REPLACE：替代，下一批数据中的 Value 会替换之前导入过的行中的 Value。
    MAX：保留最大值。
    MIN：保留最小值。
    
    1.2、Uniq（唯一主键）
    在某些多维分析场景下，用户更关注的是如何保证 Key 的唯一性，即如何获得 Primary Key 唯一性约束。
    这类数据没有聚合需求，只需保证主键唯一性。
    
    1.3、Duplicate（冗余模型）
    数据既没有主键，也没有聚合需求。
    这种数据模型区别于 Aggregate 和 Uniq 模型。数据完全按照导入文件中的数据进行存储，不会有任何聚合。即使两行数据完全相同，也都会保留。 

###### [2）、Doris底层存储原理？]()
    Doris是基于MPP架构的交互式SQL数据仓库，主要用于解决近实时的报表和多维分析。
    
    Doris分成两部分FE和BE，FE 负责存储以及维护集群元数据、接收、解析、查询、设计规划整体查询流程，BE 负责数据存储和具体的实施过程。
    
    在 Doris 的存储引擎中，用户数据被水平划分为若干个数据分片（Tablet，也称作数据分桶）。每个 Tablet 包含若干数据行。
    多个 Tablet 在逻辑上归属于不同的分区Partition。一个 Tablet 只属于一个 Partition。而一个 Partition 包含若干个 Tablet。
    Tablet 是数据移动、复制等操作的最小物理存储单元。

###### [3）、MPP引擎的选型？]()
    目前开源的比较受关注的OLAP引擎很多，比如Greenplum、Apache Impala、Presto、Doris、ClickHouse、Druid、TiDB等等


###### [4）、Doris简介？]()
    Apache Doris是一个现代化的MPP分析型数据库产品。仅需亚秒级响应时间即可获得查询结果，有效地支持实时数据分析。
    Apache Doris的分布式架构非常简洁，易于运维，并且可以支持10PB以上的超大数据集。
    Apache Doris可以满足多种数据分析需求，例如固定历史报表，实时数据分析，交互式数据分析和探索式数据分析等。
  
###### [5）、Doris适用场景？]()  
    应用场景包括：固定历史报表分析、实时数据分析、交互式数据分析等。
    
    一般情况下，用户的原始数据，比如日志或者在事务型数据库中的数据，经过流式系统或离线处理后，
    导入到Doris中以供上层的报表工具或者数据分析师查询使用。
  
###### [6）、Doris的查询规划过程？]()  

###### [7）、Doris的Rollup？]()  
    Rollup也就是上卷，是一种在多维分析中比较常用的操作——也就是从细粒度的数据向高层的聚合。
    
    在Doris中，我们提供了在聚合模型上的构建Rollup功能，将数据根据更少的维度进行预聚合。
    将本身在用户查询时才会进行聚合计算的数据预先计算好，并存储在Doris中，从而达到提升用户粗粒度上的查询效率。

###### [8）、Doris的写入方式？]()  


###### [9）、Doris优缺点？]()  

###### [10）、Doris查询方式？]()  

###### [11）、Doris的调度？]()  

###### [12）、Doris的数据划分？]()  



