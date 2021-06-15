* [Spark Core知识点]()
    - [1）、Spark作业提交流程？](#1、Spark作业提交流程？)
    - [2）、Spark的内存模型？](#2、Spark的内存模型？)
    - [3）、SparkContext创建流程？源码级别？](#3、SparkContext创建流程？源码级别？)
    - [4）、简述Spark个版本区别？1.x与2.x？](#4、简述Spark个版本区别？1.x与2.x？)
    - [5）、使用Spark中遇到过哪些问题？如何解决的？](#5、使用Spark中遇到过哪些问题？如何解决的？)
    - [6）、Spark的Shuffle过程？ 和MR Shuffle区别？](#6、Spark的Shuffle过程？和MR的Shuffle区别？)
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
    - [40）、Spark中map和flatMap有啥区别？](#40、Spark中map和flatmap有啥区别？)
    - [41）、介绍一下join操作优化经验？](#41、介绍一下join操作优化经验？)
    - [42）、Spark有哪些组件？](#42、Spark有哪些组件？)
    - [43）、Spark的工作机制？](#43、Spark的工作机制？)
    - [44）、Spark中的宽窄依赖？](#44、Spark中的宽窄依赖？)
    - [45）、Spark如何划分stage？](#45、Spark如何划分stage？)
    - [46）、spark-submit时候如何引用外部的jar包？](#46spark-submitjar)
    - [47）、Spark中RDD有哪些特性？](#47、Spark中RDD有哪些特性？)
    - [48）、Spark的一个工作流程？](#48、Spark的一个工作流程？)
    - [49）、Spark on yarn与standalone区别？](#49sparkonyarnstandalone)
    - [50）、Spark优化之内存管理？](#50、Spark优化之内存管理？)
    - [51）、Spark优化之广播变量？](#51、Spark优化之广播变量？)
    - [52）、Spark优化之数据本地性？](#52、Spark优化之数据本地性？)
    - [53）、Spark中task有几种类型？](#53、Spark中task有几种类型？)

---
###### 1、Spark作业提交流程？
    Spark作业提交流程：
    1、将我们编写的Spark程序打包成jar包。
    2、使用spark-submit脚本将任务提交到集群中运行。
    3、运行SparkSubmit的main方法，通过反射的方式创建我们编写主类的实例对象，然后调用主类的main方法开始执行我们编写的代码。
    4、当代码运行到SparkContext时，就开始初始化SparkContext。
    5、在初始化SparkContext时候会创建DAGScheduler和TaskScheduler。
    
    1、spark-submit 提交代码，执行 new SparkContext()，在 SparkContext 里构造 DAGScheduler 和 TaskScheduler。
    2、TaskScheduler 会通过后台的一个进程，连接 Master，向 Master 注册 Application。
    3、Master 接收到 Application 请求后，会使用相应的资源调度算法，在 Worker 上为这个 Application 启动多个 Executer。
    4、Executor 启动后，会自己反向注册到 TaskScheduler 中。 所有 Executor 都注册到 Driver 上之后，SparkContext 结束初始化，接下来往下执行我们自己的代码。
    5、每执行到一个 Action，就会创建一个 Job。Job 会提交给 DAGScheduler。
    6、DAGScheduler 会将 Job划分为多个 stage，然后每个 stage 创建一个 TaskSet。
    7、TaskScheduler 会把每一个 TaskSet 里的 Task，提交到 Executor 上执行。
    8、Executor 上有线程池，每接收到一个 Task，就用 TaskRunner 封装，然后从线程池里取出一个线程执行这个 task。
    (TaskRunner 将我们编写的代码，拷贝，反序列化，执行 Task，每个 Task 执行 RDD 里的一个 partition)

###### 2、Spark的内存模型？
    Spark集群在提交应用程序时候会创建Driver和Execotor两种JVM进程。
    Driver内存：Driver作为主控进程，负责创建Spark作业的上下文，将提交的作业Job转化为计算任务Task，分发到Executor进程中进行执行。
    Execotor内存：在工作节点上执行具体的计算任务，并将结果返回给Driver，同时提供RDD的持久化机制。
    
    堆内内存划分:
    指的是JVM堆内存大小，在Spark应用程序启动时通过spark.executor.memory参数配置。Executor内运行的并发任务共享JVM堆内内存。堆内内存大致可以分为如图2所示以下4个部分
    2.1.1、执行Execution内存
    用于存储Spark task执行过程中需要的对象，如Shuffle、Join、Sort、Aggregation等计算过程中的临时数据。
    2.1.2、存储Storage内存
    主要用于存储Spark的cache数据，例如RDD的cache，Broadcast变量，Unroll数据等。需要注意的是，unrolled的数据如果内存不够，会存储在driver端。
    2.1.3、用户内存User Memory
    分配Spark Memory剩余的内存，用户可以根据需要使用。可以存储RDD transformations需要的数据结构。
    2.1.4、预留内存Reserved Memory
    这部分内存是预留给系统使用，是固定不变的。在1.6.0默认为300MB(RESERVED_SYSTEM_MEMORY_BYTES = 300 * 1024 * 1024)，不过在2.4.4版本中已经看不到这个参数了。
    
    堆外内存划分
    这里Off-heap Memory从概念上可以分为两个：
    1).Executor JVM内的off-heap memory(*)，主要用于JVM自身，字符串, NIO Buffer等开销，可以通过spark.executor.memoryOverhead参数进行配置，
    大小一般设为executorMemory * 0.10, with minimum of 384。
    2).为了进一步优化内存的使用以及提高Shuffle时的排序的效率，Spark引入了堆外（Off-heap）内存，直接在工作节点的系统内存中开辟的空间，存储经过序列化的二进制数据。
    Spark可以直接操作系统堆外内存，减少了不必要的内存开销，以及频繁的 GC 扫描和回收，提升了处理性能。堆外内存可以被精确地申请和释放，而且序列化的数据占用的空间可以被精确计算，
    所以相比堆内内存来说降低了管理的难度，也降低了误差。

    Spark为存储内存和执行内存的管理提供了统一的接口——MemoryManager，同一个 Executor内的任务都调用这个接口的方法来申请或释放内存。
    MemoryManager的具体实现上，Spark 1.6之后默认为统一管理（Unified Memory Manager）方式，
    1.6之前采用的静态管理（Static Memory Manager）方式仍被保留，可通过配置 spark.memory.useLegacyMode 参数启用。
    两种方式的区别在于对空间分配的方式。
    
    Spark 1.6 之后引入的统一内存管理机制，与静态内存管理的区别在于存储内存和执行内存共享同一块空间，可以动态占用对方的空闲区域
    其中最重要的优化在于动态占用机制，其规则如下：
    1.设定基本的Storage内存和Execution内区域（spark.storage.storageFraction 参数），该设定确定了双方各自拥有的空间的范围
    2.双方的空间都不足时，则存储到硬盘；若己方空间不足而对方空余时，可借用对方的空间;（存储空间不足是指不足以放下一个完整的 Block）
    3.Execution内存的空间被对方占用后，可让对方将占用的部分转存到硬盘
    4.Storage内存的空间被对方占用后，无法让对方"归还"，多余的Storage内存被转存到硬盘

###### 3、SparkContext创建流程？源码级别？
    SparkContext初始化,包括事件总线(LiveListenerBus),UI界面,心跳,JobProgressListener,资源动态分配(ExecutorAllocationManager)等等
    初始化的核心包括:
    DAGScheduler:负责创建Job,RDD的Stage划分以及提交.
    TaskScheduler:负责Task的调度,调度的Task是由DAGScheduler创建的.
    *SchedulerBackend:负责连接Master并注册当前的程序；申请资源(Executor)和task具体到Executor的执行和管理,具体类型和deploy Mode有关比如standalone或者on yarn
    SparkEnv:Spark运行时环境,Executor依赖SparkEnv,它内部包含有很多组件，例如serializerManager,RpcEnv,BlockManager。(Driver中也有SparkEnv，这个是为了Local[*]模式下能运行)

###### 4、简述Spark个版本区别？1.x与2.x？
    spark2.x增加Tungsten执行引擎,比spark1.x快10倍.
    spark2.x增加了SparkSession,把spark1.x的SQLContext和HiveContext整合了.
    spark2.x统一DataFrames 和 Datasets 的 API
    spark2.x的Spark Streaming基于Spark SQL构建了high-level API.使得Spark Streaming更好的受益于Spark SQL的易用性和性能提升.

###### 5、使用Spark中遇到过哪些问题？如何解决的？
    数据倾斜
    内存溢出

###### 6、Spark的Shuffle过程？和MR的Shuffle区别？
    Shuffle是数据的重新分发过程,将各个节点的同一类数据汇聚到某一节点上进行计算.
    
    Spark和Mr的Shuffle区别:
    1)本质上相同，都是把Map端数据分类处理后交由Reduce的过程。
    2)数据流有所区别，MR按map, spill, merge, shuffle, sort, reduce等各阶段逐一实现。Spark基于DAG数据流，可实现更复杂数据流操作（根据宽/窄依赖实现）
    3)实现功能上有所区别，MR在map中做了排序操作，而Spark假定大多数应用场景Shuffle数据的排序操作不是必须的，而是采用Aggregator机制（Hashmap每个元素<K,V>形式）实现。
    (为了减少内存使用，Aggregator是在磁盘进行，也就是说，尽管Spark是“基于内存的计算框架”，但是Shuffle过程需要把数据写入磁盘)

###### 7、Spark中的数据倾斜问题有啥好的解决方案？
    导致数据倾斜的问题基本是使用shuffle算子引起的，所以我们先去找到代码中的shuffle的算子，比如distinct、groupBYkey、reduceBykey、aggergateBykey、join、cogroup、repartition等
    1).数据做预处理(hive etl ,spark sql...)
    2).采样倾斜key并分拆join操作
    3).采用随机前缀和扩容rdd进行join
    4).提高shuffle操作的并行度
    5).将reduce join变为map join实现(比如广播...)
    6).两阶段聚合（局部聚合+全局聚合）
    7).过滤少数导致倾斜的key

###### 8、Spark有哪些聚合类的算子，我们应该怎么避免使用这些算子？ReduceByKey和GroupByKey的区别？
    groupByKey,reduceByKey,aggregateByKey,sortByKey,join等
    
    ReduceByKey更适合使用在大数据集上,在每个分区移动数据之前将输出数据进行combine操作.
    foldByKey,aggregateByKey都是由combineByKey实现，并且mapSideCombine=true，因此可以使用这些函数替代goupByKey。

###### 9、SparkOnYarn作业执行流程？yarn-client和yarn-cluster的区别？
    Spark On Yarn的优势
    1.Spark支持资源动态共享，运行于Yarn的框架都共享一个集中配置好的资源池
    2.可以很方便的利用Yarn的资源调度特性来做分类,隔离以及优先级控制负载，拥有更灵活的调度策略
    3.Yarn可以自由地选择executor数量
    4.Yarn是唯一支持Spark安全的集群管理器，使用Yarn，Spark可以运行于Kerberized Hadoop之上，在它们进程之间进行安全认证 
    
    在yarn-cluster模式下，driver运行在AM中，负责向Yarn（RM）申请资源，并监督Application的运行情况，
    当Client（这里的Client指的是Master节点）提交作业后，就会关掉Client，作业会继续在yarn上运行，这也是Cluster模式不适合交互类型作业的原因。
    在Yarn-client模式下，Driver运行在Client上，通过AM向RM获取资源。本地Driver负责与所有的executor container进行交互，并将最后的结果汇总。结束掉终端，相当于kill掉这个spark应用。

###### 10、Spark中Job、Task、RDD、DAG、Stage的理解？
    Job-Stage-Task之间的关系:
    一个Spark程序可以被划分为一个或多个Job，划分的依据是RDD的Action算子，每遇到一个RDD的Action操作就生成一个新的Job。
    每个spark Job在具体执行过程中因为shuffle的存在，需要将其划分为一个或多个可以并行计算的stage，划分的依据是RDD间的Dependency关系，当遇到Wide Dependency时划分不同的Stage。
    Stage是由Task组组成的并行计算，因此每个stage中可能存在多个Task，这些Task执行相同的程序逻辑，只是它们操作的数据不同。一般RDD的一个Partition对应一个Task,Task可以分为ResultTask和ShuffleMapTask。

    Application: 用户编写的Spark应用程序,包括一个Driver和多个executors
    Application jar: 包含用户程序的Jar包
    Driver Program: 运行main()函数并创建SparkContext进程
    Cluster Manager: 在集群上获取资源的外部服务，如standalone manager,yarn,Mesos
    Deploy Mode: 部署模式，区别在于driver process运行的位置
    Worker Node: 集群中可以运行程序代码的节点（机器）
    Executor: 运行在worker node上执行具体的计算任务，存储数据的进程
    Task: 被分配到一个Executor上的计算单元
    Job: 由多个任务组成的并行计算阶段，因RDD的Action产生
    Stage: 每个Job被分为小的计算任务组，每组称为一个stage
    DAGScheduler: 根据Job构建基于Stage的DAG，并提交Stage给TaskScheduler
    TaskScheduler: 将TaskSet提交给worker运行，每个executor运行什么task在此分配

###### 11、Spark中RDD如何通过记录更新的方式容错？
    一般而言，分布式数据集的容错性具备两种方式：数据检查点和记录数据的更新
    checkpoint机制——数据检查点
    记录更新机制（在Saprk中对应Lineage机制）

    RDD只支持粗粒度转换，即只记录单个块上执行的单个操作，然后将创建RDD的一系列变换序列记录下来，以便恢复丢失的分区。
    Lineage本质上很类似于数据库中的重做日志（Redo Log），只不过这个重做日志粒度很大，是对全局数据做同样的重做进而恢复数据。

    Rdd在Lineage依赖方面划分成两种依赖：窄依赖（Narrow Dependencies)与宽依赖，根据父RDD分区是对应1个还是多个子RDD分区来区分窄依赖(父分区对应一个子分区)和宽依赖(父分区对应多个子分区)

    容错原理:
    在容错机制中，如果一个节点死机了，而且运算窄依赖，则只要把丢失的父RDD分区重算即可，不依赖于其他节点。而宽依赖需要父RDD的所有分区都存在，重算就很昂贵了。
    可以这样理解开销的经济与否：在窄依赖中，在子RDD的分区丢失、重算父RDD分区时，父RDD相应分区的所有数据都是子RDD分区的数据，并不存在冗余计算。
    在宽依赖情况下，丢失一个子RDD分区重算的每个父RDD的每个分区的所有数据并不是都给丢失的子RDD分区用的，会有一部分数据相当于对应的是未丢失的子RDD分区中需要的数据，这样就会产生冗余计算开销，这也是宽依赖开销更大的原因。
    因此如果使用Checkpoint算子来做检查点，不仅要考虑Lineage是否足够长，也要考虑是否有宽依赖，对宽依赖加Checkpoint是最物有所值的。

###### 12、Spark常用调优方法？
    原则一：避免创建重复的RDD
    原则二：尽可能复用同一个RDD
    原则三：对多次使用的RDD进行持久化
    原则四：尽量避免使用shuffle类算子
    原则五：使用map-side预聚合的shuffle操作
    原则六：使用高性能的算子
    原则七：广播大变量
    原则八：使用Kryo优化序列化性能
    原则九：优化数据结构

###### 13、Spark中宽依赖和窄依赖如何理解？
    Spark中RDD的高效与DAG（有向无环图）有很大的关系，在DAG调度中需要对计算的过程划分Stage，划分的依据就是RDD之间的依赖关系。
    RDD之间的依赖关系分为两种，宽依赖(wide dependency/shuffle dependency)和窄依赖（narrow dependency）。
    
    1)窄依赖:就是指父RDD的每个分区只被一个子RDD分区使用，子RDD分区通常只对应常数个父RDD分区
    窄依赖有分为两种：
    OneToOneDependency: 一种是一对一的依赖
    RangeDependency:范围的依赖，它仅仅被org.apache.spark.rdd.UnionRDD使用。
    UnionRDD是把多个RDD合成一个RDD，这些RDD是被拼接而成，即每个parent RDD的Partition的相对顺序不会变，只不过每个parent RDD在UnionRDD中的Partition的起始位置不同
    
    2)宽依赖:就是指父RDD的每个分区都有可能被多个子RDD分区使用，子RDD分区通常对应父RDD所有分区。

###### 14、Spark中Job和Task如何理解？
    Task: 被分配到一个Executor上的计算单元
    Job: 由多个任务组成的并行计算阶段，因RDD的Action产生

###### 15、Spark中Transformation和action区别是什么？列举出常用的方法？
    spark中的数据都是抽象为RDD的，它支持两种类型的算子操作：Transformation和Action。
    Transformation算子的代码不会真正被执行。只有当我们的程序里面遇到一个action算子的时候，代码才会真正的被执行。
    
    Transformation算子主要包括：map、mapPartitions、flatMap、filter、union、groupByKey、repartition、cache等。
    Action算子主要包括：reduce、collect、show、count、foreach、saveAsTextFile等。

###### 16、Spark中persist()和cache()的区别？
    RDD 可以使用 persist() 方法或 cache() 方法进行持久化。数据将会在第一次 action 操作时进行计算，并缓存在节点的内存中。
    Spark 的缓存具有容错机制，如果一个缓存的 RDD 的某个分区丢失了，Spark 将按照原来的计算过程，自动重新计算并进行缓存。
    
    // Persist this RDD with the default storage level (`MEMORY_ONLY`).
    def persist(): this.type = persist(StorageLevel.MEMORY_ONLY)
    
    // Persist this RDD with the default storage level (`MEMORY_ONLY`).
    def cache(): this.type = persist()

###### 17、Spark中map和mapPartitions的区别？
    map是对rdd中的每一个元素进行操作.比如一个partition中有1万条数据。ok，那么你的function要执行和计算1万次。
    mapPartitions则是对rdd中的每个分区的迭代器进行操作.function一次接收所有的partition数据。

###### 18、Spark中Worker和Executor的异同？
     Master和Worker是Spark的守护进程，即Spark在特定模式下正常运行所必须的进程。Driver和Executor是临时程序，当有具体任务提交到Spark集群才会开启的程序。
     Master:
     Spark特有资源调度系统的leader,掌握整个系统的资源信息.例似于Yarn中的RM
     1)监听worker,查看worker工作是否正常
     2)对worker和application进行管理(接收worker注册信息并管理所有worker节点,接收client提交的application信息,调度等待的application并向worker提交)
     
     Worker:
     Spark特有资源调度系统的slave,每个节点掌握着节点的所有资源信息,例似于Yarn中的NM
     1)通过RegisterWorker注册到Master
     2)定时发送心跳给Master
     3)根据master发送的application配置进程环境,并启动ExecutorBackend(执行task需要的临时进程)

