package guard.other.demo.threadDemo;

public class ThredDemo1 {
    public static void main(String[] args) {
		MyThread t1 = new MyThread("一号窗口");
		MyThread t2 = new MyThread("二号窗口");
		MyThread t3 = new MyThread("三号窗口");
		
		t1.start();
		t2.start();
		t3.start();
		System.out.println("-------------------------");
		/*------------------------------------------------------*/
		
		MyRunable r = new MyRunable();
		Thread t4 = new Thread(r, "一号窗口");
		Thread t5 = new Thread(r, "二号窗口");
		Thread t6 = new Thread(r, "三号窗口");
		
		t4.start();
		t5.start();
		t6.start();
	}
	
	
	/**
	 * 使用继承Thread类，多个线程分别完成自己的任务
	 * @author guard
	 * @date 2016年4月7日15:28:15
	 */
	static class MyThread extends Thread{
		private int ticket = 10;
		private String name;
		
		public MyThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
		    for(int i=0;i<30;i++){
		    	if(ticket>0){
		    		System.out.println(this.name+"售票-----"+(this.ticket--));
		    	}else {
					System.out.println(this.name+"---票已售完----");
				}
		    }	
		}
	}
	
	/**
	 * 实现Runnable接口，多个线程共同完成一个任务
	 * @author guard
	 * @date 2016年4月7日15:43:29
	 */
	static class MyRunable implements Runnable{
        private int ticket=10;		
		
		@Override
		public void run() {
			for(int i=0;i<30;i++){
				if(ticket>0){
					System.out.println(Thread.currentThread().getName()+"售票-----"+(this.ticket--));
				}else{
					System.out.println("---票已售完----");
				}
			}
		}
	}
	
}
