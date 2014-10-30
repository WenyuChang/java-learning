package wenyu.learning.Daemon;

public class MyDaemon extends Thread {
	private String name;
	
	public MyDaemon(String name) {
		this.name = name;
		
		Thread hook = new Thread(name) {
			@Override
			public void run() {
				System.out.println("Hook: MyDaemon(" + this.getName() + ") Shutdown...");
			}
		};
		
		Runtime.getRuntime().addShutdownHook(hook);
	}
	
	@Override
	public void run() {
		System.out.println("MyDaemon(" + this.name +") starts running...");
		long sum=0;
		for(int i=0;i<1000000;i++) {
			System.out.println("Now is " + i);
			sum+=i;
		}
		
		System.out.println("The result is " + sum + "\nMyDaemon(" + this.name +") ends running...");
	}
}
