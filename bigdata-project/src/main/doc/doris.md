* [9.15、Doris](bigdata-project/src/main/doc/doris.md)
    - [1）、Doris数据模型？]()
    - [2）、Doris底层存储原理？]()
    - [3）、MPP引擎的选型？]()
    - [4）、Doris简介？]()
    - [5）、Doris适用场景？]()  
    - [6）、Doris的查询流程？]()  
    - [7）、Doris的数据导入流程？]()  
    - [8）、Doris的写入方式？]()  
    - [9）、Doris优缺点？]()  
    - [10）、Doris提取方式？]()  
    - [11）、Doris的调度？]()  
    - [12）、Doris的数据划分？]()  
    - [13）、Doris的Bitmap索引？]()  
    - [14）、Doris、ClickHouse、Druid对比？]()  
    - [15）、Doris内置函数？]() 
    - [16）、MPP数据库？]() 
    - [17）、数据库架构设计？]() 
    - [18）、Doris近似去重、精确去重功能？]()
    - [19）、DorisDB基本概念？]()
    - [20）、Doris分区？]()
    - [21）、Doris分桶？]()
    - [22）、Doris的Bloom Filter索引？]()
    - [23）、Doris物化视图？]()
    - [24）、Doris系统架构？]()
    - [25）、Doris的Rollup？]()  
    - [26）、Doris用Bitmap实现精确去重？]()  
    - [27）、Doris用HLL(HyperLogLog)实现近似去重？]()  

