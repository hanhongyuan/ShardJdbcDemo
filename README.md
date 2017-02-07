说明：
--------------------------------------
这是当当shard-jdbc的一个demo程序。
resources/sql下的sql语句在两个不同的数据库下执行。

user分库不分表。分库键键为：user_id
<br/>
order分库分表。分库键为：user_id，分表键为：order_id
<br/>
order item分库分表。分库键为：order_id，分表键为：item_id

读写分离
主库：192.168.56.75、192.168.56.76
从库：192.168.56.73、192.168.56.74


















