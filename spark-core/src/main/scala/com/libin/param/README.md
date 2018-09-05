
## Spark常用配置参数


spark作业常用配置参数

| 参数 | 释义 |  
| ---- | ---- |
| executor-memory | executor内存大小 |
| driver-memory | driver内存大小 |
| spark.yarn.executor.memoryOverhead | executor的堆外内存大小设置 |
| spark.yarn.driver.memoryOverhead | driver的堆外内存大小设置  |
| spark.dynamicAllocation.enabled | 是否开启动态资源分配 |
| spark.dynamicAllocation.initialExecutors | 设置启动的时候初始化多少个executors |
| spark.dynamicAllocation.maxExecutors | 控制动态资源分配的上线 |
| spark.dynamicAllocation.minExecutors | 控制动态资源分配的下线 |
| spark.memory.fraction | 存储和计算内存占比 |
| spark.app.name | 应用程序的名字,将在UI和日志数据中出现 |
| executor-cores | executor的core个数 |