###### 19、Spark中提供的2中共享变量是啥？
    Spark为此提供了两种共享变量，一种是Broadcast Variable（广播变量），另一种是Accumulator（累加变量）。
    
    Broadcast Variable会将使用到的变量，仅仅为每个节点拷贝一份，更大的用处是优化性能，减少网络传输以及内存消耗。
    Accumulator主要用于多个节点对一个变量进行共享性的操作。Accumulator只提供了累加的功能。但是确给我们提供了多个task对一个变量并行操作的功能。
    但是task只能对Accumulator进行累加操作，不能读取它的值。只有Driver程序可以读取Accumulator的值。

###### 20、菲波那切数列可以用Spark做出来么？
    可以

###### 21、看过哪些Spark源码？
    1).作业提交流程,deploy模块
    2).作业初始化,sparkcontext流程
    3).内存模块
    4).存储模块
    5).执行模块
    6).数据集RDD模块
    7).数据shuffle模块
###### 22、Spark通信机制？
    1).通信方式:
        1.6版本之前Spark的通信机制只采用Akka通信框架；
        1.6版本之后加入Netty通信框架，并通过配置的方式允许用户自定义使用哪种通信方式；
        2.0版本之后把Akka去掉，只保留了Netty。
    2).RPC通信协议:
        网络通信需要遵守相同的通信协议，Spark通信采用的RPC通信协议。
        RPC即远程过程调用协议，一种通过网络从远程计算机上请求服务而不需要了解底层网络传输技术的协议。
    Akka:
        Akka是一个开发库和运行环境，可以用于构建高并发、分布式、可容错、事件驱动的基于JVM的应用。使构建高并发的分布式应用更加容易。Akka最重要的是它的Actor模型。
    Netty:
        Netty是一款异步的事件驱动的网络应用程序框架，支持快速地开发可维护的高性能的面向协议的服务器和客户端。

