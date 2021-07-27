* [9.14、SQL]()
    - [1）、SQL执顺序？]()
    - [2）、排名函数ROW_NUMBER,RANK,DENSE_RANK啥区别？]()

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

###### 3、用一条sql语句查询出每门课都大于80分的学生姓名？
    

###### 4、sql窗口函数
