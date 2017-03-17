package wenyu.learning.Multithread.ExecutorDemo;

public class MyRunnable implements Runnable {
	private String runnableName;
	
	public MyRunnable(String name) {
		runnableName = name;
	}
	

	public void run() {
		System.out.println(Thread.currentThread().getName() + "/" + runnableName);
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "/" + runnableName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}