###### 23、Spark的存储级别有哪些？
    MEMORY_ONLY : 将RDD以反序列化Java对象的形式存储在JVM中。如果内存空间不够，部分数据分区将不再缓存，在每次需要用到这些数据时重新进行计算。这是默认的级别。
    MEMORY_AND_DISK : 将RDD以反序列化Java对象的形式存储在JVM中。如果内存空间不够，将未缓存的数据分区存储到磁盘，在需要使用这些分区时从磁盘读取。
    MEMORY_ONLY_SER : 将RDD以序列化的Java对象的形式进行存储（每个分区为一个byte数组）。
                      这种方式会比反序列化对象的方式节省很多空间，尤其是在使用fast serializer时会节省更多的空间，但是在读取时会增加 CPU 的计算负担。
    MEMORY_AND_DISK_SER : 类似于MEMORY_ONLY_SER，但是溢出的分区会存储到磁盘，而不是在用到它们时重新计算。
    DISK_ONLY : 只在磁盘上缓存RDD。
    MEMORY_ONLY_2，MEMORY_AND_DISK_2，等等 : 与上面的级别功能相同，只不过每个分区在集群中两个节点上建立副本。
    OFF_HEAP（实验中）: 类似于 MEMORY_ONLY_SER ，但是将数据存储在 off-heap memory，这需要启动 off-heap 内存。

