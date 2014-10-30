package wenyu.learning.Multithread;

/*
 * 1. wait( ) tells the calling thread to give up the monitor 
 *    and go to sleep until some other thread enters the same monitor and calls notify( ).
 * 2. notify( ) wakes up the first thread that called wait( ) on the same object.
 * 3. notifyAll( ) wakes up all the threads that called wait( ) on the same object. 
 *    The highest priority thread will run first.
 *    
 * There are three threads in a process. The first thread prints 1 1 1 …, 
 * the second one prints 2 2 2…, and the third one prints 3 3 3 … endlessly. 
 * How do you schedule these three threads in order to print 1 2 3 1 2 3 …
 */
public class WaitAndNotify {
	
	private static Thread th1 = new Thread() {
		@Override
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread1 Print 1");
				}
			}
		}
	};
	
	private static Thread th2 = new Thread() {
		@Override
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread2 Print 2");
				}
			}
		}
	};
	
	private static Thread th3 = new Thread() {
		@Override
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread3 Print 3");
				}
			}
		}
	};
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[3];
		threads[0] = th1;
		threads[1] = th2;
		threads[2] = th3;
		
		th1.start();
		th2.start();
		th3.start();
		
		int i=0;
		while(true) {
			i = i%3;
			synchronized(threads[i]) {
				threads[i].notify();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

}
