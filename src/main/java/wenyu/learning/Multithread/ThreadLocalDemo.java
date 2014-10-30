package wenyu.learning.Multithread;

class MyThread implements Runnable {
	public String threadName;
	public String normalStr;
	public ThreadLocal<String> threadLocalStr;
	private Object lock;
	
	public MyThread(String name) {
		threadName = name;
		threadLocalStr = new ThreadLocal<String>();
		lock = new Object();
	}
	
	public void run() {
		threadLocalStr.set(Thread.currentThread().getName());
		normalStr=Thread.currentThread().getName();
		
		synchronized (lock) {
			/*
			 * From the result:
			 * All the thread local value is different and same as thread name. 
			 * But some of the normal value is same. Since normalStr is not thread-safe
			 */
			System.out.print("Current Thread: " + Thread.currentThread().getName());
			System.out.print(" / Thread Local Value:" + threadLocalStr.get());
			System.out.println(" / Normal Value: " + normalStr);
		}
	}
}

public class ThreadLocalDemo {
	public ThreadLocalDemo demo = new ThreadLocalDemo();
	
	public static void main(String[] args) {
		MyThread myThread = new MyThread("MyThread");
		
		for(int i=0;i<10;i++) {
			new Thread(myThread).start();
		}
	}
}