package guard.other.demo.threadDemo.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞的线程安全队列，N个线程和一个线程执行的结果一致
 * synchronized修饰非静态方法、同步代码块的synchronized (this)用法和synchronized (非this对象)的用法锁的是对象，线程想要执行对应同步代码，需要获得对象锁。
 * synchronized修饰静态方法以及同步代码块的synchronized (类.class)用法锁的是类，线程想要执行对应同步代码，需要获得类锁
 * 参考：1.https://blog.csdn.net/u013142781/article/details/51697672
 *      2.https://www.jianshu.com/p/25e243850bd2?appinstall=0
 * @author guard
 * @date 2018年09月16日11:25:46
 */
public class BlockingQueue {
    //用来存放队列元素
    private List<String> queueElementList = new ArrayList<String>();

    //队列的最大值
    private int maxSize;

    //队列的锁（只有在synchronize对象上才能使用wait 不然就会报monitor错误 java.lang.IllegalMonitorStateException）
    private Object lock = BlockingQueue.class;//类锁
    //private Object lock = new.Object();//对象锁

    //队列初始化
    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        System.out.println(Thread.currentThread().getName()+"队列初始化完成。。。。大小是：" + maxSize);
    }

    public void put(String element){
        synchronized (lock){
            try {
                if(this.queueElementList.size() == this.maxSize){
                    System.out.println(Thread.currentThread().getName() + "队列已满，PUT等待中。。。。");
                    lock.wait();//当线程执行wait()方法时候，会释放当前的锁，然后让出CPU，进入等待状态。
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            this.queueElementList.add(element);
            System.out.println(Thread.currentThread().getName()+"向队列中添加了元素"+element);
            lock.notifyAll();
        }
    }

    public void get(){
        synchronized (lock){
           try {
               if(this.queueElementList.size()==0){
                   System.out.println(Thread.currentThread().getName() + "队列已空，GET等待中。。。。");
                   lock.wait();
               }
           } catch (Exception e){
               e.printStackTrace();
           }
            String element = queueElementList.get(0);
            queueElementList.remove(0);
            System.out.println(Thread.currentThread().getName() + "取出了元素" +element);
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        final BlockingQueue blockingQueue = new BlockingQueue(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueue.put("1");
                blockingQueue.put("2");
                blockingQueue.put("3");
                blockingQueue.put("4");
                blockingQueue.put("5");
                blockingQueue.put("6");
                blockingQueue.put("7");
                blockingQueue.put("8");
                try {
                    Thread.sleep(2000);
                }catch (Exception e){

                }
                blockingQueue.put("9");
            }
        },"Thread Put").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
                blockingQueue.get();
            }
        },"Thread Get").start();
    }
}
