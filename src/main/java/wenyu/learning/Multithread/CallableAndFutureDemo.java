package wenyu.learning.Multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Future Class
 * 	1. cancel(boolean mayInterruptIfRunning)
 * 		Attempts to cancel execution of this task.
 *  2. get()
 *  	Waits if necessary for the computation to complete, and then retrieves its result.
 *  3. get(long timeout, TimeUnit unit)
		Waits if necessary for at most the given time for the computation to complete, 
		and then retrieves its result, if available.
 *  4. isCancelled()
		Returns true if this task was cancelled before it completed normally.
 * 	5. isDone()
		Returns true if this task completed.
 */

class MyRunnable implements Runnable {
	private String name;  
    public MyRunnable(String name) {  
        this.name = name;  
    } 
	
	@Override
	public void run() {
		int count = 10;
		while(count-->0) {
			try {
				Thread.sleep(1000);
			}
			catch(Exception ex) {
				
			}
			System.out.println("MyRunnable(" + name + ") is running...");
		}
	}
}

class MyCallable implements Callable<String> {  
    private String name;  
    public MyCallable(String name) {  
        this.name = name;  
    } 
    
	public String call() throws InterruptedException {
		int count = 10;
		while(count-->0) {
			System.out.println("MyCallable(" + name + ") is running...");
		}
        return Thread.currentThread().getName() + "/" + name;  
    }  
}

public class CallableAndFutureDemo {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void runnableDemoEntry() {
		Future<?> future1 = exec.submit(new MyRunnable("MyRunnable_4"));
		Future<?> future2 = exec.submit(new MyRunnable("MyRunnable_5"));
		Future<?> future3 = exec.submit(new MyRunnable("MyRunnable_6"));
		
		Thread thread1 = new Thread(new MyRunnable("MyRunnable_1"));
		Thread thread2 = new Thread(new MyRunnable("MyRunnable_2"));
		Thread thread3 = new Thread(new MyRunnable("MyRunnable_3"));
		thread1.start();
		thread2.start();
		thread3.start();
		
		try {
			System.out.println("Future_1: " + future1.get());
			System.out.println("Future_2: " + future2.get());
			System.out.println("Future_3: " + future3.get());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void callableDemoEntry() {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<String> future1 = exec.submit(new MyCallable("Callable-1"));
		Future<String> future2 = exec.submit(new MyCallable("Callable-2"));
		Future<String> future3 = exec.submit(new MyCallable("Callable-3"));
		Future<String> future4 = exec.submit(new MyCallable("Callable-4"));
		Future<String> future5 = exec.submit(new MyCallable("Callable-5"));
		Future<String> future6 = exec.submit(new MyCallable("Callable-6"));
		try {
			System.out.println(future1.get(1, TimeUnit.SECONDS));
			System.out.println(future2.get(1, TimeUnit.SECONDS));
			System.out.println(future3.get(1, TimeUnit.SECONDS));
			System.out.println(future4.get(1, TimeUnit.SECONDS));
			System.out.println(future5.get(1, TimeUnit.SECONDS));
			System.out.println(future6.get(1, TimeUnit.SECONDS));
		} catch (Exception e) {
			System.out.println("Time out...");
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		runnableDemoEntry();
		callableDemoEntry();
		exec.shutdown();
	}
}