###### 24、Spark序列化模式有哪些？
    spark是分布式的计算框架，其中涉及到了 rpc 的通信和中间数据的缓存。spark为了高效率的通信和减少数据存储空间，会把数据先序列化，然后处理。
    
    Java序列化: 在默认情况下，Spark采用Java的ObjectOutputStream序列化一个对象。
               该方式适用于所有实现了java.io.Serializable的类。
               Java序列化非常灵活，但是速度较慢，在某些情况下序列化的结果也比较大。
    Kryo序列化: Kryo 是 Spark 引入的一个外部的序列化工具, 可以增快 RDD 的运行速度,
               因为 Kryo 序列化后的对象更小, 序列化和反序列化的速度非常快
               Kryo不但速度极快, 而且产生的结果更为紧凑（通常能提高10倍）。
               Kryo的缺点是不支持所有类型, 为了更好的性能, 需要提前注册程序中所使用的类（class）。
    数据压缩:   当指定了spark.io.compression.codec配置的值后，spark会选择对应的压缩方式。
               目前压缩方式支持三种方式， lz4， lzf， snappy。

###### 25、Spark使用到的安全协议有哪些？
    SecurityManager主要对账号、权限及身份认证进行设置和管理。

###### 26、Spark部署模式有哪些？
    Spark 支持多种分布式部署模式，主要支持三种部署模式，分别是：Standalone、Spark on YARN和 Spark on Mesos模式。
    
    Standalone模式为Spark自带的一种集群管理模式，即独立模式，自带完整的服务，可单独部署到一个集群中，无需依赖任何其他资源管理系统。
    它是 Spark 实现的资源调度框架，其主要的节点有 Driver 节点、Master 节点和 Worker 节点。Standalone模式也是最简单最容易部署的一种模式。
    
    Spark on YARN模式，即 Spark 运行在Hadoop YARN框架之上的一种模式。
    Hadoop YARN（Yet Another Resource Negotiator，另一种资源协调者）是一种新的 Hadoop 资源管理器，它是一个通用资源管理系统，可为上层应用提供统一的资源管理和调度。
    
    Spark on Mesos模式，即 Spark 运行在Apache Mesos框架之上的一种模式。
    Apache Mesos是一个更强大的分布式资源管理框架，负责集群资源的分配，它允许多种不同的框架部署在其上，包括YARN。它被称为是分布式系统的内核。

