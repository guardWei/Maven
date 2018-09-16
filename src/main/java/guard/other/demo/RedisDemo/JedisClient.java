package guard.other.demo.RedisDemo;

import redis.clients.jedis.Jedis;

public class JedisClient {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        System.out.printf(jedis.set("abcd","1"));
    }
}


/*
RedisServer is starting...

RedisServer is started...

客户端发过来的内容:
*3
$3
SET
$3
abc
$1
1
---------------------------------------------
redis通信协议为resp协议，也是应用层协议 RESP协议内容 https://redis.io/topics/protocol
\r\n换行作为每段数据的分隔方式

" + "  单行信息
" - "  错误信息
" ："  整形数字
" $ "  多行字符串
" * "  数组

注：掌握通信协议可以应用在哪些场景：
    1.客户端开发
    2.实现Redis代理（分布式Redis解决方案，通过分片存储内存无限大）
    3.实现哨兵机制
        前提：主从模式
        Redis master服务器挂掉后，哨兵会选举一个slave节点晋升为maser，以实现集群的高可用
        a.master主从
            ./redis-server --port 6380
            ./redis-server --port 6381 --slaveof 127.0.0.1 6380
            ./redis-server --port 6382 --slaveof 127.0.0.1 6380
        b.启动Redis自带的哨兵
            哨兵配置详解 http://doc.redisfans.com/topic/sentinel.html
            ./redis-server ./sentinel.conf --sentinel
        c.自己实现一个Java版的哨兵服务

---------------------------------------------
Redis特性：
    1.使用简单
    2.高性能（每秒处理量轻松上万）
    3.支持多种数据结构（String 字符串、Hash 键值对、List 列表可重复、Set集合不重复、Sorted Set有序集合）
    4.方案成熟（分布式/高可用等场景下游刃有余）a.集群分片存储（Redis Cluster/Codis/Twemproxy）b.HA高可用（sentinel） c.主从复制（master/slave模式）
*/
