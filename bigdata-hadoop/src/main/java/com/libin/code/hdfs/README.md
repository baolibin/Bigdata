## HDFS源码阅读
    选用的版本是基于hadoop2.6.0版本，对这个版本还是蛮钟爱的。

##
##### 1.HDFS客户端
    HDFS目前提供3个客户端访问操作接口：
    1.DistributedFileSystem：(org.apache.hadoop.hdfs)为用户开发提供基于HDFS的应用操作API。
    2.FsShell：(org.apache.hadoop.fs)可以通过HDFS Shell命令执行常见的文件系统操作。
    3.DFSAdmin：(org.apache.hadoop.hdfs.tools)向系统管理员提供管理HDFS的工具,如升级、管理安全模式等。
    上面3个接口都是直接或间接持有DFSClient(org.apache.hadoop.hdfs)提供的接口方法对HDFS进行管理和操作的。

* DFSAdmin是一个真正实现分布式文件系统客户端功能的类，使用户进行HDFS操作的起点。    
* DFSAdmin会连接到HDFS，对外提供关联文件/目录，读写文件以及管理与配置HDFS系统等功能。  
* DFSAdmin通过ClientProtocol(org.apache.hadoop.hdfs.protocol)接口调用NameNode的接口。
* DFSAdmin通过DataTransferProtocol(org.apache.hadoop.hdfs.protocol.datatransfer)与DataNode交互数据。  

##
##### 2.RPC通信



##
##### 3.NameNode


##
##### 4.DataNode
