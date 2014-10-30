package wenyu.learning.Multithread;

public class SynchronizedDemo {
	final static int count = 10;
	final static int threadCount = 1000;
	
	//==========================================================================
	//==========================================================================
	public static int val_1 = 100;
	synchronized public static void add_1(String name) {
		val_1 = val_1 + 10;
	}
	synchronized public static void minus_1(String name) {
		val_1 = val_1 - 10;
	}
	public static void forSync() throws Exception {
		long start = System.currentTimeMillis();
		for(int j=0;j<count;j++) {
			val_1 = 100;
			Thread[] threads = new Thread[threadCount*2];
			
			for(int i=0;i<threadCount*2; i++) {
				if(i%2==0) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.add_1(this.getName());
						}
					};
				} else if (i%2==1) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.minus_1(this.getName());
						}
					};
				}
				threads[i].start();
			}
			
			for(int i=0;i<threadCount*2;i++) {
				threads[i].join();
			}
			System.out.println("Synchronized Result: " + val_1);
		}
		long end = System.currentTimeMillis();
		long costs = end-start;
		System.out.println("===============Synchronized Costs:" + costs + "=================");
		System.out.println("Seems Synchronized work to solve mulitithread problem.\n");
	}
	
	
	//==========================================================================
	//==========================================================================
	public static int val_2 = 100;
	public static void add_2(String name) {
		val_2 = val_2 + 10;
	}
	public static void minus_2(String name) {
		val_2 = val_2 - 10;
	}
	public static void forNormal() throws Exception {
		long start = System.currentTimeMillis();
		for(int j=0;j<count;j++) {
			val_2 = 100;
			Thread[] threads = new Thread[threadCount*2];
			
			for(int i=0;i<threadCount*2; i++) {
				if(i%2==0) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.add_2(this.getName());
						}
					};
				} else if (i%2==1) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.minus_2(this.getName());
						}
					};
				}
				threads[i].start();
			}
			
			for(int i=0;i<threadCount*2;i++) {
				threads[i].join();
			}
			
			System.out.println("Normal Result: " + val_2);
		}
		long end = System.currentTimeMillis();
		long costs = end-start;
		System.out.println("===============Normal Costs:" + costs + "=================");
		System.out.println("Seems Normal doesn't work to solve mulitithread problem.\n");
	}
	
	//==========================================================================
	//==========================================================================	
	public volatile static int val_3 = 100;
	public static void add_3(String name) {
		val_3 = val_3 + 10;
	}
	public static void minus_3(String name) {
		val_3 = val_3 - 10;
	}
	public static void forVolatile() throws Exception {
		long start = System.currentTimeMillis();
		for(int j=0;j<count;j++) {
			val_3 = 100;
			Thread[] threads = new Thread[threadCount*2];
			
			for(int i=0;i<threadCount*2; i++) {
				if(i%2==0) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.add_3(this.getName());
						}
					};
				} else if (i%2==1) {
					threads[i] = new Thread("Thread-" + i) {
						public void run() {
							SynchronizedDemo.minus_3(this.getName());
						}
					};
				}
				threads[i].start();
			}
			
			for(int i=0;i<threadCount*2;i++) {
				threads[i].join();
			}
			System.out.println("Volatile Result: " + val_3);
		}
		long end = System.currentTimeMillis();
		long costs = end-start;
		System.out.println("===============Volatile Costs:" + costs + "=================");
		System.out.println("Seems Volatile doesn't work to solve mulitithread problem.");
		System.out.println("Volatile result doesn't work. This is because: val_3=val_3+1 is not atomic.\n");
	}
	
	public static void main(String[] args) throws Exception {
		forSync();
		forNormal();
		forVolatile();
		
	}
}
