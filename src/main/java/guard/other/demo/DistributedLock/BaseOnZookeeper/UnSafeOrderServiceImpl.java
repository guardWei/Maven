package guard.other.demo.DistributedLock.BaseOnZookeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch类来模拟一个不安全的订单服务
 * @author guard
 * @data 2017年10月14日11:27:14
 */
public class UnSafeOrderServiceImpl implements Runnable {
    private static OrderCodeGenerator ocg = new OrderCodeGenerator();

    //同时并发的线程数
    private static final int NUM = 100;

    //按照线程数初始化倒计数器
    private static CountDownLatch cdl = new CountDownLatch(NUM);//一个同步辅助类,用来模拟NUM个并发请求

    private void createOrder() {
        String orderCode = null;

        //生成订单编号
        orderCode = ocg.getOrderCode();

        //...业务代码省略

        System.out.println(Thread.currentThread().getName() + "==========" + orderCode);

    }

    @Override
    public void run() {
        try {
            cdl.await();//await() 方法会一直阻塞直到计数器为0，主线程才会继续往下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        createOrder();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= NUM; i++) {
            new Thread(new UnSafeOrderServiceImpl()).start();
            cdl.countDown();//countDown() 方法将计数器减1
        }
    }
}
/*
 * 可以看到存在并发情况下订单重复的情况
 *
Thread-46==========orderId45
Thread-50==========orderId49
Thread-43==========orderId43
Thread-44==========orderId43
Thread-40==========orderId40
Thread-38==========orderId38
Thread-36==========orderId36
Thread-62==========orderId61
Thread-34==========orderId34
Thread-65==========orderId64
*/