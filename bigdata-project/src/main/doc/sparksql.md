* [9.2、Spark SQL]()
    - [1）、Spark SQL和Hive区别？Spark SQL一定比Hive快么？](#1spark-sqlhivespark-sqlhive)
    - [2）、Spark SQL有使用过么？在哪些项目中使用过？](#2spark-sql)
    - [3）、Spark SQL中UDF使用？](#3spark-sqludf)
    - [4）、SparkSession、SparkContext和SQLContext区别？](#4sparksessionsparkcontextsqlcontext)
    - [5）、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？](#5spark-sql)
    - [6）、Spark SQL程序调优？](#6spark-sql)
    - [7）、Spark SQL运行原理？](#7spark-sql)
    - [8）、Spark SQL适用的场景,Spark Core不适合的?]()
    - [9）、Spark SQL2.0和3.0区别?]()
    - [10）、Spark SQL的DataFrame和RDD有啥区别?]()

---
###### 1、Spark SQL和Hive区别？Spark SQL一定比Hive快么？
    Spark SQL 比 Hadoop Hive 快，是有一定条件的，而且不是 Spark SQL 的引擎比 Hive 的引擎快，相反，Hive 的 HQL 引擎还比 Spark SQL 的引擎更快。其实，关键还是在于 Spark 本身快。
    1.Shuffle时候spark sql处理数据不一定落盘,而hive是强制写磁盘
    2.spark提供丰富的算子,而hive每次都是一个完整的mr操作
    3.JVM优化,hive每次mr是启动一个进程处理,而spark的Mr是基于线程的

###### 2、Spark SQL有使用过么？在哪些项目中使用过？
    必须的，经常使用

###### 3、Spark SQL中UDF使用？
    spark.sql.function中的已经包含了大多数常用的函数，但是总有一些场景是内置函数无法满足要求的，此时就需要使用自定义函数了(UDF)。
    1、在dataframe中使用：
    // 注册自定义函数
    ss.udf.register("add_one", add_one _)
    // 使用自定义函数
    import org.apache.spark.sql.functions
    val frame = df.withColumn("age2", functions.callUDF("add_one", functions.col("age")))
    // 定义自定义函数
    def add_one(col: Double) = {
        col + 1
    }
    
    2、在sparkSQL中使用
    # 定义自定义函数
    def strLen(col: String) = {
        str.length()
    }
    # 注册自定义函数
    spark.udf.register("strLen", strLen _)
    # 使用自定义函数
    spark.sql("select name,strLen(name) from table ")

###### 4、SparkSession、SparkContext和SQLContext区别？
    SparkContext:驱动程序使用与集群进行连接和通信，它可以帮助执行Spark任务，并与资源管理器(如YARN 或Mesos)进行协调。
    SQLContext:是通往SparkSQL的入口。有了SQLContext，就可以开始处理DataFrame、DataSet等
    HiveContext:是通往hive入口。HiveContext具有SQLContext的所有功能。
    SparkSession:是在Spark 2.0中引入的，替换了旧的SQLContext和HiveContext。

###### 5、Spark SQL用过哪些算子？遇到哪些问题？如何解决的？
    toDF、join、foreach、head等等
    遇到问题：join等聚合操作时候发生数据倾斜
        要区分开数据倾斜与数据量过量这两种情况，
            数据倾斜是指少数task被分配了绝大多数的数据，因此少数task运行缓慢；
            数据过量是指所有task被分配的数据量都很大，相差不多，所有task都运行缓慢。
    解决方案：
        1、聚合原数据
        2、过滤导致倾斜的key：在sql中用where条件
        3、提高shuffle并行度：groupByKey(1000)，spark.sql.shuffle.partitions（默认是200）
        4、reduce join转换为map join：spark.sql.autoBroadcastJoinThreshold
        5、随机key与热点数据再分区

###### 6、Spark SQL程序调优？
    1、参数调优：
    spark.sql.shuffle.partitions：并行度
    spark.sql.autoBroadcastJoinThreshold：Join操作时，要被广播的表的最大字节数
    spark.sql.tungsten.enabled：开启tungsten优化
    spark.sql.planner.externalSort：根据需要执行Sort溢出到磁盘上，否则在每个分区内存中
    2、代码调优
    数据缓存、聚合算子

###### 7、Spark SQL运行原理？
    SparkSQL的运行架构：
    sparksql先会将SQL语句进行解析（parse）形成一个Tree,然后使用Rule对Tree进行绑定，优化等处理过程，通过模式匹配对不同类型的节点采用不同操作。
    而sparksql的查询优化器是，它负责处理查询语句的解析，绑定，优化和生成物理执行计划等过程，catalyst是sparksql最核心部分。
    
    Spark SQL由core，catalyst，hive和hive-thriftserver4个部分组成。
    core: 负责处理数据的输入/输出，从不同的数据源获取数据（如RDD,Parquet文件和JSON文件等），然后将结果查询结果输出成Data Frame。
    catalyst: 负责处理查询语句的整个处理过程，包括解析，绑定，优化，生成物理计划等。 
    hive: 负责对hive数据的处理。
    hive-thriftserver：提供client和JDBC/ODBC等接口。
    
    sql实际执行过程：
    1、语法和词法解析：对写入的sql语句进行词法和语法解析（parse），分辨出sql语句在哪些是关键词（如select ,from 和where）,
        哪些是表达式，哪些是projection ，哪些是datasource等，判断SQL语法是否规范，并形成逻辑计划。
    2、绑定：将SQL语句和数据库的数据字典（列，表，视图等）进行绑定（bind）,如果相关的projection和datasource等都在的话，
        则表示这个SQL语句是可以执行的。
    3、优化（optimize）：一般的数据库会提供几个执行计划，这些计划一般都有运行统计数据，数据库会在这些计划中选择一个最优计划。
    4、执行（execute）：执行前面的步骤获取最有执行计划，返回查询的数据集。
    
    运行原理原理分析：
    1.使用SesstionCatalog保存元数据
        在解析sql语句前需要初始化sqlcontext,它定义sparksql上下文，在输入sql语句前会加载SesstionCatalog，
        初始化sqlcontext时会把元数据保存在SesstionCatalog中，包括库名，表名，字段，字段类型等。这些数据将在解析未绑定的逻辑计划上使用。
    2.使用Antlr生成未绑定的逻辑计划
        Spark2.0版本起使用Antlr进行词法和语法解析，Antlr会构建一个按照关键字生成的语法树，也就是生成的未绑定的逻辑计划。
    3.使用Analyzer绑定逻辑计划
        在这个阶段Analyzer 使用Analysis Rules,结合SessionCatalog元数据，对未绑定的逻辑计划进行解析，生成已绑定的逻辑计划。
    4.使用Optimizer优化逻辑计划
        Opetimize（优化器）的实现和处理方式同Analyzer类似，在该类中定义一系列Rule,利用这些Rule对逻辑计划和Expression进行迭代处理，
        达到树的节点的合并和优化。
    5.使用SparkPlanner生成可执行计划的物理计划
        SparkPlanner使用Planning Strategies对优化的逻辑计划进行转化，生成可执行的物理计划。
    6.使用QueryExecution执行物理计划


###### 8、Spark SQL适用的场景,Spark Core不适合的?
    SparkCore只侧重数据本身，没有表概念，SparkSQL要侧重：数据+表结构。
    SparkSQL的编程有两种风格：SQL风格、DSL分格，SparkCore处理结构化的数据不能很好的基于列进行处理，每次都需要分割，这种情况SparkSQL方便些。

###### 9、Spark SQL2.0和3.0区别?


###### 10、Spark SQL的DataFrame和RDD有啥区别?
    1、SparkCore的核心数据类型是RDD，SparkSQL核心数据类型是DataFrame
    2、SparkCore的核心入口类是SparkContext、SparkSQL的核心入口类是：SparkSession
    3、SparkSQL是基于SparkCore，SparkSQL代码底层就是rdd
