* [9.15、Doris]()
    - [1）、Doris数据模型？](#1、Doris数据模型？)
    - [2）、Doris底层存储原理？](#2、Doris底层存储结构？)
    - [3）、MPP引擎的选型？](#3、MPP引擎的选型？)
    - [4）、Doris简介？](#4、Doris简介？历史背景？)
    - [5）、Doris适用场景？](#5、Doris适用场景？)  
    - [6）、Doris的查询流程？](#6、Doris的查询流程？)  
    - [7）、Doris的数据导入流程？](#7、Doris的数据导入流程？)  
    - [8）、Doris的写入方式？](#8、Doris的写入方式？)  
    - [9）、Doris优缺点？]()  
    - [10）、Doris提取方式？]()  
    - [11）、Doris的调度？]()  
    - [12）、Doris的数据分布？]()  
    - [13）、Doris的Bitmap索引？]()  
    - [14）、Doris、ClickHouse、Druid对比？]()  
    - [15）、Doris内置函数？]() 
    - [16）、MPP数据库？]() 
    - [17）、数据库架构设计？]() 
    - [18）、Doris近似去重、精确去重功能？]()
    - [19）、Doris基本概念？]()
    - [20）、Doris分区？]()
    - [21）、Doris分桶？]()
    - [22）、Doris的Bloom Filter索引？]()
    - [23）、Doris物化视图？]()
    - [24）、Doris系统架构？]()
    - [25）、Doris的Rollup？]()  
    - [26）、Doris用Bitmap实现精确去重？]()  
    - [27）、Doris用HLL(HyperLogLog)实现近似去重？]()  
    - [28）、Doris的Colocation Join？]()  
    - [29）、Doris的窗口函数？]()  
    - [30）、Doris精确去重方式？]()  
    - [31）、Doris传统去重计算？]()  
    - [32）、Doris的CBO优化器？]()  
    - [33）、Spark Doris Connector？]() 
    - [34）、Doris数据类型？]() 
    - [35）、Doris监控报警？]() 
    - [36）、Doris用户权限？]() 
    - [37）、Doris分区缓存？]() 
    - [38）、Doris动态分区？]() 
    - [39）、Doris物化视图 VS Rollup？]() 
    - [40）、Doris的Join有哪些？]() 
    - [41）、Doris的Broker？]()  
    - [42）、Doris的Binlog Load？]()  
    - [43）、Doris的Broker Load？]()  
    - [44）、Doris的Routine Load？]()  
    - [45）、Doris的Load Json Format Data？]() 
    - [46）、Doris的查询性能？]() 
    - [47）、Doris的分区与分桶？]()  
    - [48）、Doris的Runtime Filter？]()  
    - [49）、Doris监控与报警？]()  
    - [50）、Doris支持的索引？]()  
    - [51）、Doris如何确定分桶数量？]()
    - [52）、Doris结点增加？]()  
    - [53）、Doris位图实现原理？]()
    - [54）、bitmap常见实现？]()

---
###### [0）、Doris资料网址？]()
* [Doris GitHub地址](https://github.com/apache/incubator-doris/wiki)
* [Doris 官网地址](https://doris.apache.org/zh-CN/installing/compilation.html)
* [DorisDB企业版文档](http://doc.dorisdb.com)

###### 1、Doris数据模型？
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
    
    数据模型的选择建议
    Aggregate 模型可以通过预聚合，极大地降低聚合查询时所需扫描的数据量和查询的计算量，非常适合有固定模式的报表类查询场景。但是该模型对 count(*) 查询很不友好。同时因为固定了 Value 列上的聚合  方式，在进行其他类型的聚合查询时，需要考虑语意正确性。
    Uniq 模型针对需要唯一主键约束的场景，可以保证主键唯一性约束。但是无法利用 ROLLUP 等预聚合带来的查询优势（因为本质是 REPLACE，没有 SUM 这种聚合方式）。
    Duplicate 适合任意维度的 Ad-hoc 查询。虽然同样无法利用预聚合的特性，但是不受聚合模型的约束，可以发挥列存模型的优势（只读取相关列，而不需要读取所有 Key 列）。

###### 2、Doris底层存储结构？
    Doris是基于MPP架构的交互式SQL数据仓库，主要用于解决近实时的报表和多维分析。
    Doris分成两部分FE和BE，FE 负责存储以及维护集群元数据、接收、解析、查询、设计规划整体查询流程，BE 负责数据存储和具体的实施过程。
    
    在 Doris 中，数据都以表（Table）的形式进行逻辑上的描述。
    一张表包括行（Row）和列（Column）。Row 即用户的一行数据。Column 用于描述一行数据中不同的字段。
    Column 可以分为两大类：Key 和 Value。从业务角度看，Key 和 Value 可以分别对应维度列和指标列。从聚合模型的角度来说，Key 列相同的行，会聚合成一行。其中 Value 列的聚合方式由用户在建表时指定。
    
    在 Doris 的存储引擎中，用户数据被水平划分为若干个数据分片（Tablet，也称作数据分桶）。每个 Tablet 包含若干数据行。各个 Tablet 之间的数据没有交集，并且在物理上是独立存储的。
    多个 Tablet 在逻辑上归属于不同的分区（Partition）。一个 Tablet 只属于一个 Partition。而一个 Partition 包含若干个 Tablet。
    因为 Tablet 在物理上是独立存储的，所以可以视为 Partition 在物理上也是独立。Tablet 是数据移动、复制等操作的最小物理存储单元。
    若干个 Partition 组成一个 Table。Partition 可以视为是逻辑上最小的管理单元。数据的导入与删除，都可以或仅能针对一个 Partition 进行。

###### 3、MPP引擎的选型？
    目前开源的比较受关注的OLAP引擎很多，比如Greenplum、Apache Impala、Presto、Doris、ClickHouse、Druid、TiDB等等
    
    Druid：是一个实时处理时序数据的OLAP数据库，索引首先按照时间分片，查询时候也是按照时间线去路由索引。
    Apache Impala：基于内存计算，速度快，支持的数据源没有Presto多。
    Presto：没有使用MapReduce，大部分情况比Hive快一个数量级，其中关键是所有处理都在内存中完成。
    Kylin：核心是cube，cude是一种预计算技术，基本思路预先对数据做多维索引，查询时候只扫描索引而不访问原始数据从而提速。
    SparkSQL：基于Spark平台上的一个OLAP框架，基本思路是增加机器来并行计算，从而提高查询效率。

    Druid同kylin一样，是采用预计算的方式。主要解决的是对于大量的基于时序的数据进行聚合查询。数据可以实时摄入，进入到Druid后立即可查，
    同时数据是几乎是不可变。通常是基于时序的事实事件，事实发生后进入Druid，外部系统就可以对该事实进行查询。
    Druid是一个用于大数据实时查询和分析的高容错、高性能开源分布式系统，用于解决如何在大规模数据集下进行快速的、交互式的查询和分析。
    
    Apache kylin是一个开源分布式分析引擎、提供Hadoop、Spark之上的SQL查询接口及多维分析（OLAP）能力，可以在亚秒内查询巨大的Hive表。
    kylin是一种OLAP数据引擎，支持大数据生态圈的数据分析业务，主要是通过预计算的方式将用户设定的多维度数据立方体(cube)缓存起来，
    达到快速查询的目的。应用场景应该是针对复杂sql join后的数据缓存。
    
    impala是Cloudera开发开源的，Impala是Cloudera开发并开源的，能查询存储在HDFS和HBase中的数据。
    同Hive一样，也是一种SQL on Hadoop解决方案。但Impala抛弃了MapReduce,使用更类似于传统的MPP数据库技术来提高查询速度。

    presto是Facebook开源的大数据查询引擎，为了解决hive查询慢产生。使用java编写，数据全部在内存中处理。
    
    Spark SQL基于spark平台上的一个olap框架，本质上也是基于DAG的MPP， 基本思路是增加机器来并行计算，从而提高查询速度。
    Spark SQL应该还是算做Batching Processing, 中间计算结果需要落地到磁盘，所以查询效率没有MPP架构的引擎（如Impala）高。

    Clickhouse是一个用于在线分析处理（OLAP）的列式数据库管理系统（DBMS）。
    是由俄罗斯的Yandex公司为了Yandex Metrica网络分析服务而开发。它支持分析实时更新的数据，Clickhouse以高性能著称。

    Greenplum是一种基于PostgreSQL的分布式数据库。其采用shared nothing架构（MPP），主机，操作系统，内存，存储都是自我控制的，
    不存在共享。也就是每个节点都是一个单独的数据库。节点之间的信息交互是通过节点互联网络实现。
    通过将数据分布到多个节点上来实现规模数据的存储，通过并行查询处理来提高查询性能。

###### 4、Doris简介？历史背景？
    Doris是一个MPP的OLAP系统，主要整合了Google Mesa（数据模型），Apache Impala（MPP Query Engine)和Apache ORCFile (存储格式，编码和压缩) 的技术。
    Apache Doris是一个现代化的MPP分析型数据库产品。仅需亚秒级响应时间即可获得查询结果，有效地支持实时数据分析。
    Apache Doris的分布式架构非常简洁，易于运维，并且可以支持10PB以上的超大数据集。
    Apache Doris可以满足多种数据分析需求，例如固定历史报表，实时数据分析，交互式数据分析和探索式数据分析等。
  
###### 5、Doris适用场景？
    应用场景包括：固定历史报表分析、实时数据分析、交互式数据分析等。
    一般情况下，用户的原始数据，比如日志或者在事务型数据库中的数据，经过流式系统或离线处理后，
    导入到Doris中以供上层的报表工具或者数据分析师查询使用。

###### 6、Doris的查询流程？
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

###### 7、Doris的数据导入流程？
    数据导入的流程如下:
    ① 用户选择一台BE作为协调者, 发起数据导入请求, 传入数据格式, 数据源和标识此次数据导入的label, label用于避免数据重复导入. 用户也可以向FE发起请求, FE会把请求重定向给BE.
    ② BE收到请求后, 向FE master节点上报, 执行loadTxnBegin, 创建全局事务。 因为导入过程中, 需要同时更新base表和物化索引的多个bucket, 为了保证数据导入的一致性, 用事务控制本次导入的原子性.
    ③ BE创建事务成功后, 执行streamLoadPut调用, 从FE获得本次数据导入的计划. 数据导入, 可以看成是将数据分发到所涉及的全部的tablet副本上, BE从FE获取的导入计划包含数据的schema信息和tablet副本信息.
    ④ BE从数据源拉取数据, 根据base表和物化索引表的schema信息, 构造内部数据格式.
    ⑤ BE根据分区分桶的规则和副本位置信息, 将发往同一个BE的数据, 批量打包, 发送给BE, BE收到数据后, 将数据写入到对应的tablet副本中.
    ⑥ 当BE coordinator节点完成此次数据导入, 向FE master节点执行loadTxnCommit, 提交全局事务, 发送本次数据导入的 执行情况, FE master确认所有涉及的tablet的多数副本都成功完成, 则发布本次数据导入使数据对外可见, 否则, 导入失败, 数据不可见, 后台负责清理掉不一致的数据.

###### 8、Doris的写入方式？
    用户创建表之后, 导入数据填充表.
    ① 支持导入数据源有: 本地文件, HDFS, Kafka和S3.
    ② 支持导入方式有: 批量导入, 流式导入, 实时导入.
    ③ 支持的数据格式有: CSV, Parquet, ORC等.
    ④ 导入发起方式有: 用RESTful接口, 执行SQL命令.
    
    为适配不同的数据导入需求，Doris 系统提供了6种不同的导入方式。每种导入方式支持不同的数据源，存在不同的使用方式（异步，同步）。
    所有导入方式都支持 csv 数据格式。其中 Broker load 还支持 parquet 和 orc 数据格式。
    每个导入方式的说明请参阅单个导入方式的操作手册。

    1、Broker load
    通过 Broker 进程访问并读取外部数据源（如 HDFS）导入到 Doris。用户通过 Mysql 协议提交导入作业后，异步执行。通过 SHOW LOAD 命令查看导入结果。
    2、Stream load
    用户通过 HTTP 协议提交请求并携带原始数据创建导入。主要用于快速将本地文件或数据流中的数据导入到 Doris。导入命令同步返回导入结果。
    3、Insert
    类似 MySQL 中的 Insert 语句，Doris 提供 INSERT INTO tbl SELECT ...; 的方式从 Doris 的表中读取数据并导入到另一张表。或者通过 INSERT INTO tbl VALUES(...); 插入单条数据。
    4、Multi load
    用户通过 HTTP 协议提交多个导入作业。Multi Load 可以保证多个导入作业的原子生效。
    5、Routine load
    用户通过 MySQL 协议提交例行导入作业，生成一个常驻线程，不间断的从数据源（如 Kafka）中读取数据并导入到 Doris 中。
    6、通过S3协议直接导入
    用户通过S3协议直接导入数据，用法和Broker Load 类似

###### 9、Doris优缺点？

###### 10、Doris提取方式？
    1）、Export数据导出：
    数据导出（Export）是 Doris 提供的一种将数据导出并存储到其他介质上的功能。
    该功能可以将用户指定的表或分区的数据，以文本的格式，通过 Broker 进程导出到远端存储上，如 HDFS/阿里云OSS/AWS S3（或者兼容S3协议的对象存储） 等。
    
    2）、Spark-connector：
    Spark Doris Connector 可以支持通过 Spark 读取 Doris 中存储的数据。
    可以将Doris表映射为DataFrame或者RDD，推荐使用DataFrame。
    支持在Doris端完成数据过滤，减少数据传输量。

###### 11、Doris的调度？

###### 12、Doris的数据分布？
    数据分布方式：Doris使用先分区后分桶的方式, 可灵活地支持支持二种分布方式:
    1)、Hash分布:  不采用分区方式, 整个table作为一个分区, 指定分桶的数量.
    2)、Range-Hash的组合数据分布: 即指定分区数量, 指定每个分区的分桶数量.

###### 13、Doris的Bitmap索引？ 
    Doris 支持基于Bitmap索引，对于有Filter的查询有明显的加速效果。
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

    如上图所示，Platform列有4行数据，可能的取值有Android、Ios。Doris中会首先针对Platform列构建一个字典，将Android和Ios映射为int，
    然后就可以对Android和Ios分别构建Bitmap。具体来说，我们分别将Android、Ios 编码为0和1，因为Android出现在第1，2，3行，
    所以Bitmap是0111，因为Ios出现在第4行，所以Bitmap是1000。

    假如有一个针对包含该Platform列的表的SQL查询，select xxx from table where Platform = iOS，Doris会首先查找字典，找出iOS对于的编码值是1，
    然后再去查找 Bitmap Index，知道1对应的Bitmap是1000，我们就知道只有第4行数据符合查询条件，Doris就会只读取第4行数据，不会读取所有数据。
    
###### 14、Doris、ClickHouse、Druid对比？
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

###### 15、Doris内置函数？
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

###### 16、MPP数据库？
    存储和计算都是有MPP数据库来提供的，有代表性的GREENPLUM、CLICKHOUSE、DORIS等。
    
    MPP (Massively Parallel Processing)，大规模并行处理系统，由许多松耦合的处理单元组成的，
    每个单元内的 CPU都有自己私有的资源，如总线，内存，硬盘等。
    
###### 17、数据库架构设计？
    数据库构架设计中主要有Shared Everthting、Shared Nothing、和Shared Disk。
    
    Shared Everthting:一般是针对单个主机，完全透明共享CPU/MEMORY/IO，并行处理能力是最差的，典型的代表SQLServer。
    Shared Disk：各个处理单元使用自己的私有 CPU和Memory，共享磁盘系统。典型的代表Oracle Rac。
    Shared Nothing：各个处理单元都有自己私有的CPU/内存/硬盘等，不存在共享资源，类似于MPP（大规模并行处理）模式，
                    各处理单元之间通过协议通信，并行处理和扩展能力更好。

###### 18、Doris的Lateral Join？
    行列转化是ETL处理过程中常见的操作，Lateral 一个特殊的Join关键字，能够按照每行和内部的子查询或者table function关联，
    通过Lateral 与unnest配合，我们可以实现一行转多行的功能。

###### 19、Doris基本概念？
    FE：FrontEnd Doris的前端节点，负责管理元数据，管理客户端连接，进行查询规划，查询调度等工作。
    BE：BackEnd Doris的后端节点，负责数据存储，计算执行，以及compaction，副本管理等工作。
    Broker：Doris中和外部HDFS/对象存储等外部数据对接的中转服务，辅助提供导入导出功能。
    Tablet：Doris 表的逻辑分片，也是Doris中副本管理的基本单位，每个表根据分区和分桶机制被划分成多个Tablet存储在不同BE节点上。

###### 20、Doris分区？
    Doris 支持两层的数据划分。第一层是 Partition，支持 Range 和 List 的划分方式。第二层是 Bucket（Tablet），仅支持 Hash 的划分方式。
    1、Range 分区
    分区列通常为时间列，以方便的管理新旧数据。
    Partition 支持通过 VALUES LESS THAN (...) 仅指定上界，系统会将前一个分区的上界作为该分区的下界，生成一个左闭右开的区间。通过，也支持通过 VALUES [...) 指定同时指定上下界，生成一个左闭右开的区间。
    p201701: [MIN_VALUE,  2017-02-01)
    p201702: [2017-02-01, 2017-03-01)
    p201703: [2017-03-01, 2017-04-01)
    
    -- Range Partition
    CREATE TABLE IF NOT EXISTS example_db.expamle_range_tbl
    (
        `user_id` LARGEINT NOT NULL COMMENT "用户id",
        `date` DATE NOT NULL COMMENT "数据灌入日期时间",
        `timestamp` DATETIME NOT NULL COMMENT "数据灌入的时间戳",
        `city` VARCHAR(20) COMMENT "用户所在城市",
        `age` SMALLINT COMMENT "用户年龄",
        `sex` TINYINT COMMENT "用户性别",
        `last_visit_date` DATETIME REPLACE DEFAULT "1970-01-01 00:00:00" COMMENT "用户最后一次访问时间",
        `cost` BIGINT SUM DEFAULT "0" COMMENT "用户总消费",
        `max_dwell_time` INT MAX DEFAULT "0" COMMENT "用户最大停留时间",
        `min_dwell_time` INT MIN DEFAULT "99999" COMMENT "用户最小停留时间"
    )
    ENGINE=olap
    AGGREGATE KEY(`user_id`, `date`, `timestamp`, `city`, `age`, `sex`)
    PARTITION BY RANGE(`date`)
    (
        PARTITION `p201701` VALUES LESS THAN ("2017-02-01"),
        PARTITION `p201702` VALUES LESS THAN ("2017-03-01"),
        PARTITION `p201703` VALUES LESS THAN ("2017-04-01")
    )
    DISTRIBUTED BY HASH(`user_id`) BUCKETS 16
    PROPERTIES
    (
        "replication_num" = "3",
        "storage_medium" = "SSD",
        "storage_cooldown_time" = "2018-01-01 12:00:00"
    );
    
    2、List 分区
    分区列支持 BOOLEAN, TINYINT, SMALLINT, INT, BIGINT, LARGEINT, DATE, DATETIME, CHAR, VARCHAR 数据类型，分区值为枚举值。只有当数据为目标分区枚举值其中之一时，才可以命中分区。
    Partition 支持通过 VALUES IN (...) 来指定每个分区包含的枚举值。
    p_cn: ("Beijing", "Shanghai", "Hong Kong")
    p_usa: ("New York", "San Francisco")
    p_jp: ("Tokyo")
    
    -- List Partition
    CREATE TABLE IF NOT EXISTS example_db.expamle_list_tbl
    (
        `user_id` LARGEINT NOT NULL COMMENT "用户id",
        `date` DATE NOT NULL COMMENT "数据灌入日期时间",
        `timestamp` DATETIME NOT NULL COMMENT "数据灌入的时间戳",
        `city` VARCHAR(20) COMMENT "用户所在城市",
        `age` SMALLINT COMMENT "用户年龄",
        `sex` TINYINT COMMENT "用户性别",
        `last_visit_date` DATETIME REPLACE DEFAULT "1970-01-01 00:00:00" COMMENT "用户最后一次访问时间",
        `cost` BIGINT SUM DEFAULT "0" COMMENT "用户总消费",
        `max_dwell_time` INT MAX DEFAULT "0" COMMENT "用户最大停留时间",
        `min_dwell_time` INT MIN DEFAULT "99999" COMMENT "用户最小停留时间"
    )
    ENGINE=olap
    AGGREGATE KEY(`user_id`, `date`, `timestamp`, `city`, `age`, `sex`)
    PARTITION BY LIST(`city`)
    (
        PARTITION `p_cn` VALUES IN ("Beijing", "Shanghai", "Hong Kong"),
        PARTITION `p_usa` VALUES IN ("New York", "San Francisco"),
        PARTITION `p_jp` VALUES IN ("Tokyo")
    )
    DISTRIBUTED BY HASH(`user_id`) BUCKETS 16
    PROPERTIES
    (
        "replication_num" = "3",
        "storage_medium" = "SSD",
        "storage_cooldown_time" = "2018-01-01 12:00:00"
    );

###### 21、Doris分桶？
    在 Doris 的存储引擎中，用户数据被水平划分为若干个数据分片（Tablet，也称作数据分桶）。每个 Tablet 包含若干数据行。各个 Tablet 之间的数据没有交集，并且在物理上是独立存储的。
    多个 Tablet 在逻辑上归属于不同的分区（Partition）。一个 Tablet 只属于一个 Partition。而一个 Partition 包含若干个 Tablet。因为 Tablet 在物理上是独立存储的，所以可以视为 Partition 在物理上也是独立。Tablet 是数据移动、复制等操作的最小物理存储单元。
    若干个 Partition 组成一个 Table。Partition 可以视为是逻辑上最小的管理单元。数据的导入与删除，都可以或仅能针对一个 Partition 进行。

    Bucket
    如果使用了 Partition，则 DISTRIBUTED ... 语句描述的是数据在各个分区内的划分规则。如果不使用 Partition，则描述的是对整个表的数据的划分规则。

    分桶列可以是多列，但必须为 Key 列。分桶列可以和 Partition 列相同或不同。
    分桶列的选择，是在 查询吞吐 和 查询并发 之间的一种权衡：
    如果选择多个分桶列，则数据分布更均匀。如果一个查询条件不包含所有分桶列的等值条件，那么该查询会触发所有分桶同时扫描，这样查询的吞吐会增加，单个查询的延迟随之降低。这个方式适合大吞吐低并发的查询场景。
    如果仅选择一个或少数分桶列，则对应的点查询可以仅触发一个分桶扫描。此时，当多个点查询并发时，这些查询有较大的概率分别触发不同的分桶扫描，各个查询之间的IO影响较小（尤其当不同桶分布在不同磁盘上时），所以这种方式适合高并发的点查询场景。
    分桶的数量理论上没有上限。

###### 22、Doris的Bloom Filter索引？
    Bloom Filter（布隆过滤器）是用于判断某个元素是否在一个集合中的数据结构，优点是空间效率和时间效率都比较高，缺点是有一定的误判率。

    布隆过滤器是由一个Bit数组和n个哈希函数构成。Bit数组初始全部为0，当插入一个元素时，n个Hash函数对元素进行计算, 得到n个slot，
    然后将Bit数组中n个slot的Bit置1。

###### 23、Doris物化视图？
    物化视图是将预先计算（根据定义好的 SELECT 语句）好的数据集，存储在 Doris 中的一个特殊的表。
    在实际的业务场景中，通常存在两种场景并存的分析需求：对固定维度的聚合分析 和 对原始明细数据任意维度的分析。
    物化视图的出现主要是为了满足用户，既能对原始明细数据的任意维度分析，也能快速的对固定维度进行分析查询。
    
    创建物化视图：
    CREATE MATERIALIZED VIEW materialized_view_name
    AS SELECT id, SUM(clicks) AS sum_clicks
    FROM  database_name.base_table
    GROUP BY id ORDER BY id’
    
    Duplicate 数据模型：Doris中的用于存放明细数据的数据模型，建表可指定，数据不会被聚合。
    Base 表：Doris 中通过 CREATE TABLE 命令创建出来的表。
    Materialized Views 表：简称 MVs，物化视图。
    
    优势
    对于那些经常重复的使用相同的子查询结果的查询性能大幅提升。
    Doris自动维护物化视图的数据，无论是新的导入，还是删除操作都能保证base 表和物化视图表的数据一致性。无需任何额外的人工维护成本。
    查询时，会自动匹配到最优物化视图，并直接从物化视图中读取数据。

###### 24、Doris系统架构？


###### 25、Doris的Rollup？
    在 Doris 中，我们将用户通过建表语句创建出来的表称为 Base 表（Base Table）。Base 表中保存着按用户建表语句指定的方式存储的基础数据。
    在 Base 表之上，我们可以创建任意多个 ROLLUP 表。这些 ROLLUP 的数据是基于 Base 表产生的，并且在物理上是独立存储的。
    ROLLUP 表的基本作用，在于在 Base 表的基础上，获得更粗粒度的聚合数据。

    Rollup也就是上卷，是一种在多维分析中比较常用的操作——也就是从细粒度的数据向高层的聚合。
    在Doris中，我们提供了在聚合模型上的构建Rollup功能，将数据根据更少的维度进行预聚合。
    将本身在用户查询时才会进行聚合计算的数据预先计算好，并存储在Doris中，从而达到提升用户粗粒度上的查询效率。

###### 26、Doris用Bitmap实现精确去重？
    给定一个数组A, 其取值范围为[0, n)(注: 不包括n), 对该数组去重, 可采用(n+7)/8的字节长度的bitmap, 初始化为全0; 
    逐个处理数组A的元素, 以A中元素取值作为bitmap的下标, 将该下标的bit置1; 最后统计bitmap中1的个数即为数组A的count distinct结果.

###### 27、Doris用HLL(HyperLogLog)实现近似去重？
    HyperLogLog（简称 HLL）是一种近似去重算法，它的特点是具有非常优异的空间复杂度O(mloglogn),  时间复杂度为O(n),
    并且计算结果的误差可控制在1%—10%左右，误差与数据集大小以及所采用的哈希函数有关。
    
    HyperLogLog是一种近似的去重算法，能够使用极少的存储空间计算一个数据集的不重复元素的个数。
    HLL类型是基于HyperLogLog算法的工程实现。用于保存HyperLogLog计算过程的中间结果，它只能作为数据表的指标列类型。
    
    如何使用HyperLogLog？
    1）、使用HyperLogLog去重, 需要在建表语句中, 将目标的指标列的类型设置为HLL,  聚合函数设置为HLL_UNION.
    2）、目前, 只有聚合表支持HLL类型的指标列.
    3）、当在HLL类型列上使用count distinct时，Doris会自动转化为HLL_UNION_AGG计算。
    
    Bitmap和HLL应该如何选择？如果数据集的基数在百万、千万量级，并拥有几十台机器，那么直接使用 count distinct 即可。
    如果基数在亿级以上，并且需要精确去重，那么只能用Bitmap类型；如果可以接受近似去重，那么还可以使用HLL类型。

###### 28、Doris的Colocation Join？
    Colocation Join 是在 Doris 0.9 版本中引入的新功能。旨在为某些 Join 查询提供本地性优化，来减少数据在节点间的传输耗时，加速查询。
    
    FE：Frontend，Doris 的前端节点。负责元数据管理和请求接入。
    BE：Backend，Doris 的后端节点。负责查询执行和数据存储。
    Colocation Group（CG）：一个 CG 中会包含一张及以上的 Table。在同一个 Group 内的 Table 有着相同的 Colocation Group Schema，并且有着相同的数据分片分布。
    Colocation Group Schema（CGS）：用于描述一个 CG 中的 Table，和 Colocation 相关的通用 Schema 信息。包括分桶列类型，分桶数以及副本数等。
    
    Colocation Join 功能，是将一组拥有相同 CGS 的 Table 组成一个 CG。并保证这些 Table 对应的数据分片会落在同一个 BE 节点上。
    使得当 CG 内的表进行分桶列上的 Join 操作时，可以通过直接进行本地数据 Join，减少数据在节点间的传输耗时。

    一个表的数据，最终会根据分桶列值 Hash、对桶数取模的后落在某一个分桶内。假设一个 Table 的分桶数为 8，则共有 [0, 1, 2, 3, 4, 5, 6, 7] 8 个分桶（Bucket），
    我们称这样一个序列为一个 BucketsSequence。每个 Bucket 内会有一个或多个数据分片（Tablet）。当表为单分区表时，一个 Bucket 内仅有一个 Tablet。如果是多分区表，则会有多个。

###### 29、Doris的窗口函数？
    窗口函数的语法：
    function(args) OVER(partition_by_clause order_by_clause [window_clause])    
    partition_by_clause ::= PARTITION BY expr [, expr ...]    
    order_by_clause ::= ORDER BY expr [ASC | DESC] [, expr [ASC | DESC] ...]

###### 30、Doris精确去重方式？
    使用Doris进行精确去重分析时，通常会有两种方式：
    1）、基于明细去重：即传统的count distinct 方式，好处是可以保留明细数据。提高了分析的灵活性。
         缺点则是需要消耗极大的计算和存储资源，对大规模数据集和查询延迟敏感的去重场景支持不够友好。
    2）、基于预计算去重：这种方式也是Doris推荐的方式。在某些场景中，用户可能不关心明细数据，仅仅希望知道去重后的结果。
         这种场景可采用预计算的方式进行去重分析，本质上是利用空间换时间，也是MOLAP聚合模型的核心思路。就是将计算提前到数据导入的过程中，
         减少存储成本和查询时的现场计算成本。并且可以使用RollUp表降维的方式，进一步减少现场计算的数据集大小。

###### 31、Doris传统去重计算？
    Doris 是基于MPP 架构实现的，在使用count distinct做精准去重时，可以保留明细数据，灵活性较高。
    但是，由于在查询执行的过程中需要进行多次数据shuffle（不同节点间传输数据，计算去重），会导致性能随着数据量增大而直线下降。

###### 32、Doris的CBO优化器？


###### 33、Spark Doris Connector？
    Spark Doris Connector 是Doris在0.12版本中推出的新功能。用户可以使用该功能，直接通过Spark对Doris中存储的数据进行查询。
    
    早期的方案中，直接将Doris的JDBC接口提供给Spark。对于JDBC这个Datasource，Spark侧的工作原理为，Spark的Driver通过JDBC协议，访问Doris的FE，以获取对应Doris表的Schema。
    然后，按照某一字段，将查询分位多个Partition子查询任务，下发给多个Spark的Executors。Executors将所负责的Partition转换成对应的JDBC查询，直接访问Doris的FE接口，获取对应数据。
    这种方案几乎无需改动代码，但是因为Spark无法感知Doris的数据分布，会导致打到Doris的查询压力非常大。
    
    于是开发了针对Doris的新的Datasource，Spark-Doris-Connector。这种方案下，Doris可以暴露Doris数据分布给Spark。Spark的Driver访问Doris的FE获取Doris表的Schema和底层数据分布。
    之后，依据此数据分布，合理分配数据查询任务给Executors。最后，Spark的Executors分别访问不同的BE进行查询。大大提升了查询的效率。

###### 34、Doris数据类型？
    1、数字类型
        BIGINT
        LARGEINT
        SMALLINT
        TINYINT
        BOOLEAN
        DECIMAL
        DOUBLE
        FLOAT
    2、字符串类型
        CHAR
        VARCHAR
        STRING
    3、日期类型
        DATE
        DATETIME
    4、其他类型
        HLL(HyperLogLog)
        BITMAP

###### 35、Doris监控报警？
    DorisDB提供两种监控报警的方案，第一种是使用内置的DorisManager，其自带的Agent从各个Host采集监控信息上报到Center Service然后做可视化展示，也提供了邮件和Webhook的方式发送报警通知。
    但是如果用户为了二次开发需求，需要自己搭建部署监控服务，也可以使用开源的Prometheus+Grafana的方案，DorisDB提供了兼容Prometheus的信息采集接口，
    可以通过直接链接BE/FE的HTTP端口来获取集群的监控信息。

###### 36、Doris用户权限？
    Doris 新的权限管理系统参照了 Mysql 的权限管理机制，做到了表级别细粒度的权限控制，基于角色的权限访问控制，并且支持白名单机制。

###### 37、Doris分区缓存？
    大部分数据分析场景是写少读多，数据写入一次，多次频繁读取，比如一张报表涉及的维度和指标，数据在凌晨一次性计算好，但每天有数百甚至数千次的页面访问，因此非常适合把结果集缓存起来。
    
    本分区缓存策略可以解决上面的问题，优先保证数据一致性，在此基础上细化缓存粒度，提升命中率，因此有如下特点
    1、用户无需担心数据一致性，通过版本来控制缓存失效，缓存的数据和从BE中查询的数据是一致的
    2、没有额外的组件和成本，缓存结果存储在BE的内存中，用户可以根据需要调整缓存内存大小
    3、实现了两种缓存策略，SQLCache和PartitionCache，后者缓存粒度更细
    4、用一致性哈希解决BE节点上下线的问题，BE中的缓存算法是改进的LRU

###### 38、Doris动态分区？
    动态分区是在 Doris 0.12 版本中引入的新功能。旨在对表级别的分区实现生命周期管理(TTL)，减少用户的使用负担。
    目前实现了动态添加分区及动态删除分区的功能。
    动态分区只支持 Range 分区。

    在某些使用场景下，用户会将表按照天进行分区划分，每天定时执行例行任务，这时需要使用方手动管理分区，否则可能由于使用方没有创建分区导致数据导入失败，这给使用方带来了额外的维护成本。
    通过动态分区功能，用户可以在建表时设定动态分区的规则。FE 会启动一个后台线程，根据用户指定的规则创建或删除分区。用户也可以在运行时对现有规则进行变更。

###### 39、Doris物化视图 VS Rollup？ 
    在没有物化视图功能之前，用户一般都是使用 Rollup 功能通过预聚合方式提升查询效率的。但是 Rollup 具有一定的局限性，他不能基于明细模型做预聚合。
    物化视图则在覆盖了 Rollup 的功能的同时，还能支持更丰富的聚合函数。所以物化视图其实是 Rollup 的一个超集。
    也就是说，之前 ALTER TABLE ADD ROLLUP 语法支持的功能现在均可以通过 CREATE MATERIALIZED VIEW 实现。

###### 40、Doris的Join有哪些？
    1、Broadcast Join
    系统默认实现 Join 的方式，是将小表进行条件过滤后，将其广播到大表所在的各个节点上，形成一个内存 Hash 表，然后流式读出大表的数据进行Hash Join。
    但是如果当小表过滤后的数据量无法放入内存的话，此时 Join 将无法完成，通常的报错应该是首先造成内存超限。
    2、Shuffle Join，也被称作 Partitioned Join。
    将小表和大表都按照 Join 的 key 进行 Hash，然后进行分布式的 Join。这个对内存的消耗就会分摊到集群的所有计算节点上。
    3、Lateral Join
    4、Colocation Join

###### 41、Doris的Broker？
    Broker 是 Doris 集群中一种可选进程，主要用于支持 Doris 读写远端存储上的文件和目录，如 HDFS、BOS 和 AFS 等。
    
    Broker 通过提供一个 RPC 服务端口来提供服务，是一个无状态的 Java 进程，负责为远端存储的读写操作封装一些类 POSIX 的文件操作，
    如 open，pread，pwrite 等等。除此之外，Broker 不记录任何其他信息，所以包括远端存储的连接信息、文件信息、权限信息等等，
    都需要通过参数在 RPC 调用中传递给 Broker 进程，才能使得 Broker 能够正确读写文件。
    
    Broker 仅作为一个数据通路，并不参与任何计算，因此仅需占用较少的内存。通常一个 Doris 系统中会部署一个或多个 Broker 进程。
    并且相同类型的 Broker 会组成一个组，并设定一个 名称（Broker name）。

###### 42、Doris的Binlog Load？
    Binlog Load提供了一种使Doris增量同步用户在Mysql数据库的对数据更新操作的CDC(Change Data Capture)功能。
    
    1、适用场景
    INSERT/UPDATE/DELETE支持
    过滤Query
    暂不兼容DDL语句
    
    2、名词解释
    Frontend（FE）：Doris 系统的元数据和调度节点。在导入流程中主要负责导入 plan 生成和导入任务的调度工作。
    Backend（BE）：Doris 系统的计算和存储节点。在导入流程中主要负责数据的 ETL 和存储。
    Canal：阿里巴巴开源的Mysql Binlog日志解析工具。提供增量数据订阅&消费等功能。
    Batch：Canal发送到客户端的一批数据，具有全局唯一自增的ID。
    SyncJob：用户提交的一个数据同步作业。
    Receiver: 负责订阅并接收Canal的数据。
    Consumer: 负责分发Receiver接收的数据到各个Channel。
    Channel: 接收Consumer分发的数据的渠道，创建发送数据的子任务，控制单个表事务的开启、提交、终止。
    Task：Channel向Be发送数据的子任务。
    
    3、基本原理
    在第一期的设计中，Binlog Load需要依赖canal作为中间媒介，让canal伪造成一个从节点去获取Mysql主节点上的Binlog并解析，
    再由Doris去获取Canal上解析好的数据，主要涉及Mysql端、Canal端以及Doris端

###### 43、Doris的Broker Load？
    Broker load 是一个异步的导入方式，支持的数据源取决于 Broker 进程支持的数据源。
    用户需要通过 MySQL 协议 创建 Broker load 导入，并通过查看导入命令检查导入结果。
    
    1、适用场景
    源数据在 Broker 可以访问的存储系统中，如 HDFS。
    数据量在 几十到百GB 级别。
    
    2、名词解释
    Frontend（FE）：Doris 系统的元数据和调度节点。在导入流程中主要负责导入 plan 生成和导入任务的调度工作。
    Backend（BE）：Doris 系统的计算和存储节点。在导入流程中主要负责数据的 ETL 和存储。
    Broker：Broker 为一个独立的无状态进程。封装了文件系统接口，提供 Doris 读取远端存储系统中文件的能力。
    Plan：导入执行计划，BE 会执行导入执行计划将数据导入到 Doris 系统中。
    
    3、基本原理
    用户在提交导入任务后，FE 会生成对应的 Plan 并根据目前 BE 的个数和文件的大小，将 Plan 分给 多个 BE 执行，每个 BE 执行一部分导入数据。
    BE 在执行的过程中会从 Broker 拉取数据，在对数据 transform 之后将数据导入系统。所有 BE 均完成导入，由 FE 最终决定导入是否成功。

###### 44、Doris的Routine Load？
    例行导入（Routine Load）功能为用户提供了一种自动从指定数据源进行数据导入的功能。
    当前仅支持从 Kafka 系统进行例行导入。

    1、名词解释
    FE：Frontend，Doris 的前端节点。负责元数据管理和请求接入。
    BE：Backend，Doris 的后端节点。负责查询执行和数据存储。
    RoutineLoadJob：用户提交的一个例行导入作业。
    JobScheduler：例行导入作业调度器，用于调度和拆分一个 RoutineLoadJob 为多个 Task。
    Task：RoutineLoadJob 被 JobScheduler 根据规则拆分的子任务。
    TaskScheduler：任务调度器。用于调度 Task 的执行。

###### 45、Doris的Load Json Format Data？
    Currently only the following load methods support data import in Json format:
    Stream Load
    Routine Load

###### 46、Doris的查询性能？
    FE连接数、FE结点数、BE结点数
    大查询、失败查询

###### 47、Doris的分区与分桶？
    Doris 支持两层的数据划分。第一层是 Partition，支持 Range 和 List 的划分方式。第二层是 Bucket（Tablet），仅支持 Hash 的划分方式。
    也可以仅使用一层分区。使用一层分区时，只支持 Bucket 划分。
    
    Range 分区
    分区列通常为时间列，以方便的管理新旧数据。
    Partition 支持通过 VALUES LESS THAN (...) 仅指定上界，系统会将前一个分区的上界作为该分区的下界，生成一个左闭右开的区间。通过，也支持通过 VALUES [...) 指定同时指定上下界，生成一个左闭右开的区间。
    
    List 分区
    分区列支持 BOOLEAN, TINYINT, SMALLINT, INT, BIGINT, LARGEINT, DATE, DATETIME, CHAR, VARCHAR 数据类型，分区值为枚举值。只有当数据为目标分区枚举值其中之一时，才可以命中分区。
    Partition 支持通过 VALUES IN (...) 来指定每个分区包含的枚举值。
    
    Bucket
    如果使用了 Partition，则 DISTRIBUTED ... 语句描述的是数据在各个分区内的划分规则。如果不使用 Partition，则描述的是对整个表的数据的划分规则。
    分桶列可以是多列，但必须为 Key 列。分桶列可以和 Partition 列相同或不同。

###### 48、Doris的Runtime Filter？
    Runtime Filter 是在 Doris 0.15 版本中正式加入的新功能。旨在为某些 Join 查询在运行时动态生成过滤条件，来减少扫描的数据量，避免不必要的I/O和网络传输，从而加速查询。
    它的设计、实现和效果可以参阅 ISSUE 6116 (https://github.com/apache/incubator-doris/issues/6116)。

    原理：Runtime Filter在查询规划时生成，在HashJoinNode中构建，在ScanNode中应用。
    FE：Frontend，Doris 的前端节点。负责元数据管理和请求接入。
    BE：Backend，Doris 的后端节点。负责查询执行和数据存储。
    左表：Join查询时，左边的表。进行Probe操作。可被Join Reorder调整顺序。
    右表：Join查询时，右边的表。进行Build操作。可被Join Reorder调整顺序。
    Fragment：FE会将具体的SQL语句的执行转化为对应的Fragment并下发到BE进行执行。BE上执行对应Fragment，并将结果汇聚返回给FE。
    Join on clause: A join B on A.a=B.b中的A.a=B.b，在查询规划时基于此生成join conjuncts，包含join Build和Probe使用的expr，其中Build expr在Runtime Filter中称为src expr，Probe expr在Runtime Filter中称为target expr。

###### 49、Doris监控与报警？
    Doris 使用 Prometheus (opens new window)和 Grafana (opens new window)进项监控项的采集和展示。
    1、Prometheus
    Prometheus 是一款开源的系统监控和报警套件。它可以通过 Pull 或 Push 采集被监控系统的监控项，存入自身的时序数据库中。并且通过丰富的多维数据查询语言，满足用户的不同数据展示需求。
    2、Grafana
    Grafana 是一款开源的数据分析和展示平台。支持包括 Prometheus 在内的多个主流时序数据库源。通过对应的数据库查询语句，从数据源中获取展现数据。通过灵活可配置的 Dashboard，快速的将这些数据以图表的形式展示给用户。

###### 50、Doris支持的索引？
    Doris主要支持两类索引：
    1、内建的智能索引，包括前缀索引和ZoneMap索引。
    1.1）、前缀索引
    Doris 的数据存储在类似 SSTable（Sorted String Table）的数据结构中。该结构是一种有序的数据结构，可以按照指定的列进行排序存储。
    在这种数据结构上，以排序列作为条件进行查找，会非常的高效。而前缀索引，即在排序的基础上，实现的一种根据给定前缀列，快速查询数据的索引方式。
    前缀索引是以Block为粒度创建的稀疏索引，一个Block包含1024行数据，每个Block，以该Block的第一行数据的前缀列的值作为索引。
    1.1）、ZoneMap索引
    ZoneMap索引是在列存格式上，对每一列自动维护的索引信息，包括Min/Max，Null值个数等等。

    2、用户创建的二级索引，包括Bloom Filter索引和Bitmap倒排索引。
    2.1）、布隆过滤器索引    
    Bloom Filter本质上是一种位图结构，用于快速的判断一个给定的值是否在一个集合中。
    以Block为粒度创建的。每个Block中，指定列的值作为一个集合生成一个BF索引条目，用于在查询是快速过滤不满足条件的数据。
    2.2）、Bitmap倒排索引
    在某些列上创建Bitmap索引,Bitmap索引是一种特殊的数据库索引技术，其索引使用bit数组进行存储与计算操作。

###### 51、常用OLAP框架对比？
    MOLAP：Doris、Druid、Kylin
    ROLAP：Clickhouse、Presto、Impala、GreenPlum、Elasticsearch、Hive、Spark SQL
    MPP架构：Doris、Clickhouse、Presto、SparkSQL
    
    Doris：
    支持明细和聚合查询，支持多表join
    支持实时和离线导入方式
    支持Rollup和物化视图
    支持前缀索引、Bitmap索引、Bloom Filter索引，提高查询效率

    Clickhouse：
    分块分布式存储大表，单表聚合查询性能强劲，不适合做多表关联，不依赖hadoop生态

    Druid：
    可以对数据支持亚秒级别查询，支持低延迟的数据插入和更新，只支持单数据源的查询，不支持join
    
###### 52、Doris结点增加？
    FE分为Leader，Follower和Observer三种角色。 默认一个集群，只能有一个Leader，可以有多个Follower和Observer。
    其中Leader和Follower组成一个Paxos选择组，如果Leader宕机，则剩下的Follower会自动选出新的Leader，保证写入高可用。
    Observer同步Leader的数据，但是不参加选举。如果只部署一个FE，则FE默认就是Leader。

    增加BE结点：BE节点的增加方式通过 ALTER SYSTEM ADD BACKEND 命令增加 BE节点。
    删除BE结点：ALTER SYSTEM DROP BACKEND

###### 53、Doris位图实现原理？ 
    直接使用原始的Bitmap会有两个问题，一个是内存和存储占用，一个是Bitmap输入只支持Int类型。
    解决内存和存储占用的思路就是压缩，业界普遍采用的Bitmap库是Roaring Bitmap；解决任意类型输入的思路就是将任意类型映射到Int类型。
     
    Roaring Bitmap 的核心思路很简单，就是根据数据的不同特征采用不同的存储或压缩方式。
    将一个整数划分为高16位和低16位，高16位作为桶，低16位作为value

    Roaring Bitmap 首先进行了分桶，将整个int 域拆成了 2的16次方 65536个桶，每个桶最多包含65536个元素。 
    所以一个int的高16位决定了，它位于哪个桶，桶里只存储低16位。

###### 54、bitmap常见实现？
    Java中一个int类型占用4个字节32位,
    有一亿的数据量，使用普通的存储模式需要:100000000*4/1024/1024 约为381.5M的存储；
    使用bitmap存储模式需要：100000000/8/1024/1024 约为11.9M的存储;

    1、Java.util包中提供了BitSet类型，其内部包含了一个long类型的数组，通过位运算实现bitmap功能
    bitmap有一个弊端，那就是对于稀疏数据来说会占用很大存储。对此需要使用压缩bitmap,也就是roaringbitmap。

    2、RoaringBitmap 是一种压缩bitmap，
    其思想就是采用高低位存储方式，将一个Int类型的数据转换为高16位与低16位，也就是两个short类型的数据，
    高位存储在一个short[] 里面，低位存储在Container[]中，short[] 下标与Container[]下标是一一对应关系。

###### 55、doris分区partition和分桶bucket数量和数据量的建议？
    1、一个表的tablet数量，建议多于整个集群的磁盘数量
    2、单个tablet的数据量建议在1~10G，过小则数据聚合效果不佳，且元数据管理压力大。过大则不利于副本迁移、同步，而且会增加schema change和rollup操作失败重试的代价。
    3、当tablet数量和数据量冲突时候，数据量优先，比如一个很小的集群，但是数据量很大，可以多个tablet。

###### 56、doris前缀索引、位图索引、布隆过滤器索引使用场景？
    1、前缀索引，即在排序的基础上，实现的一种根据给定前缀列，快速查询数据的索引方式。前36个字节作为这行数据的前缀索引，当遇到 VARCHAR 类型时，前缀索引会直接截断。
    前缀索引是以Block为粒度创建的稀疏索引，一个Block包含1024行数据，每个Block，以该Block的第一行数据的前缀列的值作为索引。
    适用场景
    当我们的查询条件，是前缀索引的前缀时，可以极大的加快查询速度。

    2、Bitmap 索引
    适用场景
    适用于低基数的列上，建议在100到100000之间，如：职业、地市等。基数太高则没有明显优势；基数太低，则空间效率和性能会大大降低。

    3、Bloom Filter 索引
    用于快速的判断一个给定的值是否在一个集合中。BF索引也是以Block为粒度创建的。
    每个Block中，指定列的值作为一个集合生成一个BF索引条目，用于在查询是快速过滤不满足条件的数据。
    适用场景
    由于Bloom Filter数据结构的特性，BF索引比较适合创建在高基数的列上，比如UserID。
    因为如果创建在低基数的列上，比如”性别“列，则每个Block几乎都会包含所有取值，导致BF索引失去意义。
