package guard.other.demo.RedisDemo;

import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * http://doc.redisfans.com/topic/sentinel.html
 * http://www.redis.net.cn/order/3679.html
 * https://segmentfault.com/q/1010000002898134
 * http://blog.csdn.net/lee_nacl/article/details/62044097
 */
public class RedisSentinel {
    private static String master;
    //所有的slave
    private static final Vector<String> slaveRedisServers = new Vector<String >();
    //所有已经宕机的redis
    private static final Vector<String> badRedisServers = new Vector<String>();



    public static void main(String[] args) throws Exception{
        //配置redis master
        config("127.0.0.1:6380");
        //定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //检查master是否正常
                checkMaster();
                //更新slave列表
                updateSlaves();
                //检测宕机的服务是否恢复正常
                checkBadServer();
            }
        }, 3000L,3000L);

        //开启端口接收请求
        open();
    }

    private static void config(String ms){
        master = ms;
    }

    private static void checkMaster(){
        System.out.print("检查master状态：" + master);
        String masterHost = master.split(":")[0];
        int masterPort = Integer.parseInt(master.split(":")[1]);

        try {
            Jedis jedis = new Jedis(masterHost,masterPort);
            jedis.ping();
            jedis.close();
        } catch (Exception e) {
            System.out.println(master + ".......已宕机！");
            //master挂掉
            badRedisServers.add(master);
            //切换master
            chanageMaster();
        }

    }


    private static void chanageMaster(){
        Iterator<String> iterator = slaveRedisServers.iterator();
        while (iterator.hasNext()){
            String slave = iterator.next();

            try {
                String slaveHost = slave.split(":")[0];
                int slavePort = Integer.parseInt(slave.split(":")[1]);

                Jedis jedis = new Jedis(slaveHost,slavePort);
                jedis.slaveofNoOne();//将指定的从服务器升级为主服务器
                jedis.close();
                master = slave;
                System.out.println("产生新的master：" + master);
                break;
            } catch (Exception e) {
                badRedisServers.add(slave);
            } finally {
                iterator.remove();
            }
        }

        //所有的slave切换到新的master
        for(String slave: slaveRedisServers){
            String slaveHost = slave.split(":")[0];
            int slavePort = Integer.parseInt(slave.split(":")[1]);
            Jedis jedis = new Jedis(slaveHost,slavePort);

            jedis.slaveof(master.split(":")[0],Integer.parseInt(master.split(":")[1]));
            jedis.close();
        }
    }

    private static void updateSlaves(){

        try {
            String masterHost = master.split(":")[0];
            int masterPort = Integer.parseInt(master.split(":")[1]);

            Jedis jedis = new Jedis(masterHost,masterPort);
            String infoReplication = jedis.info("replication");
            //解析 infoReplication
            String[] lines = infoReplication.split("\r\n");
            int slaveCount = Integer.parseInt(lines[2].split(":")[1]);
            if(slaveCount > 0){
                slaveRedisServers.clear();
                for(int i = 0;i < slaveCount;i++){
                    String port = lines[3 + i].split(",")[1].split("=")[1];
                    slaveRedisServers.add("127.0.0.1:" + port);
                }
            }
            System.out.println("更新slave列表："+ Arrays.toString(slaveRedisServers.toArray(new String[] {})));
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新slave失败："+e.getMessage());
        }
    }

    private static void checkBadServer(){
        Iterator<String> iterator = badRedisServers.iterator();
        while (iterator.hasNext()){
            String bad = iterator.next();

            try {
                String badHost = bad.split(":")[0];
                int badPort = Integer.parseInt(bad.split(":")[1]);
                Jedis badServer = new Jedis(badHost,badPort);
                badServer.ping();

                //如果能ping通则表示服务恢复，需要挂在当前mater下
                badServer.slaveof(master.split(":")[0],Integer.parseInt(master.split(":")[1]));
                badServer.close();

                slaveRedisServers.add(bad);
                iterator.remove();
                System.out.println(bad + " 恢复正常，当前master："+master);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private static void open() throws Exception{
        //SENTINEL get-master-addr-by-name master
        ServerSocket sentlnel = new ServerSocket(26379);
        System.out.println("Java版本哨兵服务启动成功：" + 26379);
        Socket socket = null;
        while ((socket = sentlnel.accept())!=null){
            try {
                while (true){
                    System.out.println("一个链接。。。。");
                    InputStream inputStream = socket.getInputStream();
                    byte[] request = new byte[1024];
                    inputStream.read(request);
                    //解析get-master-addr-by-name请求
                    String req = new String(request);
                    System.out.println("收到请求：");
                    System.out.println(req);
                    System.out.println("-------------结束---------------");
                    System.out.println();

                    String[] param = req.split("\r\n");
                    if("get-master-addr-by-name".equals(param[4])){
                        //返回结果为当前的master信息
                        /*
                            *2\r\n
                            $9\r\n
                            127.0.0.1
                            $4
                            6380
                         */
                        String result = "*2\r\n"
                                +"$9\r\n"
                                + master.split(":")[0] + "\r\n" //master host
                                + "$4\r\n"
                                + master.split(":")[1] + "\r\n"; //master port
                        socket.getOutputStream().write(result.getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