###### 27、Spark的cache后能不能接其它算子？是不是action操作?
    cache可以接其他算子，但是接了算子之后，起不到缓存应有的效果，因为会重新触发cache。
    cache类算子的返回值必须复制给一个变量，在接下来的job中，直接使用这个变量就能读取到内存中缓存的数据。
    cache不是action操作。

###### 28、Spark中reduceByKey是action算子不？reduec呢?
    不是，很多人都会以为是action，reduce rdd是action

###### 29、Spark中数据本地性是哪个阶段确定的？
    dag划分stage的时候，确定的具体的task运行在哪台机器上

###### 30、Spark中RDD的弹性提现在哪里？
    1）自动的进行内存和磁盘的存储切换；
    2）基于Lingage的高效容错；
    3）task如果失败会自动进行特定次数的重试；
    4）stage如果失败会自动进行特定次数的重试，而且只会计算失败的分片；
    5）checkpoint和persist，数据计算之后持久化缓存
    6）数据调度弹性，DAG TASK调度和资源无关
    7）数据分片的高度弹性

###### 31、Spark中容错机制？
    1）.数据检查点,会发生拷贝，浪费资源
    2）.记录数据的更新，每次更新都会记录下来，比较复杂且比较消耗性能

###### 32、Spark中RDD的缺陷？
    1）不支持细粒度的写和更新操作（如网络爬虫），spark写数据是粗粒度的
    所谓粗粒度，就是批量写入数据，为了提高效率。但是读数据是细粒度的也就是
    说可以一条条的读
    2）不支持增量迭代计算，Flink支持

###### 33、Spark中有哪些聚合类的算子？应该避免什么类型的算子?
    避免使用 reduceByKey、join、distinct、repartition 等会进行 shuffle 的算子

###### 34、Spark中并行度怎么设置比较合理一些？
    并行度和数据规模无关,和内存与CPU有关.
    每个core承载2-4个partition

###### 35、Spark中数据的位置由谁来管理？
    每个数据分片都对应具体物理位置，数据的位置被blockManager管理

