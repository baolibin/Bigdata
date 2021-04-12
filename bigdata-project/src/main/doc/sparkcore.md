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
    - [40）、Spark中map和flatmap有啥区别？](#40、Spark中map和flatmap有啥区别？)
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
###### 2、Spark的内存模型？
    Spark集群在提交应用程序时候会创建Driver和Execotor两种JVM进程。
    Driver内存：Driver作为主控进程，负责创建Spark作业的上下文，将提交的作业Job转化为计算任务Task，分发到Executor进程中进行执行。
    Execotor内存：在工作节点上执行具体的计算任务，并将结果返回给Driver，同时提供RDD的持久化机制。
    
    堆内内存划分
    2.1.1、执行Execution内存
    主要用于存放Shuffle、Join、Sort、Aggregation等计算过程中的临时数据。
    2.1.2、存储Storage内存
    主要用于存储spark的cache数据，例如RDD的缓存、unroll数据；
    2.1.3、用户内存User Memory
    主要用于存储内部元数据、用户自定义的数据结构等，根据用户实际定义进行使用。
    2.1.4、预留内存Reserved Memory
    默认300M的系统预留内存，主要用于程序运行，参见SPARK-12081。
    
    堆外内存划分
    为了进一步优化内存的使用以及提高Shuffle时排序的效率，Spark引入了堆外（Off-heap）内存，
    使之可以直接在工作节点的系统内存中开辟空间，存储经过序列化的二进制数据。
    
    Spark为存储内存和执行内存的管理提供了统一的接口——MemoryManager，同一个 Executor内的任务都调用这个接口的方法来申请或释放内存。
    MemoryManager的具体实现上，Spark 1.6之后默认为统一管理（Unified Memory Manager）方式，
    1.6之前采用的静态管理（Static Memory Manager）方式仍被保留，可通过配置 spark.memory.useLegacyMode 参数启用。
    两种方式的区别在于对空间分配的方式。
    
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

###### 21、看过哪些Spark源码？
    1).作业提交流程,deploy模块
    2).作业初始化,sparkcontext流程
    3).内存模块
    4).存储模块
    5).执行模块
    6).数据集RDD模块
    7).数据shuffle模块
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
    并行度和数据规模无关,和内存与CPU有关.
    每个core承载2-4个partition

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

###### 49、SparkOnYarn与standalone区别？

###### 50、Spark优化之内存管理？

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

