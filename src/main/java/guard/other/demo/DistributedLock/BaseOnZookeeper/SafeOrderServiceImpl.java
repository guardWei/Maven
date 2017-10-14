package guard.other.demo.DistributedLock.BaseOnZookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用锁来保证订单服务的线程安全
 * @author guard
 * @data 2017年10月14日11:32:08
 */
public class SafeOrderServiceImpl implements Runnable {
    private static OrderCodeGenerator ocg = new OrderCodeGenerator();

    //同时并发的线程数
    private static final int NUM = 10;

    //按照线程数初始化倒计数器
    private static CountDownLatch cdl = new CountDownLatch(NUM);//一个同步辅助类,用来模拟NUM个并发请求

    //方法1：使用jdk自带的lock，但是不适用于分布式服务即跨虚拟机的多线程
    //private static Lock lock = new ReentrantLock();

    //方法2：使用基于zk的分布式锁的实现
    //private Lock lock = new ZookeeperDistributedLock();
    //方法3：使用基于zk的分布式锁的实现，升级后的实现当前节点仅关注当前节点的前面一个节点的监听事件(注意：这种方式下不能使用静态的锁，否则currentPath将一直是最后一个节点)
    private Lock lock = new ZookeeperDistributedImproveLock();

    private void createOrder() {
        String orderCode = null;

        lock.lock();
        try {
            //生成订单编号
            orderCode = ocg.getOrderCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//防止获取订单号异常而导致死锁
        }

        //...业务代码省略

        System.out.println(Thread.currentThread().getName() + "==========" + orderCode);

    }

    @Override
    public void run() {
        try {
            //等待其他其他线程初始化
            cdl.await();//await() 方法会一直阻塞直到计数器为0，主线程才会继续往下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        createOrder();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= NUM; i++) {
            new Thread(new SafeOrderServiceImpl()).start();
            //创建一个线程，倒计数器减1
            cdl.countDown();//countDown() 方法将计数器减1
        }
    }
}