###### 36、Spark中数据本地性有哪几种？
    Spark中的数据本地性有三种：
    1).PROCESS_LOCAL是指读取缓存在本地节点的数据
    2).NODE_LOCAL是指读取本地节点硬盘数据
    3).ANY是指读取非本地节点数据
    通常读取数据PROCESS_LOCAL>NODE_LOCAL>ANY，尽量使数据以PROCESS_LOCAL或NODE_LOCAL方式读取。
    其中PROCESS_LOCAL还和cache有关，如果RDD经常用的话将该RDD cache到内存中，注意，由于cache是lazy的，所以必须通过一个action的触发，才能真正的将该RDD cache到内存中。

###### 37、Spark如何处理不被序列化的数据？
    将不能序列化的内容封装成object

###### 38、Spark中collect功能是啥？其底层是如何实现的?
    driver通过collect把集群中各个节点的内容收集过来汇总成结果，collect返回结果是Array类型的，
    collect把各个节点上的数据抓过来，抓过来数据是Array型，collect对Array抓过来的结果进行合并

###### 39、Spark作业在没有获得足够资源就开始启动了,可能会导致什么问题？
    task的调度线程和Executor资源申请是异步的。
    会导致执行该job时候集群资源不足，导致执行job结束也没有分配足够的资源，分配了部分Executor，该job就开始执行task。
    如果想等待申请完所有的资源再执行job的：
        需要将spark.scheduler.maxRegisteredResourcesWaitingTime设置的很大；
        spark.scheduler.minRegisteredResourcesRatio 设置为1，
    但是应该结合实际考虑否则很容易出现长时间分配不到资源，job一直不能运行的情况。

###### 40、Spark中map和flatmap有啥区别？
    map：是将函数用于RDD中的每个元素，将返回值构成新的RDD。
    flatMap：是将函数应用于RDD中的每个元素，将返回的迭代器的所有内容构成新的RDD,这样就得到了一个由各列表中的元素组成的RDD,而不是一个列表组成的RDD。

###### 41、介绍一下join操作优化经验？
    join其实常见的就分为两类： map-side join 和 reduce-side join。
    当大表和小表join时，用map-side join能显著提高效率。将多份数据进行关联是数据处理过程中非常普遍的用法，不过在分布式计算系统中，这个问题往往会变的非常麻烦，
    因为框架提供的join操作一般会将所有数据根据key发送到所有的reduce分区中去，也就是shuffle的过程。造成大量的网络以及磁盘IO消耗，运行效率极其低下，这个过程一般被称为reduce-side-join。
    如果其中有张表较小的话，我们则可以自己实现在 map 端实现数据关联，跳过大量数据进行 shuffle 的过程，运行时间得到大量缩短，根据不同数据可能会有几倍到数十倍的性能提升。
    
    热掉数据再分区
    key先聚合再join

###### 42、Spark有哪些组件？
    Application：基于 Spark 的用户程序，即由用户编写的调用 Spark API 的应用程序，它由集群上的一个驱动（Driver）程序和多个执行器（Executor）程序组成。
                其中应用程序的入口为用户所定义的 main 方法。
    SparkContext：是 Spark 所有功能的主要入口点，它是用户逻辑与 Spark 集群主要的交互接口。
                  通过SparkContext，可以连接到集群管理器（Cluster Manager），能够直接与集群 Master 节点进行交互，并能够向 Master 节点申请计算资源，
                  也能够将应用程序用到的 JAR 包或 Python 文件发送到多个执行器（Executor）节点上。
    Cluster Manager：即集群管理器，它存在于 Master 进程中，主要用来对应用程序申请的资源进行管理。
    Worker Node：任何能够在集群中能够运行 Spark 应用程序的节点。
    Task：由SparkContext发送到Executor节点上执行的一个工作单元。
    Driver：也即驱动器节点，它是一个运行Application中main()函数并创建SparkContext的进程。
            Driver节点也负责提交Job，并将Job转化为Task，在各个Executor进程间协调 Task 的调度。Driver节点可以不运行于集群节点机器上。
    Executor：也即执行器节点，它是在一个在工作节点（Worker Node）上为Application启动的进程，它能够运行 Task 并将数据保存在内存或磁盘存储中，也能够将结果数据返回给Driver。
    
###### 43、Spark的工作机制？
    一、应用执行机制
    Driver进程运行在客户端（Client模式）：
    即用户在客户端直接运行程序。 
    程序的提交过程大致会经过以下阶段：
    1、用户运行程序。
    2、启动Driver进行（包括DriverRunner和SchedulerBackend），并向集群的Master注册。
    3、Driver在客户端初始化DAGScheduler等组件。
    4、Woker节点向Master节点注册并启动Executor（包括ExecutorRunner和ExecutorBackend）。
    5、ExecutorBackend启动后，向Driver内部的SchedulerBackend注册，使得Driver可以找到计算节点。
    6、Driver中的DAGScheduler解析RDD生成Stage等操作。
    7、Driver将Task分配到各个Executor中并行执行。
    8、Driver进程运行在集群中（某个Worker节点，Cluster模式）：
    
    即用户将Spark程序提交给Master分配执行。 
    大致会经过一下流程：
    1、用户启动客户端，提交Spark程序给Master。
    2、Master针对每个应用分发给指定的Worker启动Driver进行。
    3、Worker收到命令之后启动Driver进程（即DriverRunner和其中的SchedulerBackend），并向Master注册。
    4、Master指定其他Worker启动Executor（即ExecutorRunner和其内部的ExecutorBackend）。
    5、ExecutorBackend向Driver中的SchedulerBackend注册。
    6、Driver中的DAGScheduler解析RDD生产Stage等。
    7、Executor内部启动线程池并行化执行Task。

