
##### 1.分布式缓存
    Flink提供了一个分布式缓存（Distributed Cache），类似于Hadoop，可以使用户在并行函数中很方便地读取本地文件。
    
    此缓存的工作机制为程序注册一个文件或者目录（本地或者远程文件系统，如HDFS或者S3），通过ExecutionEnvironment注册缓存文件并为它起一个名称。
    当程序执行时，Flink自动将文件或者目录复制到所有TaskManager节点的本地文件系统，用户可以通过这个指定的名称查找文件或者目录，
    然后从TaskManager节点的本地文件系统访问它。


