package wenyu.learning.Lock;

import java.util.List;

import org.apache.zookeeper.ZooKeeper;

public class ZookeeperTester {

	public static void GetZKChildren(ZooKeeper zk) {
		List<String> nodes;
		try {
			nodes = zk.getChildren("/", false);
			System.out.println("Current nodes is: "+ nodes.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