###### 44、Spark中的宽窄依赖？
    参考: 13、Spark中宽依赖和窄依赖如何理解？

###### 45、Spark如何划分stage？
    Shuffle是产生宽依赖RDD的算子，例如reduceByKey、reparttition、sortByKey等算子。同一个Stage内的所有Transformation算子所操作的RDD都是具有相同的Partition数量的。
    Stage划分基于数据依赖关系的，一般分为两类：宽依赖（Shuffle Dependency）与窄依赖（Narrow Dependency）。
    宽依赖，父RDD的一个分区会被子RDD的多个分区使用。
    窄依赖，父RDD的分区最多只会被子RDD的一个分区使用。
    区分宽窄依赖，我们主要从父RDD的Partition流向来看：流向单个RDD就是窄依赖，流向多个RDD就是宽依赖。
    
    Spark Stage划分，就是从最后一个RDD往前推算，遇到窄依赖（NarrowDependency）就将其加入该Stage，当遇到宽依赖（ShuffleDependency）则断开。
    每个Stage里task的数量由Stage最后一个RDD中的分区数决定。如果Stage要生成Result，则该Stage里的Task都是ResultTask，否则是ShuffleMapTask。
    
    ShuffleMapTask的计算结果需要shuffle到下一个Stage，其本质上相当于MapReduce中的mapper。Result Task则相当于MapReduce中的reducer。
    因此整个计算过程会根据数据依赖关系自后向前建立，遇到宽依赖则形成新的Stage。

    Stage的调度是由DAG Scheduler完成的。由RDD的有向无环图DAG切分出了Stage的有向无环图DAG。
    Stage以最后执行的Stage为根进行广度优先遍历，遍历到最开始执行的Stage执行，如果提交的Stage仍有未完成的父Stage，则Stage需要等待其父Stage执行完才能执行。

