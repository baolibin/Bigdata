* [9.14、SQL]()
    - [1）、SQL执顺序？]()

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
