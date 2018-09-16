package guard.other.demo.RedisDemo;

import redis.clients.jedis.Jedis;

/**
 * 实现不同长度的key存在不同的服务器
 */
public class RedisProxyTest {
    //代理服务器IP
    private  static String proxyHost = "127.0.0.1";
    //代理服务器端口
    private  static int proxyPort = 19000;

    public static void main(String[] args) {
        Jedis jedis = new Jedis(proxyHost,proxyPort);

        System.out.println(jedis.set("abc","123"));

        jedis.close();
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        System.out.println(jedis.set("1","2"));

    }
}
