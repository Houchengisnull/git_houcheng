[TOC]

# 安装

- **参考**
- [Windows下安装MySQL详细教程](https://www.jianshu.com/p/e676799ed3af)

## mysqld --initialize

- **初始化**

``` mysql
# 生成随机密码 root用户 的初始化
mysqld --initialize
# 生成空密码 root用户 的初始化
mysqld --initialize-insecure
```

初始化将创建`root`用户及`data`文件夹

## mysqld --console

可用于在mysql server 发生错误时查看错误信息。有效排除错误。

## mysqld --install

- 将`mysql`注册为`Windows`服务：

``` mysql
mysqld --install %server_name%
```

- 卸载`mysql`服务

``` mysql
mysqld --uninstall %server_name%
```

## 启动

``` bash
# 启动mysql服务
net start mysql

# 关闭mysql服务
net stop mysql 
```

- **参考**
- [MySQL 成功安装，但是无法启动](https://bbs.csdn.net/topics/391950830)
- [MYSQL服务无法启动，服务没有任何错误；解决方法](https://blog.csdn.net/qq_37915248/article/details/82631398)

## Faq

### 缺少环境组件redistrubutable

- 报错信息

  `This application requires Visual Studio 2019 Redistributable. Please install the Redistributable then run this installer again.`

`mysql`运行在`VC++`环境下，而`redistrubutable`为`VC++`环境必要组件。

- 参考

- [mysql8安装This application requires Visual Studio 2019 Redistributable问题及连接navicat时1251问题的解决](https://blog.csdn.net/mengjie0617/article/details/105148847/)

- `redistrubutable`下载地址

  [The latest supported Visual C++ downloads](https://support.microsoft.com/en-us/topic/the-latest-supported-visual-c-downloads-2647da03-1eea-4433-9aff-95f26a218cc0)

# 修改密码

进入`mysql`客户端后，输入：

``` mysql
alter user 'root'@'localhost' identified by 'new_password'
```

或者使用`mysqladmin`程序修改：

``` mysql
mysqladmin -u root -p password newPassword
```

# 最大连接数和修改最大连接数

<https://blog.csdn.net/wzygis/article/details/52461007>

# 接收数据包大小查看与设置

show VARIABLES like '%max_allowed_packet%’

> 设置方式1 : set global max_allowed_packet = #{max_allowed_packet};
> 设置方式2 : 修改配置文件 my.cfg

# 显示所有与mysql连接进程

show full processlist

# Err:1205 Lock wait timeout exceeded

<https://www.cnblogs.com/cchust/p/3585847.html>

## 普通方式

  show full processlist;
  kill #{pid}

## 升级1

```
/* 显示当前事务 */
select * from information_schema.INNODB_trx; 
select * from information_schema.INNODB_locks;
/* 查询锁等待情况 */
select * from information_schema.INNODB_locks_waits;
```

  kill #{pid};

## 升级2

```
SELECT r.trx_id waiting_trx_id,
       r.trx_query waiting_query,
       b.trx_id blocking_trx_id,
       b.trx_query blocking_query,
       b.trx_mysql_thread_id blocking_thread,
       b.trx_started,
       b.trx_wait_started
FROM information_schema.innodb_lock_waits w
       INNER JOIN information_schema.innodb_trx b
               ON b.trx_id = w.blocking_trx_id
       INNER JOIN information_schema.innodb_trx r
               ON r.trx_id = w.requesting_trx_id
```

# 日志

## 查看日志

show variables like 'log_%';
show variables like 'general%';

## 开启日志

set GLOBAL general_log = 'OFF'

## 查看二进制日志(用于恢复database)

show master log; show binary log;

## 查询慢查询属性

show VARIABLES LIKE '%slow%';
查询慢查询限制时间
show VARIABLES LIKE 'long_query_time';
开启慢查询日志
set GLOBAL slow_query_log = OFF;
查询执行时间95名之后statements
select * from sys.statements_with_runtimes_in_95th_percentile ORDER BY avg_latency desc;

# 性能查询

> SHOW　GLOBAL STATUS LIKE 'Questions';
> SHOW GLOBAL STATUS LIKE 'Com_select';
> SHOW GLOBAL STATUS LIKE 'Com_insert'; SHOW GLOBAL STATUS LIKE 'Com_delete'; SHOW GLOBAL STATUS LIKE 'Com_update'; SHOW GLOBAL STATUS LIKE 'Com_commit'; SHOW GLOBAL STATUS LIKE 'Com_rollback';

# 引擎

## show useful engine

> show engines;

## 显示表引擎

> show table status from #{database};

## 修改表引擎

``` 
ALTER TABLE #{tablename} ENGINE=InnoDB;
ALTER TABLE #{tablename} ENGINE=MEMORY;
```



# MySQL缓存

## 查询缓存开启

> show variables like '%query_cache_type%'

# 索引

## 查看表索引

> show keys from #{table}

## 查看索引

show index from #{table}