###### 46、spark-submit时候如何引用外部的jar包？
    方法一：spark-submit –jars
    根据spark官网，在提交任务的时候指定–jars，用逗号分开。
    命令：spark-submit --master yarn-client --jars ***.jar,***.jar
    
    方法二：extraClassPath
    提交时在spark-default中设定参数，将所有需要的jar包考到一个文件里，然后在参数中指定该目录就可以
    spark.executor.extraClassPath=/home/hadoop/wzq_workspace/lib/* 
    spark.driver.extraClassPath=/home/hadoop/wzq_workspace/lib/*

###### 47、Spark中RDD有哪些特性？
    RDD（Resilient Distributed Dataset）叫做分布式数据集，是Spark中最基本的数据抽象，它代表一个不可变、可分区、里面的元素可并行计算的集合。
    1，分区列表( a list of partitions)
    Spark RDD是被分区的，每一个分区都会被一个计算任务(Task)处理，分区数决定了并行计千算的数量，RDD的并行度默认从父RDD传给子RDD。
    2，每一个分区都有一个计算函数( a function for computing each split）
    3，依赖于其他RDD的列表( a list of dependencies on other RDDS)
    4，key- value数据类型的RDD分区器( a Partitioner for key- alue RDDS)、控制分区策略和分区数
    5，每个分区都有一个优先位置列表( a list of preferred locations to compute each split on）

###### 48、Spark的一个工作流程？
    （1）任何spark的应用程序都包含Driver代码和Executor代码。Spark应用程序首先在Driver初始化SparkContext。
    因为SparkCotext是Spark应用程序通往集群的唯一路径，在SparkContext里面包含了DAGScheduler和TaskScheduler两个调度器类。在创建SparkContext对象的同时也自动创建了这两个类。
    （2）SparkContext初始化完成后，首先根据Spark的相关配置，向Cluster Master申请所需要的资源，然后在各个Worker结点初始化相应的Executor。
    Executor初始化完成后，Driver将通过对Spark应用程序中的RDD代码进行解析，生成相应的RDD graph（RDD图），该图描述了RDD的相关信息及彼此之间的依赖关系。
    （3）RDD图构建完毕后，Driver将提交给DAGScheduler进行解析。DAGScheduler在解析RDD图的过程中，当遇到Action算子后将进行逆向解析，根据RDD之间的依赖关系以及是否存在shuffle等，
    将RDD图解析成一系列具有先后依赖关系的Stage。Stage以shuffle进行划分，即如果两个RDD之间存在宽依赖的关系，DAGScheduler将会在这RDD之间拆分为两个Stage进行执行，
    且只有在前一个Stage（父Stage）执行完毕后，才执行后一个Stage。
    （4）DAGScheduler将划分的一系列的Stage（TaskSet），按照Stage的先后顺序依次提交给底层的调度器TaskScheduler去执行。
    （5）TaskScheduler接收到DAGScheduler的Stage任务后，将会在集群环境中构建一个 TaskSetManager 实例来管理Stage（TaskSet） 的生命周期。
    （6）TaskSetManager将会把相关的计算代码、数据资源文件等发送到相应的Executor上，并在相应的Executor上启动线程池执行。TaskSetManager在执行过程中，使用了一些优化的算法，
    用于提高执行的效率，譬如根据数据本地性决定每个Task***位置、推测执行碰到Straggle任务需要放到别的结点上重试、出现shuffle输出数据丢失时要报告fetch failed错误等机制。
    （7）在Task执行的过程中，可能有部分应用程序涉及到I/O的输入输出，在每个Executor由相应的BlockManager进行管理，相关BlockManager的信息将会与Driver中的Block tracker进行交互和同步。
    （8）在TaskThreads执行的过程中，如果存在运行错误、或其他影响的问题导致失败，TaskSetManager将会默认尝试3次，尝试均失败后将上报TaskScheduler，TaskScheduler如果解决不了，
    再上报DAGScheduler，DAGScheduler将根据各个Worker结点的运行情况重新提交到别的Executor中执行。
    （9）TaskThreads执行完毕后，将把执行的结果反馈给TaskSetManager，TaskSetManager反馈给TaskScheduler，TaskScheduler再上报DAGScheduler，
    DAGScheduler将根据是否还存在待执行的Stage，将继续循环迭代提交给TaskScheduler去执行。
    （10）待所有的Stage都执行完毕后，将会最终达到应用程序的目标，或者输出到文件、或者在屏幕显示等，Driver的本次运行过程结束，等待用户的其他指令或者关闭。
    （11）在用户显式关闭 SparkContext后，整个运行过程结束，相关的资源被释放或回收。

###### 49、SparkOnYarn与standalone区别？
     1、Yarn 支持动态资源配置。
     2、Standalone 模式只支持简单的固定资源分配策略，每个任务固定数量的 core，各 Job 按顺序依次分配在资源，资源不够的时候就排队。
     这种模式比较适合单用户的情况，多用户的情境下，会有可能有些用户的任务得不到资源。
     Yarn 作为通用的种子资源调度平台，除了 Spark 提供调度服务之外，还可以为其他系统提供调度，如 Hadoop MapReduce, Hive 等。

###### 50、Spark优化之内存管理？
    1.设置cache
    2.设置jvm堆外内存

###### 51、Spark优化之广播变量？

###### 52、Spark优化之数据本地性？

###### 53、Spark中task有几种类型？
    有2种:
    resultTask类型，最后一个task
    shuffleMapTask类型，除了最后一个task都是
###### 54、Spark中repartition和coalesce区别?
    1)spark分区partition的理解：
    spark中是以vcore级别调度task的。
        如果读取的是hdfs，那么有多少个block，就有多少个partition
        举例来说：sparksql 要读表T, 如果表T有1w个小文件，那么就有1w个partition
        这时候读取效率会较低。假设设置资源为 --executor-memory 2g --executor-cores 2 --num-executors 5。
        步骤是拿出1-10号10个小文件（也就是10个partition） 分别给5个executor读取（spark调度会以vcore为单位，实际就是5个executor，10个task读10个partition）
        如果5个executor执行速度相同，再拿11-20号文件 依次给这5个executor读取,而实际执行速度不会完全相同，那就是哪个task先执行完，哪个task领取下一个partition读取执行，
        以此类推。这样往往读取文件的调度时间大于读取文件本身，而且会频繁打开关闭文件句柄，浪费较为宝贵的io资源，执行效率也大大降低。
    2)coalesce与repartition的区别:
    repartition(numPartitions:Int):RDD[T]和coalesce(numPartitions:Int，shuffle:Boolean=false):RDD[T]  
    repartition只是coalesce接口中shuffle为true的实现

    3)例子:
    有1w的小文件，资源也为--executor-memory 2g --executor-cores 2 --num-executors 5。
        repartition(4)：产生shuffle。这时会启动5个executor像之前介绍的那样依次读取1w个分区的文件，然后按照某个规则%4,写到4个文件中，这样分区的4个文件基本毫无规律，比较均匀。
        coalesce(4):这个coalesce不会产生shuffle。那启动5个executor在不发生shuffle的时候是如何生成4个文件呢，其实会有1个或2个或3个甚至更多的executor在空跑
        （具体几个executor空跑与spark调度有关，与数据本地性有关，与spark集群负载有关），他并没有读取任何数据！

    我们常认为coalesce不产生shuffle会比repartition 产生shuffle效率高，而实际情况往往要根据具体问题具体分析，coalesce效率不一定高:
    coalesce与repartition 他们两个都是RDD的分区进行重新划分，repartition只是coalesce接口中shuffle为true的实现（假设源RDD有N个分区，需要重新划分成M个分区）
    1）如果N<M。一般情况下N个分区有数据分布不均匀的状况，利用HashPartitioner函数将数据重新分区为M个，这时需要将shuffle设置为true(repartition实现,coalesce也实现不了)。
    2）如果N>M并且N和M相差不多，(假如N是1000，M是100)那么就可以将N个分区中的若干个分区合并成一个新的分区，最终合并为M个分区，这时可以将shuff设置为false（coalesce实现）
    3）如果N>M并且两者相差悬殊，这时你要看executor数与要生成的partition关系，如果executor数小于等于要生成partition数，coalesce效率高，
    反之如果用coalesce会导致(executor数-要生成partiton数)个excutor空跑从而降低效率。

---
参考:
* [1.Spark性能优化指南——基础篇](https://endymecy.gitbooks.io/spark-config-and-tuning/content/meituan/spark-tuning-basic.html)
* [2.Spark性能优化指南——高级篇](https://endymecy.gitbooks.io/spark-config-and-tuning/content/meituan/spark-tuning-pro.html)
