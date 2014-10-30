package wenyu.learning.CacheDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

class EHCacheUnit {
	String cacheName = null;
	int maxEnrty = 0;
	MemoryStoreEvictionPolicy policy = null;
	long timeToLive = 0;
	long timeToIdle = 0;
	Refresher ref = null;
	
	public EHCacheUnit(String name, int maxEtry, MemoryStoreEvictionPolicy policy, long timeToLive, long timeToIdle, Refresher ref) {
		this.cacheName = name;
		this.maxEnrty = maxEtry;
		this.policy = policy;
		this.timeToLive = timeToLive;
		this.timeToIdle = timeToIdle;
		this.ref = ref;
	}
}

public class EHCache {

	CacheManager manager = null;
	Map<String, Refresher> cacheRefMap = new HashMap<String, Refresher>();
	
	private CacheConfiguration initCacheConfig(EHCacheUnit unit) {
		//Create a Cache specifying its configuration.
		CacheConfiguration config = new CacheConfiguration();
		config.setName(unit.cacheName);
		config.setMaxEntriesLocalHeap(unit.maxEnrty);
		config.setMemoryStoreEvictionPolicyFromObject(unit.policy);
		config.setTimeToIdleSeconds(unit.timeToIdle);
		config.setTimeToLiveSeconds(unit.timeToLive);
		
		return config;
	}
	
	public void initCache(ArrayList<EHCacheUnit> units) {
		//Create a singleton CacheManager using defaults
		manager = new CacheManager();
		
		for (EHCacheUnit unit : units) {
			CacheConfiguration config = initCacheConfig(unit);
			Cache testCache = new Cache(config);
			cacheRefMap.put(unit.cacheName, unit.ref);
			manager.addCache(testCache);
		}
	}
	
	public Element retrieve(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (cache.get(key) == null)
			{
				System.out.println("refreshing...");
				element = new Element(key, cacheRefMap.get(cacheName).refresh(key));
				cache.put(element);
			}
			return element;
		}
		return null;
	}
}
