
## MapReduce应用API整理
    刚开始学习Hadoop时候使用的版本是hadoop0.20.2，但是Hadoop版本迭代蛮快的。
    学习时候主要使用的是Hadoop1.2.1和Hadoop2.6.0两个版本，工作时候基本都是用Hadoop2.0开发的，也就是Hadoop2.6.0版本。
    下面的代码基本是基于Hadoop2.6.0编写的，大部分都是工作中的实战代码。
    年限基本都是在2015年使用的，那个时候Spark也是刚兴起不久，很多公司还是编写MapReduce居多。

* [1、分布式缓存](DistributedDemo.java)
* [2、全排序](allSort)
* [3、多目录输出](TestwithMultipleOutputs.java)
* [4、Hadoop的map获取当前spilt文件名](GetInputSplit.java)
* [5、自定义InputFormat 类代码](inputformat)
* [6、TopK](Topk.java)
* [7、二次排序](SecondarySort.java)
* [8、自定义Partitioner](PartitionerDemo.java)
* [9、MultipleInputs用法](MultipleInputsTest.java)
* [10、CombineTextInputFormat用法](CombineTextInputFormatTest.java)
* [11、NLineInputFormat用法](NLineInputFormatTest.java)
* [12、SequenceFileInputFormat用法](SequenceFileInputFormatTest.java)
* [13、MapReduce编程自定义排序](SortTest.java)
* [14、DBInputFormat用法](DBInputFormatTest.java)
* [15、自定义计数器](CounterTest.java)
* [16、Hadoop自定义数据类型](KpiApp.java)
* [17、经典WordCount](WordCount.java)
* [18、Hadoop的ChainMapper/ChainReducer](ChainMapperChainReducer.java)
* [19、hadoop的Context简单使用](GetIDMapReduce.java)
* [20、hadoop的FileSplit简单使用](GetSplitMapReduce.java)
* [21、Hadoop的FileStatus简单使用](GetStatusMapReduce.java)
* [22、Hadoop自定义分组Group](MyGroup.java)
* [23、Hadoop的PathFilter使用](TextPathFilterDemo.java)
