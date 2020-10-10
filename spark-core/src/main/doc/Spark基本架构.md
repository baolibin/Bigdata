

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
    
    