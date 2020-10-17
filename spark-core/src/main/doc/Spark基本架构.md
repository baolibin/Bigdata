##### Spark基本架构
    从集群部署的角度来看，Spark集群由集群管理器（Cluster Manager）、工作节点（Worker）、执行器（Executor）、驱动器（Driver）、应用程序（Application）等部分组成
    
    （1）Cluster Manager
    Spark的集群管理器，主要负责对整个集群资源的分配与管理。
    Cluster Manager在YARN部署模式下为ResourceManager；
    在Mesos部署模式下为Mesos Master；
    在Standalone部署模式下为Master。
    Cluster Manager分配的资源属于一级分配，它将各个Worker上的内存、CPU等资源分配给Application，但是并不负责对Executor的资源分配。
    
    （2）Worker
    Spark的工作节点。在YARN部署模式下实际由NodeManager替代。
    Worker节点主要负责以下工作：
    将自己的内存、CPU等资源通过注册机制告知ClusterManager；
    创建Executor；
    将资源和任务进一步分配给Executor；
    同步资源信息、Executor状态信息给Cluster Manager等。
    
    （3）Executor
    执行计算任务的一线组件。主要负责任务的执行及与Worker、Driver的信息同步。

    （4）Driver
    Application的驱动程序，Application通过Driver与Cluster Manager、Executor进行通信。
    
    （5）Application
    用户使用Spark提供的API编写的应用程序，Application通过Spark API将进行RDD的转换和DAG的构建，
    并通过Driver将Application注册到ClusterManager。

    Cluster Manager将会根据Application的资源需求，
    通过一级分配将Executor、内存、CPU等资源分配给Application。
    Driver通过二级分配将Executor等资源分配给每一个任务，Application最后通过Driver告诉Executor运行任务。

##### Spark核心模块
    （1）SparkContext
    SparkContext隐藏了网络通信、分布式部署、消息通信、存储体系、计算引擎、度量系统、文件服务、Web UI等内容，
    应用程序开发者只需要使用SparkContext提供的API完成功能开发。
    （2）SparkEnv
    Spark执行环境SparkEnv是Spark中的Task运行所必需的组件。
    SparkEnv内部封装了RPC环境（RpcEnv）、序列化管理器、广播管理器（BroadcastManager）、map任务输出跟踪器（MapOutputTracker）、
    存储体系、度量系统（MetricsSystem）、输出提交协调器（OutputCommitCoordinator）等Task运行所需的各种组件。
    （3）调度系统
    调度系统主要由DAGScheduler和TaskScheduler组成，它们都内置在SparkContext中。
    DAGScheduler负责创建Job、将DAG中的RDD划分到不同的Stage、给Stage创建对应的Task、批量提交Task等功能。
    TaskScheduler负责按照FIFO或者FAIR等调度算法对批量Task进行调度；为Task分配资源；将Task发送到集群管理器的当前应用的Executor上，由Executor负责执行等工作。
    
