package guard.other.demo.RedisDemo;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟Redis代理（场景：根据key的长度不同，将数据存储到不同的redis服务器上）
 * 分布式redis解决方案，通过分片存储内存无限大
 * @author guard
 * @date 2017年11月12日16:14:07
 */
public class RedisProxy {
    //redis服务器列表
    private static List<String> servers = new ArrayList<String>();

    static {
        servers.add("127.0.0.1:6379");
        servers.add("127.0.0.1:6380");
        servers.add("127.0.0.1:6381");
    }

    //最简单的redis代理实现负载均衡
    public static void main(String[] args) throws Exception {
        System.out.println("启动redis代理服务，端口：" + 19000);

        //监听端口
        ServerSocket serverSocket = new ServerSocket(19000);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            try {
                while (true) {
                    System.out.println("一个链接进入。。。");

                    InputStream inputStream = socket.getInputStream();
                    byte[] request = new byte[1024];
                    inputStream.read(request);

                    String req = new String(request);
                    System.out.println("收到请求：" + req);

                    //根据RESP协议解析请求
                    String[] param = req.split("\r\n");

                    //获取key的长度（第4个参数值为key值的长度,如$3）
                    int keyLenth = Integer.parseInt(param[3].split("\\$")[1]);

                    int mod = keyLenth % servers.size();

                    //根据模结果选择服务器
                    System.out.println("根据负载均衡算法选择服务器：" + servers.get(mod));

                    String[] serverInfo = servers.get(mod).split(":");

                    //代理请求(类似Nginx反向代理)
                    //注：此处可以先去哨兵服务器获取master
                    Socket client = new Socket(serverInfo[0], Integer.parseInt(serverInfo[1]));
                    client.getOutputStream().write(request);

                    //返回结果
                    byte[] response = new byte[1024];
                    //获取redisserver响应
                    client.getInputStream().read(response);
                    client.close();
                    //将响应内容响应给jedis客户端
                    socket.getOutputStream().write(response);

                    System.out.println("-------------------------代理结束---------------------");

                }
            } catch (Exception e) {
                System.out.println("代理发生异常：" + e.getMessage());
                e.printStackTrace();
            } finally {
                socket.close();
            }
        }
    }
}
