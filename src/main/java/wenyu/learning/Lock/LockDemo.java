package wenyu.learning.Lock;

public class LockDemo {
	public static final int nodeCount = 10;
	public static final Object lock = new Object();
    public static void main(String[] args) {
    	runDemoWithoutLock();
//    	runDemoWithThreadLock();
//    	runDemoWithDistributedLock();
    }
    
    public static void runDemoWithoutLock() {
    	for(int i=0;i<LockDemo.nodeCount;i++) {
    		Thread thread = new Thread("thread-"+i) {
    			public void run() {
    				try {
						new VirtualJob(this.getName()).excute();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		};
    		
    		thread.start();
    	}
    }
    
    public static void runDemoWithThreadLock() {
    	for(int i=0;i<LockDemo.nodeCount;i++) {
    		Thread thread = new Thread("thread-"+i) {
    			public void run() {
    				synchronized(lock) {
	    				try {
							new VirtualJob(this.getName()).excute();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
    				}
    			}
    		};
    		
    		thread.start();
    	}
    }
    
    public static void runDemoWithDistributedLock() {
    	for(int i=0;i<LockDemo.nodeCount;i++) {
    		Thread thread = new Thread("thread-"+i) {
    			public void run() {
    				try {
    					DistributedLock lock = new DistributedLock();
    					
    					lock.tryLock();
						new VirtualJob(this.getName()).excute();
						lock.releaseLock();
						
    				} catch (Exception e1) {
						e1.printStackTrace();
					}
    			}
    		};
    		
    		thread.start();
    	}
    }
}
