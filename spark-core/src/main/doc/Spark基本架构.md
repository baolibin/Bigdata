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

