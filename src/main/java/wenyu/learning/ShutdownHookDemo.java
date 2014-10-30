package wenyu.learning;

public class ShutdownHookDemo {

	public ShutdownHookDemo() {
		Thread hook = new Thread() {
			@Override
			public void run() {
				System.out.println("Hook: System Shutdown...");
			}
		};
		Thread hook1 = new Thread() {
			@Override
			public void run() {
				System.out.println("Hook1: System Shutdown...");
			}
		};
		Runtime.getRuntime().addShutdownHook(hook);
		Runtime.getRuntime().addShutdownHook(hook1);
	}
	
	public void process() {
		System.out.println("Instance running...");
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread thread = new Thread() {
			@Override
			public void run() {
				new ShutdownHookDemo().process();
				System.out.println("Thread running...");
			}
		};
		thread.start();
		thread.join();
		System.out.println("Thread ended...");
	}

}
