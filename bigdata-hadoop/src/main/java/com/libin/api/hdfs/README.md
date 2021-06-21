
## HDFS使用
    在大数据开发中，对HDFS的使用无处不在，大部分计算和存储框架直接与HDFS交互,因此学习HDFS很重要.
    RD(Research and Development engineer)们一般都喜欢通过命令行进行操作HDFS,那敲键盘的感觉很爽...当然代码API操作也是必须的.
    PM(Product Manager)们一般不会研发那点儿东东,更多使用自研的数据管理平台或HUE之类的进行网页查看数据...
##
##### 1.HDFS命令行使用
    之前博客整理的文档：https://blog.csdn.net/baolibin528/article/details/43854291  
    
###### 1.1 显示hdfs目录
    hdfs dfs -ls hdfs://192.168.1.100:9000/user/hadoop/input

###### 1.2 创建目录
    hdfs dfs -mkdir hdfs://192.168.1.100:9000/user/hadoop/input

###### 1.3 上传本地文件到hdfs目录
    hdfs dfs -put baozi.txt hdfs://192.168.1.100:9000/user/hadoop/input
    hdfs dfs -copyFromLocal baozi.txt hdfs://192.168.1.100:9000/user/hadoop/input
    hdfs dfs -moveFromLocal baozi.txt hdfs://192.168.1.100:9000/user/hadoop/input

###### 1.4 从hdfs目录下载文件到本地
    hdfs dfs -get hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -copyToLocal hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -moveToLocal hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt

###### 1.5 查看hdfs目录文本内容
    hdfs dfs -text hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -cat hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -text hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt | more
    hdfs dfs -text hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt | grep "xxx" | more
    hdfs dfs -text hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt | awk -F "\t" '{print $1}' | sort | uniq | more
    hdfs dfs -text hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt | awk -F "\t" '{print $1}' | sort | uniq | wc -l

###### 1.6 删除hdfs目录
    hdfs dfs -rm hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -rm -r hdfs://192.168.1.100:9000/user/hadoop/input/
    hdfs dfs -skipTrash -rm -r hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt

###### 1.7 设置hdfs目录ACL权限
    hdfs dfs -setfacl -m user:kerberos_name:rwx hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt
    hdfs dfs -setfacl -m default:user:kerberos_name:rwx hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt

###### 1.8 查看hdfs目录配额
    hdfs dfs -count -q hdfs://192.168.1.100:9000/user/hadoop/input/

###### 1.9 拷贝hdfs目录文件
    hdfs dfs -cp hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt hdfs://192.168.1.100:9000/user/hadoop/output
    hdfs dfs -mv hdfs://192.168.1.100:9000/user/hadoop/input/baozi.txt hdfs://192.168.1.100:9000/user/hadoop/output


##
##### 2.[HDFS的Java API使用](HdfsUtils.java)
    之前博客整理的文档：https://blog.csdn.net/baolibin528/article/details/43868515
