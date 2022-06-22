# MVCC 多版本并发控制

DB_TRX_ID 事务id 全局自增
DB_ROLL_PTR 指向事务回滚的上一条记录

CURREN_ID 表示当前事务ID，则：
1. > CURREN_ID 看不到，因为相当于未来的修改。当前read-view中并没有
2. == CURREN_ID 可以看到，自己修改的自己肯定能看到
3. < CURREN_ID 若已提交可以看到，若未提交则看不到

Read-View 

[当前系统最小的活跃事务id,   当前事务id] 这个集合用来计算上述逻辑

对于可重复读的隔离级别，Read-View 在begin到 commit整个过程中不变，所以能看到的事务也从一而终，
一定程度上避免幻读（快照读的情况下  select）
如果是当前读（update insert delete之后再select），因为会重新建立Read-View，有可能造成前后不一致的情况

