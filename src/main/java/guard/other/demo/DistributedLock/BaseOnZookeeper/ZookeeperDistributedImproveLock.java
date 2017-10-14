package guard.other.demo.DistributedLock.BaseOnZookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
/**
 * 基于zookeeper的分布式锁的一个升级版实现
 * @author guard
 * @date 2017年9月12日20:09:08
 */
public class ZookeeperDistributedImproveLock implements Lock {

	private static final String LOCK_PATH = "/LOCK";

	private static final String ZOOKEEPER_IP_PORT = "localhost:2181";

	private ZkClient zkClient = new ZkClient(ZOOKEEPER_IP_PORT, 1000, 1000, new SerializableSerializer());

	private CountDownLatch cdl = null;

	private String currentPath;// 当前请求的节点
	private String beforePath;// 当前请求的节点前一个节点

	// 判断有没有/Lock目录，没有则创建
	public ZookeeperDistributedImproveLock() {
		if (!this.zkClient.exists(LOCK_PATH)) {
			this.zkClient.createPersistent(LOCK_PATH);
		}
	}

	@Override
	public void lock() {
		if (tryLock()) {//如果拿到锁则抢到资源继续后面的业务
			System.out.println(Thread.currentThread().getName() + ": 获得分布式锁！");
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
				System.out.println(
						Thread.currentThread().getName() + ":----------------get data delete event-------------------");
				if (cdl != null) {
					cdl.countDown();
				}

			}

			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				// TODO Auto-generated method stub

			}
		};
		//给排在前面的节点增加数据删除的watcher
		this.zkClient.subscribeDataChanges(beforePath, listener);

		if (this.zkClient.exists(beforePath)) {
			try {
				cdl = new CountDownLatch(1);
				cdl.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.zkClient.unsubscribeDataChanges(beforePath, listener);
	}

	@Override
	// 实现非阻塞式的加锁
	public boolean tryLock() {
		// 如果currentPath为空则为第一次尝试加锁，第一次加锁赋值currentPath
		if (currentPath == null || currentPath.length() <= 0) {
			// 创建第一个临时顺序节点
			currentPath = this.zkClient.createEphemeralSequential(LOCK_PATH + "/", "lock");
			System.out.println("------------------" + currentPath);
		}
		// 获取所有临时节点并排序，临时节点名称为自增长的字符如：000000040
		List<String> childrens = this.zkClient.getChildren(LOCK_PATH);
		System.out.println(Thread.currentThread().getName() + ":currentPath " + currentPath);
		System.out.println(Thread.currentThread().getName() + ":排序前"+childrens);
		Collections.sort(childrens);
		System.out.println(Thread.currentThread().getName() + ":排序后"+childrens);


		if (currentPath.equals(LOCK_PATH + "/" + childrens.get(0))) {// 如果当前节点在所有节点中排名第一则获取锁成功
			return true;
		} else {// 如果当前节点在所有节点中排名不是第一，则获取前面的节点名称，并赋值给beforePath
			int wz = Collections.binarySearch(childrens, currentPath.substring(6));//利用二分查找当前节点在所有节点列表中的位置
			beforePath = LOCK_PATH + "/" + childrens.get(wz - 1);//当前节点的前一个节点
		}
		return false;
	}

	@Override
	public void unlock() {
		// 删除当前临时节点
		zkClient.delete(currentPath);
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
/*
/LOCK  下面的顺序临时节点
[0000000001, 0000000000, 0000000003, 0000000002, 0000000005, 0000000004, 0000000007, 0000000006, 0000000009, 0000000008]

(注意：这种方式下不能使用静态的锁，否则currentPath将一直是最后一个节点)
*/
