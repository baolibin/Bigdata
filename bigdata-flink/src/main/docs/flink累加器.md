

##### 1.累加器
    Accumulator即累加器，与MapReduce中Counter的应用场景差不多，都能很好地观察Task在运行期间的数据变化。
    可以在Flink Job的算子函数中使用累加器，但是只有在任务执行结束之后才能获得累加器的最终结果。
    Counter是一个具体的累加器实现，常用的Counter有IntCounter、LongCounter和DoubleCounter。
    
    
    