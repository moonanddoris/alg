## 原理

redis是单线程工作，如果使用keys命令进行线上的执行，它是O(n)的算法，且没有limit限制。
这意味着如果key很多的话，很容易会导致进程阻塞。

因此redis提供了scan命令，使用增量迭代的方式进行遍历。

## 使用方法

SCAN cursor MATCH pattern COUNT count

cursor是遍历的游标位置。特别的 count并不是符合条件的个数，而是遍历的个数。

## 底层

```shell
127.0.0.1:6379> keys *
1) "db_number"
2) "key1"
3) "myKey"
   127.0.0.1:6379> scan 0 MATCH * COUNT 1
1) "2"
2) 1) "db_number"
      127.0.0.1:6379> scan 2 MATCH * COUNT 1
1) "1"
2) 1) "myKey"
      127.0.0.1:6379> scan 1 MATCH * COUNT 1
1) "3"
2) 1) "key1"
      127.0.0.1:6379> scan 3 MATCH * COUNT 1
1) "0"
2) (empty list or set)
```

可以发现，迭代使用scan命令的时候，cursor返回并不是顺序递增
顺序是 0 2 1 3
这是因为scan命令是按照高位进位的顺序来遍历的 00 10 01 11
