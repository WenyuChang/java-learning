package wenyu.learning.Multithread.ExecutorDemo;

import java.util.concurrent.Executor;

class ExecutorImp implements Executor {
	public String name;
	
	public ExecutorImp(String name) {
		this.name = name;
	}
	
	/**
	 * More typically, tasks are executed in some 
	 * thread other than the caller's thread. The 
	 * executor below spawns a new thread for each 
	 * task. 
	 */

	public void execute(Runnable command) {
		Thread thread = new Thread(command);
		thread.setName(name);
		thread.start();
	}
}


public class ExecutorImpDemo {
	public static void main(String[] args) {
		Executor executor = new ExecutorImp("Pool-1");
		executor.execute(new MyRunnable("runnable-1"));
		executor.execute(new MyRunnable("runnable-2"));
		executor.execute(new MyRunnable("runnable-3"));
		executor.execute(new MyRunnable("runnable-5"));
		executor.execute(new MyRunnable("runnable-6"));
		executor.execute(new MyRunnable("runnable-7"));
		executor.execute(new MyRunnable("runnable-8"));
		executor.execute(new MyRunnable("runnable-9"));
		executor.execute(new MyRunnable("runnable-10"));
	}
}
