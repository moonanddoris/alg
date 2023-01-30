
## ONLY_FULL_GROUP_BY的问题

如果数据库开启了sql_mode=only_full_group_by 的选项，在使用group by时有限制，如果不符合要求会报错
具体限制是：

* 如果select后的字段，不在group by的字段中 如：select name, age from student group by age;
* 上一条，对聚合列没有影响
* 在没有group by的语句中，直接使用聚合函数也会报错 select id, max(id) from student;

解决办法：

* 直接修改配置，去掉only_full_group_by的严格限制
* 使用ANY_VALUE