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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}