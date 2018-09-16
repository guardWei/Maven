package guard.other.demo.RedisDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 手写的一个简易redis服务
 * 著名的redis相关开源项目
 *  1.Jedis https://github.com/xetorthio/jedis
 *  2.Redisson https://github.com/redisson/redisson
 *  3.推特开源 https://github.com/twitter/twemproxy
 *  4.豌豆荚团队开源 https://github.com/CodisLabs/codis
 *  5.官方高可用方案：sentinel哨兵
 * @author guard
 * @date 2017年11月12日16:16:34
 */
public class RedisServer {
    public static final int PORT = 6379;//监听的端口号


    public static void main(String[] args) {

        System.out.println("RedisServer is starting...\n");
        RedisServer server = new RedisServer();
        server.init();
    }

    public void init() {
        try {
            //创建一个ServerSocket，这里可以指定连接请求的队列长度
            //new ServerSocket(port,3);意味着当队列中有3个连接请求，如果Client再请求连接，就会被Server拒绝
            ServerSocket redisServerSocket = new ServerSocket(PORT, 3);
            System.out.println("RedisServer is started...\n");
            while (true) {
                Socket socket = redisServerSocket.accept();

                new HandlerThread(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            PrintStream out = null;
            try {
                inputStream = socket.getInputStream();
                byte[] inputByte = new byte[1024];
                inputStream.read(inputByte);

                System.out.println("客户端发过来的内容:\r\n" + new String(inputByte));

                // 向客户端回复信息
                out = new PrintStream(socket.getOutputStream());
                out.println("+ OK\r\n");

            } catch (IOException e) {
                System.out.println("服务器异常: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
/*

    BufferedReader input = null;
    PrintStream out = null;
    // 读取客户端数据
    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    StringBuffer clientInputStr=new StringBuffer();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
    for(String inputLineStr = input.readLine();inputLineStr != null;){
        clientInputStr.append(inputLineStr);
    }
    // 处理客户端数据
    System.out.println("客户端发过来的内容:" + clientInputStr.toString());

    System.out.print("请输入:\t");
    String inStr = new BufferedReader(new InputStreamReader(System.in)).readLine();
    out.println(inStr);


    服务器端这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
    Exception in thread "Thread-0" java.lang.OutOfMemoryError: Java heap space
        at java.util.Arrays.copyOf(Arrays.java:3332)
        at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
        at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
        at java.lang.StringBuffer.append(StringBuffer.java:270)
        at guard.other.demo.RedisDemo.RedisServer$HandlerThread.run(RedisServer.java:58)
        at java.lang.Thread.run(Thread.java:748)
*/