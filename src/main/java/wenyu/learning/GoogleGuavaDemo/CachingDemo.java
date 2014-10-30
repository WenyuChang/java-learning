package wenyu.learning.GoogleGuavaDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;

public class CachingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		demoEntry();
	}
	
	
	/**
	 * Demo Entrance
	 */
	public static void demoEntry() {
		
		// TODO initial a cache instance
		LoadingCache<String, String> cache = newCache(false);
	
		// TODO Test Cache Feature
		testCache(cache);
		
		// TODO Test Cache Stats Feature
		testCacheStats(cache);
		

	}
	
	public static LoadingCache<String, String> newCache(boolean fromSpec) {
		
		// TODO initial a cache loader (optional)
		CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            public String load(String key) throws Exception {
            	// TODO input the key and resturn the value
            	return "Key:" + key + "/Value: " + key.toUpperCase();
            	}
		};
		
		// TODO initial a weighter be used by loadingCache which will return the value weight
		Weigher<String, String> weighByLength = new Weigher<String, String>() {
			public int weigh(String key, String value) {
				return value.length();
				}
			};
		
		// TODO initial a removal listener which will be called when remove a value in cache
		RemovalListener<String, String> removalListener = new RemovalListener<String, String>() {
			  public void onRemoval(RemovalNotification<String, String> removal) {
				  System.out.println("remove listen invoked...");
			  }
			};
		
		// TODO there are two ways to initial an LoadingCache
		// One is spec the configuration in String
		// The other is just spec in code
		if (fromSpec) {
			// TODO from String
			String readSpec = "maximumWeight=1000,expireAfterWrite=1,refreshAfterWrite=2s,weakKeys";
			LoadingCache<String, String> cache = CacheBuilder.from(readSpec)
				       .removalListener(removalListener)
				       .weigher(weighByLength)
				       .build(cacheLoader);
			
			return cache;
		} else {
			// TODO code the configuration
			LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				       .maximumSize(1000)
				       .recordStats()
				       //.maximumWeight(3)
				       //.weigher(weighByLength)
				       .expireAfterAccess(1, TimeUnit.SECONDS)
				       .removalListener(removalListener)
				       .build(cacheLoader);
			
			return cache;
		}
	}
	
	public static void testCache(LoadingCache<String, String> cache) {
		try {
//			System.out.println("1. Current cache size: " + cache.size());
			cache.put("aa", "rr");
//			System.out.println("2. Current cache size: " + cache.size());
			System.out.println(cache.get("aa"));
			
			
			
//			Thread.sleep(3000);
//			System.out.println(cache.get("aa"));
//			System.out.println("3. Current cache size: " + cache.size());
//			cache.invalidate("aa");
//			System.out.println("4. Current cache size: " + cache.size());
//
//			
//			System.out.println(cache.get("bb"));
//			System.out.println("5. Current cache size: " + cache.size());
//			System.out.println(cache.get("bb"));
//			System.out.println("6. Current cache size: " + cache.size());
//			
//			
//			cache.cleanUp();
//			System.out.println("7. Current cache size after clear: " + cache.size());
//			
//			
//			System.out.println(cache.getIfPresent("cc"));
//			System.out.println("8. Current cache size after clear: " + cache.size());
			
		} catch (ExecutionException  e) {
			e.printStackTrace();}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public static void testCacheStats(LoadingCache<String, String> cache) {
		
		CacheStats stats = cache.stats();
		System.out.println(stats.hitRate());
		System.out.println(stats.missRate());
		System.out.println(stats.loadExceptionRate());
		System.out.println(stats.averageLoadPenalty());
		
		
		CacheStats delta = cache.stats().minus(stats);
		System.out.println(delta.hitCount());
		System.out.println(delta.missCount());
		System.out.println(delta.loadSuccessCount());
		System.out.println(delta.loadExceptionCount());
		System.out.println(delta.totalLoadTime());
	}
}
