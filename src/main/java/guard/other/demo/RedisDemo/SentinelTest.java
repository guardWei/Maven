package guard.other.demo.RedisDemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class SentinelTest {
    public static void main(String[] args) {
        Set<String> sentinels = new HashSet<String>();
        //配置哨兵服务器的信息
        sentinels.add("127.0.0.1:26379");

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("master",sentinels);
        //去哨兵服务器查询当前的master信息
        //命令：SENTINEL get-master-addr-by-name master
        Jedis jedis = jedisSentinelPool.getResource();
        //执行redis命令
        jedis.set("hello_sentinel_1","my_sentinel_1");
    }
}
