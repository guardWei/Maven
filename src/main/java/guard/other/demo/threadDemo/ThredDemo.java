package guard.other.demo.threadDemo;

public class ThredDemo {
    public static void main(String[] args) {
    	System.out.println(Thread.currentThread().getName());
		//创建一个线程对象
    	MyThread t1 = new MyThread();
    	MyThread t2 = new MyThread();
    	//t1.run(); //直接调用不是启动线程，而是在本线程中执行方法
    	t1.start();//线程已经准备就绪，等待CPU调度
    	t2.start();
    	
    	
    	
    	MyRunnable r = new MyRunnable();
    	Thread t3 = new Thread(r);
    	t3.setDaemon(true);//设置为守护线程
    	t3.start();
	}
    /**
     * 继承Thread实现线程
     * @author guard
     *
     */
    static class MyThread extends Thread{
    	public void run() {
    		//在次方法中编写线程要执行的工作
    		for(int i = 0;i<10;i++){
    			System.out.println(Thread.currentThread().getName()+"***"+System.currentTimeMillis()+"--------"+i);
    		}
    	}
    }
    
    /**
     * 通过实现Runnable接口的方式实现线程
     * @author guard
     *
     */
    static class MyRunnable implements Runnable{

		public void run() {
			//在次方法中编写线程要执行的工作
    		for(int i = 0;i<10;i++){
    			System.out.println(Thread.currentThread().getName()+"***"+System.currentTimeMillis()+"--------"+i);
    		}
		}
    	
    }
}
