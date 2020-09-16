

##### 1、HBase逻辑视图基本概念
    行键、列簇、列限定符、单元格、时间戳
    1）、table：表，一个表包含多行数据。
    2）、row：行，一行数据包含一个唯一标识rowkey、多个column以及对应的值。
    在HBase中，一张表中所有row都按照rowkey的字典序由小到大排序。
    3）、column：列，与关系型数据库中的列不同，HBase中的column由columnfamily（列簇）
    以及qualif ier（列名）两部分组成，两者中间使用":"相连。
    4）、timestamp：时间戳，每个cell在写入HBase的时候都会默认分配一个时间戳作为该cell的版本，
    当然，用户也可以在写入的时候自带时间戳。
    5）、cell：单元格，由五元组（row, column, timestamp, type, value）组成的结构，
    其中type表示Put/Delete这样的操作类型，timestamp代表这个cell的版本。
    这个结构在数据库中实际是以KV结构存储的，其中（row, column,timestamp, type）是K，value字段对应KV结构的V。
    




