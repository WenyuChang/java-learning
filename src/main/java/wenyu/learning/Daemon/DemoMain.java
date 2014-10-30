package wenyu.learning.Daemon;

public class DemoMain {
	private final int daemonNum = 1;
	
	public DemoMain() {
		Thread hook = new Thread() {
			@Override
			public void run() {
				System.out.println("Hook: DemoMain Shutdown...");
			}
		};
		
		Runtime.getRuntime().addShutdownHook(hook);
	}
	
	/*
	 * Java makes a distinction between a user thread and another 
	 * type of thread known as a daemon thread. The daemon threads 
	 * are typically used to perform services for user threads. 
	 * The main() method of the application thread is a user thread. 
	 * Threads created by a user thread are user thread. JVM doesn't 
	 * terminates unless all the user thread terminate.
	 * 
	 * if MyDaemon is set to daemon, it will be terminated if the main 
	 * instance is over. That means MyDaemon will not execute to the 
	 * end of the code.
	 * 
	 * Unless make the main instance wait by using sleep or join.
	 */
	public void process() throws Exception {
		for(int i=0;i<daemonNum;i++) {
			MyDaemon daemon = new MyDaemon(i+1+"");
			daemon.setDaemon(true);
			daemon.start();
			//daemon.join();
		}
	}
	
	public static void main(String[] args) throws Exception {
		DemoMain demo = new DemoMain();
		demo.process();
		
		//Thread.sleep(5000);
	}

}
