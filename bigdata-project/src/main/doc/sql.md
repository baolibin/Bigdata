* [9.14、SQL]()
    - [1)、SQL执顺序？]()
    - [2)、排名函数ROW_NUMBER,RANK,DENSE_RANK啥区别？]()
    - [3)、用一条sql语句查询出每门课都大于80分的学生姓名？]()
    - [4)、sql窗口函数？]()
    - [5)、sql窗口函数使用？]()
    - [6)、sql中条件函数使用？]()
    - [7)、sql优化？]()
    - [8)、sql常用函数？]()
    - [9)、sql explain的用法？]()
    - [10)、sql各种join？]()
    - [11)、sql的行转列，列转行？]()
    - [12)、sql如何解析json格式数据？]()
    - [13)、Mysql如何建立索引？]()
    - [14)、sql编写到执行的过程？]()

---
###### 1、SQL执顺序？
    SQL定义顺序：
    (1) SELECT 
    (2) DISTINCT<select_list>
    (3) FROM <left_table>
    (4) <join_type> JOIN <right_table>
    (5) ON <join_condition>
    (6) WHERE <where_condition>
    (7) GROUP BY <group_by_list>
    (8) WITH {CUBE|ROLLUP}
    (9) HAVING <having_condition>
    (10) ORDER BY <order_by_condition>
    (11) LIMIT <limit_number>
    
    SQL执行顺顺序：
    (8) SELECT 
    (9) DISTINCT<select_list>
    (1) FROM <left_table>
    (3) <join_type> JOIN <right_table>
    (2) ON <join_condition>
    (4) WHERE <where_condition>
    (5) GROUP BY <group_by_list>
    (6) WITH {CUBE|ROLLUP}
    (7) HAVING <having_condition>
    (10) ORDER BY <order_by_list>
    (11) LIMIT <limit_number>

###### 2、排名函数ROW_NUMBER,RANK,DENSE_RANK啥区别？
    row_number() over() : 排名函数，不会重复，适合于生成主键或者不并列排名
    rank() over() :  排名函数，有并列名次，名次不连续。如:1,1,3
    dense_rank() over() : 排名函数，有并列名次，名次连续。如：1，1，2
    
    row_number就是1,2,3,4(不存在并列的情况下的排序)
    rank就是1,1,1,4(按照占据的人数排序)
    dense_rank就是1,1,1,2(按照分数的名次排序)

###### 3、用一条sql语句查询出每门课都大于80分的学生姓名？

###### 4、sql窗口函数
    窗口函数，也叫OLAP函数（Online Anallytical Processing，联机分析处理），可以对数据库数据进行实时分析处理。
    
    select <列名>
    <窗口函数> over (partition by <用于分组的列名>
                    order by <用于排序的列名>)
    from <表名>
    
    <窗口函数>的位置，可以放以下两种函数：
    1） 专用窗口函数，包括后面要讲到的rank, dense_rank, row_number等专用窗口函数。
    2） 聚合函数，如sum. avg, count, max, min等

    窗口函数和group by+order by组合的区别：group by 分组汇总改变行数，partition by分组汇总行数不变
    窗口函数功能：
    （1）同时具有分组和排序的功能
    （2）不减少原表的行数

###### 5、sql窗口函数使用
    如何找到每个类别下用户最喜欢的产品是哪个
    如何找到每个类别下用户最不喜欢的产品是哪个
    如何找到每个类别下用户点击最多的5个产品是什么
    
    -- 求每个课程成绩最大值（关联子查询），取成绩最大值所在行数据(where 成绩 =(关联子查询))
    select *
    from score as a
    where 成绩 = (select max(成绩) from score as b where a.课程号=b.课程号);

    每位同学成绩最好的两门课目：
    select *
    from(select *,row_number() over (partition by 姓名
                       order by 成绩 DESC) as ranking from score) as a
    where ranking <=2

    万能模板
    select *,row_number over (partition by 要分组的列名 order by 要排序的列名 desc) as ranking 
    from score
    where ranking<=N;

###### 6、sql子查询

###### 7、sql优化

###### 8、sql常用函数

###### 9、sql explain的用法

###### 10、sql各种join

###### 11、sql的行转列，列转行

###### 12、sql如何解析json格式数据

###### 13、Mysql如何建立索引？

###### 14、sql编写到执行的过程

###### 15、请用户访问网站最长连续天数，数据格式user、dt、page。

###### 16、查询同时在线人数的峰值
    select t2.live_id, max(t2.cnt) as max_online_cnt
    from
      (select t1.live_id, sum(t1.indexx) over(partition by t1.live_id order by t1.time) as cnt
    from
      (select live_id, login_time as time, 1 as indexx from live_table where dt = '20220801'
      union all
      select live_id, logout_time as time, -1 as indexx from live_table where dt = '20220801'
      ) t1
    ) t2
    group by t2.live_id
