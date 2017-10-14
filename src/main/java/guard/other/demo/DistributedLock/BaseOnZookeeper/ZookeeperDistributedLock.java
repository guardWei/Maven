package guard.other.demo.DistributedLock.BaseOnZookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * 基于zookeeper的分布式锁的一个实现
 * 存在的问题：1.客户端无端接收了很多与自己无关的事件通知(通知消息过多)
 * 2.存在死锁的可能(创建的是持久化的节点，所以当网络或者其他原因导致客户端没有收到通知时存在死锁的可能)
 *
 * @author guard
 * @date 2017年9月12日20:08:33
 */
public class ZookeeperDistributedLock implements Lock {

    private static final String LOCK_NODE = "/LOCK";

    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    private ZkClient zkClient = new ZkClient(ZOOKEEPER_IP_PORT, 1000, 1000, new SerializableSerializer());

    private CountDownLatch cdl = null;

    @Override
    public void lock() {
        if (tryLock()) {//如果拿到锁则抢到资源继续后面的业务
            return;
        }
        waitForLock();//如果没有抢到锁则阻塞等待

        lock();// 收到锁被释放即有节点被删除事件时，递归调用尝试去加锁
    }


    //阻塞式的实现
    private void waitForLock() {
        // 给节点加监听
        IZkDataListener listener = new IZkDataListener() {

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("----------------get data delete event-------------------");
                if (cdl != null) {
                    cdl.countDown();//节点被删除时通知阻塞的线程继续运行
                }

            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                // TODO Auto-generated method stub

            }
        };

        zkClient.subscribeDataChanges(LOCK_NODE, listener);//当前线程的zk客户端订阅这个监听事件
        if (zkClient.exists(LOCK_NODE)) {//监听事件后如果发现之前线程创建的节点依然存在，则开始阻塞等待
            try {
                cdl = new CountDownLatch(1);
                cdl.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(LOCK_NODE, listener);//阻塞的线程被唤起后取消订阅之前的监听事件
    }

    @Override
    // 实现非阻塞式的加锁
    public boolean tryLock() {
        try {
            zkClient.createPersistent(LOCK_NODE);//因为此处创建的是持久化的节点，所以当网络或者其他原因导致客户端没有收到通知时存在死锁的可能
            return true;
        } catch (ZkNodeExistsException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void unlock() {
        zkClient.delete(LOCK_NODE);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }
}