---
###### [0）、Doris资料网址？]()
* [Doris GitHub地址](https://github.com/apache/incubator-doris/wiki)
* [Doris 官网地址](http://doris.apache.org/master/zh-CN/)
* [DorisDB企业版文档](http://doc.dorisdb.com)

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


###### [4）、Doris简介？历史背景？]()
    Doris是一个MPP的OLAP系统，主要整合了Google Mesa（数据模型），Apache Impala（MPP Query Engine)和Apache ORCFile (存储格式，编码和压缩) 的技术。
    Apache Doris是一个现代化的MPP分析型数据库产品。仅需亚秒级响应时间即可获得查询结果，有效地支持实时数据分析。
    Apache Doris的分布式架构非常简洁，易于运维，并且可以支持10PB以上的超大数据集。
    Apache Doris可以满足多种数据分析需求，例如固定历史报表，实时数据分析，交互式数据分析和探索式数据分析等。
  
###### [5）、Doris适用场景？]()  
    应用场景包括：固定历史报表分析、实时数据分析、交互式数据分析等。
    一般情况下，用户的原始数据，比如日志或者在事务型数据库中的数据，经过流式系统或离线处理后，
    导入到Doris中以供上层的报表工具或者数据分析师查询使用。
    
    DorisDB可以满足企业级用户的多种分析需求，包括OLAP多维分析，定制报表，实时数据分析，Ad-hoc数据分析等。具体的业务场景包括：
    数据仓库建设
    OLAP/BI分析
    用户行为分析
    广告数据分析
    系统监控分析
    探针分析 APM（Application Performance Management）

###### [6）、Doris的查询流程？]() 
    用户可使用MySQL客户端连接FE，执行SQL查询， 获得结果。
    查询流程如下：
    ① MySQL客户端执行DQL SQL命令。
    ② FE解析, 分析, 改写, 优化和规划, 生成分布式执行计划。
    ③ 分布式执行计划由 若干个可在单台be上执行的plan fragment构成， FE执行exec_plan_fragment, 将plan fragment分发给BE，并指定其中一台BE为coordinator。
    ④ BE执行本地计算, 比如扫描数据。
    ⑤ 其他BE调用transimit_data将中间结果发送给BE coordinator节点汇总为最终结果。
    ⑥ FE调用fetch_data获取最终结果。
    ⑦ FE将最终结果发送给MySQL client。
    执行计划在BE上的实际执行过程比较复杂, 采用向量化执行方式，比如一个算子产生4096个结果，输出到下一个算子参与计算，而非batch方式或者one-tuple-at-a-time。


###### [7）、Doris的数据导入流程？]()  
    数据导入的流程如下:
    ① 用户选择一台BE作为协调者, 发起数据导入请求, 传入数据格式, 数据源和标识此次数据导入的label, label用于避免数据重复导入. 用户也可以向FE发起请求, FE会把请求重定向给BE.
    ② BE收到请求后, 向FE master节点上报, 执行loadTxnBegin, 创建全局事务。 因为导入过程中, 需要同时更新base表和物化索引的多个bucket, 为了保证数据导入的一致性, 用事务控制本次导入的原子性.
    ③ BE创建事务成功后, 执行streamLoadPut调用, 从FE获得本次数据导入的计划. 数据导入, 可以看成是将数据分发到所涉及的全部的tablet副本上, BE从FE获取的导入计划包含数据的schema信息和tablet副本信息.
    ④ BE从数据源拉取数据, 根据base表和物化索引表的schema信息, 构造内部数据格式.
    ⑤ BE根据分区分桶的规则和副本位置信息, 将发往同一个BE的数据, 批量打包, 发送给BE, BE收到数据后, 将数据写入到对应的tablet副本中.
    ⑥ 当BE coordinator节点完成此次数据导入, 向FE master节点执行loadTxnCommit, 提交全局事务, 发送本次数据导入的 执行情况, FE master确认所有涉及的tablet的多数副本都成功完成, 则发布本次数据导入使数据对外可见, 否则, 导入失败, 数据不可见, 后台负责清理掉不一致的数据.

###### [8）、Doris的写入方式？]()  
    用户创建表之后, 导入数据填充表.
    ① 支持导入数据源有: 本地文件, HDFS, Kafka和S3.
    ② 支持导入方式有: 批量导入, 流式导入, 实时导入.
    ③ 支持的数据格式有: CSV, Parquet, ORC等.
    ④ 导入发起方式有: 用RESTful接口, 执行SQL命令.
    
    Broker Load通过部署的Broker程序，DorisDB可读取对应数据源（如HDFS, S3）上的数据，利用自身的计算资源对数据进行预处理和导入。
    这是一种异步的导入方式，用户需要通过MySQL协议创建导入，并通过查看导入命令检查导入结果。
    
    Spark Load 通过外部的 Spark 资源实现对导入数据的预处理，提高 DorisDB 大数据量的导入性能并且节省 Doris 集群的计算资源。
    主要用于初次迁移、大数据量导入 DorisDB 的场景（数据量可到TB级别）。
    Spark Load 是一种异步导入方式，用户需要通过 MySQL 协议创建 Spark 类型导入任务，并可以通过 SHOW LOAD 查看导入结果。
    
    Stream Load 是一种同步的导入方式，用户通过发送 HTTP 请求将本地文件或数据流导入到 DorisDB 中。Stream Load 同步执行导入并返回导入结果。
    用户可直接通过请求的返回值判断导入是否成功。DorisDB支持从本地直接导入数据，支持CSV文件格式。数据量在10GB以下。
    
    Routine Load 是一种例行导入方式，DorisDB通过这种方式支持从Kafka持续不断的导入数据，并且支持通过SQL控制导入任务的暂停、重启、停止。
    
    Insert Into 语句的使用方式和 MySQL 等数据库中 Insert Into 语句的使用方式类似。但在 DorisDB 中，所有的数据写入都是一个独立的导入作业。
    
    http://doc.dorisdb.com/2145999


###### [9）、Doris优缺点？]()  

###### [10）、Doris提取方式？]()  
    1）、Export数据导出：
    数据导出（Export）是 DorisDB 提供的一种将数据导出并存储到其他介质上的功能。
    该功能可以将用户指定的表或分区的数据，以文本的格式，通过 Broker 进程导出到远端存储上，如 HDFS/阿里云OSS/AWS S3（或者兼容S3协议的对象存储） 等。
    
    2）、Spark-connector：
    Spark DorisDB Connector 可以支持通过 Spark 读取 DorisDB 中存储的数据。
    可以将DorisDB表映射为DataFrame或者RDD，推荐使用DataFrame。
    支持在DorisDB端完成数据过滤，减少数据传输量。

###### [11）、Doris的调度？]()  

###### [12）、Doris的数据划分？]()  



###### [13）、Doris的Bitmap索引？]()  
    DorisDB 支持基于Bitmap索引，对于有Filter的查询有明显的加速效果。
    Bitmap是元素为1个bit的, 取值为0,1两种情形的, 可对某一位bit进行置位(set)和清零(clear)操作的数组. Bitmap的使用场景有:
        用两个long型表示16学生的性别, 0表示女生, 1表示男生.
        用bitmap表示一组数据中是否存在null值, 0表示元素不为null, 1表示为null.
        一组数据的取值为(Q1, Q2, Q3, Q4), 表示季度; 用bitmap表示这组数据中取值为Q4的元素; 1表示取值为Q4的元素, 0表示其他取值的元素.
    
    Raw Data：
    【Platform】
    Android
    Android
    Android
    IOS
    
    Dictionary：
    【value、Id】
    Android、0
    IOS、1
    
    Bitmap Index:
    【Id、Bitmap】
    0、0111
    1、1000
    
    Bitmap只能表示取值为两种情形的列数组, 当列的取值为多种取值情形枚举类型时, 例如季度(Q1, Q2, Q3, Q4),  系统平台(Linux, Windows, FreeBSD, MacOS), 
    则无法用一个Bitmap编码; 此时可以为每个取值各自建立一个Bitmap的来表示这组数据; 同时为实际枚举取值建立词典.

    如上图所示，Platform列有4行数据，可能的取值有Android、Ios。DorisDB中会首先针对Platform列构建一个字典，将Android和Ios映射为int，
    然后就可以对Android和Ios分别构建Bitmap。具体来说，我们分别将Android、Ios 编码为0和1，因为Android出现在第1，2，3行，
    所以Bitmap是0111，因为Ios出现在第4行，所以Bitmap是1000。

    假如有一个针对包含该Platform列的表的SQL查询，select xxx from table where Platform = iOS，DorisDB会首先查找字典，找出iOS对于的编码值是1，
    然后再去查找 Bitmap Index，知道1对应的Bitmap是1000，我们就知道只有第4行数据符合查询条件，DorisDB就会只读取第4行数据，不会读取所有数据。
    
###### [14）、Doris、ClickHouse、Druid对比？]()  
    Doris的前身是百度的Palo，后贡献给apache社区，相应的商业版本是DorisDB。其官方的性能测试也是对标的ClickHouse。
    
    存储方式：
        Apache Doris：shard nothing，本地磁盘存储
        Clickhouse：列式，本地磁盘存储
    
    编程语言：
        Apache Doris：C++、Java
        Clickhouse：C++
    
    核心原理：
        Apache Doris：MPP现场计算，向量化执行
        Clickhouse：MPP现场计算，向量化执行
    
    数据库完整度：
        Apache Doris：完整的DBMS，支持DDL、DML
        Clickhouse：完整的DBMS，支持DDL、DML
        
    运维成本：
        Apache Doris：不依赖hadoop生态，schema支持修改
        Clickhouse：不依赖hadoop生态，schema支持修改

###### [15）、Doris内置函数？]() 
    数学函数
    位操作函数
    类型转换函数
    日期和时间函数
    条件函数
    字符串函数
    聚合函数
    json解析函数
    HLL函数
    分析函数（窗口函数）

###### [16）、MPP数据库？]() 
    存储和计算都是有MPP数据库来提供的，有代表性的GREENPLUM、CLICKHOUSE、DORIS等。
    
    MPP (Massively Parallel Processing)，大规模并行处理系统，由许多松耦合的处理单元组成的，
    每个单元内的 CPU都有自己私有的资源，如总线，内存，硬盘等。
    
###### [17）、数据库架构设计？]() 
    数据库构架设计中主要有Shared Everthting、Shared Nothing、和Shared Disk。
    
    Shared Everthting:一般是针对单个主机，完全透明共享CPU/MEMORY/IO，并行处理能力是最差的，典型的代表SQLServer。
    Shared Disk：各个处理单元使用自己的私有 CPU和Memory，共享磁盘系统。典型的代表Oracle Rac。
    Shared Nothing：各个处理单元都有自己私有的CPU/内存/硬盘等，不存在共享资源，类似于MPP（大规模并行处理）模式，
                    各处理单元之间通过协议通信，并行处理和扩展能力更好。

###### [18）、Doris近似去重、精确去重功能？]()



###### [19）、DorisDB基本概念？]()
    FE：FrontEnd DorisDB的前端节点，负责管理元数据，管理客户端连接，进行查询规划，查询调度等工作。
    BE：BackEnd DorisDB的后端节点，负责数据存储，计算执行，以及compaction，副本管理等工作。
    Broker：DorisDB中和外部HDFS/对象存储等外部数据对接的中转服务，辅助提供导入导出功能。
    DorisManager：DorisDB 管理工具，提供DorisDB集群管理、在线查询、故障查询、监控报警的可视化工具。
    Tablet：DorisDB 表的逻辑分片，也是DorisDB中副本管理的基本单位，每个表根据分区和分桶机制被划分成多个Tablet存储在不同BE节点上。

###### [20）、Doris分区？]()



###### [21）、Doris分桶？]()



###### [22）、Doris的Bloom Filter索引？]()
    Bloom Filter（布隆过滤器）是用于判断某个元素是否在一个集合中的数据结构，优点是空间效率和时间效率都比较高，缺点是有一定的误判率。

    布隆过滤器是由一个Bit数组和n个哈希函数构成。Bit数组初始全部为0，当插入一个元素时，n个Hash函数对元素进行计算, 得到n个slot，
    然后将Bit数组中n个slot的Bit置1。

    DorisDB的建表时, 可通过PROPERTIES{"bloom_filter_columns"="c1,c2,c3"}指定需要建BloomFilter索引的列，查询时, 
    BloomFilter可快速判断某个列中是否存在某个值。如果Bloom Filter判定该列中不存在指定的值，就不需要读取数据文件；如果是全1情形，
    此时需要读取数据块确认目标值是否存在。另外，Bloom Filter索引无法确定具体是哪一行数据具有该指定的值。

###### [23）、Doris物化视图？]()


###### [24）、Doris系统架构？]()




###### [25）、Doris的Rollup？]()  
    Rollup也就是上卷，是一种在多维分析中比较常用的操作——也就是从细粒度的数据向高层的聚合。
    
    在Doris中，我们提供了在聚合模型上的构建Rollup功能，将数据根据更少的维度进行预聚合。
    将本身在用户查询时才会进行聚合计算的数据预先计算好，并存储在Doris中，从而达到提升用户粗粒度上的查询效率。

###### [26）、Doris用Bitmap实现精确去重？]()  



###### [27）、Doris用HLL(HyperLogLog)实现近似去重？]()  
    HyperLogLog（简称 HLL）是一种近似去重算法，它的特点是具有非常优异的空间复杂度O(mloglogn),  时间复杂度为O(n),
    并且计算结果的误差可控制在1%—10%左右，误差与数据集大小以及所采用的哈希函数有关。
    
    HyperLogLog是一种近似的去重算法，能够使用极少的存储空间计算一个数据集的不重复元素的个数。
    HLL类型是基于HyperLogLog算法的工程实现。用于保存HyperLogLog计算过程的中间结果，它只能作为数据表的指标列类型。
    
    如何使用HyperLogLog？
    1）、使用HyperLogLog去重, 需要在建表语句中, 将目标的指标列的类型设置为HLL,  聚合函数设置为HLL_UNION.
    2）、目前, 只有聚合表支持HLL类型的指标列.
    3）、当在HLL类型列上使用count distinct时，DorisDB会自动转化为HLL_UNION_AGG计算。
    
    Bitmap和HLL应该如何选择？如果数据集的基数在百万、千万量级，并拥有几十台机器，那么直接使用 count distinct 即可。
    如果基数在亿级以上，并且需要精确去重，那么只能用Bitmap类型；如果可以接受近似去重，那么还可以使用HLL类型。

###### [28）、Doris的Colocation Join？]()  




###### [29）、Doris的窗口函数？]()  




