package wenyu.learning.CacheDemo;

import java.util.ArrayList;

import net.sf.ehcache.Element;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class MainDemo {

	private static void myCacheEnrty() {
		MyCache cache = MyCache.getInstance();
		
		// add key and call back function
		cache.addEntry("mykey", new Refresher() {
			public Object refresh(String key) {
				System.out.println("refreshing...");
				return key.toUpperCase();
			}
		});
		// first retrieve value which will call back to refresh...
		System.out.println("retrieve value: " + cache.retrive("mykey"));
		// second retrieve value which will not call back to refresh unless it is expired...
		System.out.println("retrieve value: " + cache.retrive("mykey"));
	}
	
	private static void EHCacheEntry() {
		EHCache cacheMgmt = new EHCache();
		ArrayList<EHCacheUnit> units = new ArrayList<EHCacheUnit>();
		Refresher ref = new Refresher() {
			public Object refresh(String key) {
				return key.toUpperCase();
			}
		};
		EHCacheUnit unit = new EHCacheUnit("testCache", 100, MemoryStoreEvictionPolicy.LFU, (long)60, (long)60, ref);
		units.add(unit);
		
		cacheMgmt.initCache(units);
		cacheMgmt.retrieve("testCache", "mykey");
		
		Element value = cacheMgmt.retrieve("testCache", "mykey");
		System.out.println(value.getObjectValue());
	}
	
	public static void demoEntry() {
		myCacheEnrty();
		
		EHCacheEntry();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		demoEntry();
	}

}
