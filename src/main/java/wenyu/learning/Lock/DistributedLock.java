package wenyu.learning.Lock;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class DistributedLock {
	public static ZooKeeper zk;
	private String lockPath;
	static {
		try {
			ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
			long tmp_start_time = threadMXBean.getCurrentThreadCpuTime();
			zk = new ZooKeeper("sjmcesiumdev2:2181",
					Integer.MAX_VALUE, 
					new Watcher() {
						public void process(WatchedEvent event) { 
							GetZKChildren();
							System.out.println("Trigger: " + event.getType()); 
						} 
			    	}
				);
			long tmp_end_time = threadMXBean.getCurrentThreadCpuTime();
			System.out.println("It costs " + (tmp_end_time-tmp_start_time)/1000000 + "ms to generate ZooKeeper instance.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void tryLock() throws Exception {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long tmp_start_time = threadMXBean.getCurrentThreadCpuTime();
		lockPath = zk.create("/lock", null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		long tmp_end_time = threadMXBean.getCurrentThreadCpuTime();
		System.out.println("It costs " + (tmp_end_time-tmp_start_time)/1000000 + "ms to generate create lock node.");
		
		final Object lock = new Object();
		
		synchronized(lock) {
			while(true) {
				List<String> nodes = zk.getChildren("/", new Watcher() {
		            public void process(WatchedEvent event) {
		            	synchronized (lock) {
		            		lock.notifyAll();
		            	}
		            }
				});
			
				Collections.sort(nodes);
				
				if(lockPath.endsWith(nodes.get(0))) {
					return;
		        } else {
		            lock.wait();
		        }
			}
		}
	}
	
	public void releaseLock() throws Exception {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		long tmp_start_time = threadMXBean.getCurrentThreadCpuTime();
		zk.delete(lockPath, -1);
		lockPath = null;
		long tmp_end_time = threadMXBean.getCurrentThreadCpuTime();
		System.out.println("It costs " + (tmp_end_time-tmp_start_time)/1000000 + "ms to delete lock node.");
		GetZKChildren();
	}
	
	public static void GetZKChildren() {
		try {
			List<String> nodes = zk.getChildren("/", false);
			System.out.println("Current nodes are " + nodes.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}