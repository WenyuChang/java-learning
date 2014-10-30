package wenyu.learning.Multithread;

public class VolatileDemo {
	final static int count = 10000;
	
	//==========================================================================
	//==========================================================================
	public static boolean keepRunning = true;
    public static void forNormal() throws Exception {
        new Thread() {
        	public void run() {
                while (keepRunning) {
                	// if has commands here, seems the JVM will re-read value from main memory.
                }
                System.out.println("Thread Terminated.");
            }
        }.start();
        Thread.sleep(1000);
        keepRunning = false;
        System.out.println("keepRunning set to false. Suppose the thread should ends...but not!!");
        System.out.println("The thread will stop unless thread re-read keepRunning value from main memory!");
    }
    
    //==========================================================================
  	//==========================================================================
  	volatile public static boolean volatileKeepRunning = true;
	public static void forVolatile() throws Exception {
	    new Thread() {
	    	public void run() {
	    		while (volatileKeepRunning) {
	    			// if has commands here, seems the JVM will re-read value from main memory.
	            }
	            System.out.println("Thread Terminated.");
	    	}
	    }.start();
	    Thread.sleep(1000);
	    volatileKeepRunning = false;
	    System.out.println("volatileKeepRunning set to false. Suppose the thread should ends...and it does!!");
	    System.out.println("The thread is forced to read keepRunning value from main memory every time!");
	}
	
	public static void main(String[] args) throws Exception {
		//Caution: forNormal() will cause infinite loop
		forNormal();
		
		//forVolatile();
	}

}
