package wenyu.learning.Multithread.ExecutorDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

	public static void main(String[] args) {
		/**
		 * Creates and executes a one-shot action that 
		 * becomes enabled after the given delay. 
		 */
		int handelers = 2;
//		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(handelers);
//		scheduler.schedule(new MyRunnable("Runnable-1"), 3, TimeUnit.SECONDS);
//		scheduler.schedule(new MyRunnable("Runnable-2"), 0, TimeUnit.SECONDS);
//		scheduler.shutdown();
		
		
		ScheduledExecutorService schedulerByRate = Executors.newScheduledThreadPool(handelers);
		schedulerByRate.scheduleAtFixedRate(new MyRunnable("Runnable-1"), 3, 3, TimeUnit.SECONDS);
		schedulerByRate.scheduleAtFixedRate(new MyRunnable("Runnable-2"), 0, 1, TimeUnit.SECONDS);
	}

}
