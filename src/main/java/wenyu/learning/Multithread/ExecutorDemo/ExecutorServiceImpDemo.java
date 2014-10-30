package wenyu.learning.Multithread.ExecutorDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceImpDemo {
	public static void main(String[] args) {
		
		/**
		 * This type of pool always has a specified 
		 * number of threads running; if a thread is 
		 * somehow terminated while it is still in use, 
		 * it is automatically replaced with a new thread. 
		 * Tasks are submitted to the pool via an internal 
		 * queue, which holds extra tasks whenever there 
		 * are more active tasks than threads.
		 */
		int handelers = 10;
		ExecutorService exec = Executors.newFixedThreadPool(handelers);
		exec.submit(new MyRunnable("Runnable-1"));
		exec.submit(new MyRunnable("Runnable-2"));
		exec.submit(new MyRunnable("Runnable-3"));
		exec.submit(new MyRunnable("Runnable-4"));
		exec.shutdown();
		
		
		/**
		 * The newCachedThreadPool method creates an executor 
		 * with an expandable thread pool. This executor is 
		 * suitable for applications that launch many short-lived 
		 * tasks.
		 */
//		ExecutorService exec_cache = Executors.newCachedThreadPool();
//		exec_cache.submit(new MyRunnable("Runnable-1"));
//		exec_cache.submit(new MyRunnable("Runnable-2"));
//		exec_cache.submit(new MyRunnable("Runnable-3"));
//		exec_cache.submit(new MyRunnable("Runnable-4"));
//		exec_cache.shutdown();
		
		
		/**
		 * The newSingleThreadExecutor method creates an executor 
		 * that executes a single task at a time.
		 */
//		ExecutorService exec_single = Executors.newSingleThreadExecutor();
//		exec_single.submit(new MyRunnable("Runnable-1"));
//		exec_single.submit(new MyRunnable("Runnable-2"));
//		exec_single.submit(new MyRunnable("Runnable-3"));
//		Future<?> future = exec_single.submit(new MyRunnable("Runnable-4"));
//		System.out.println(future);
//		exec_single.shutdown();
	}
}
