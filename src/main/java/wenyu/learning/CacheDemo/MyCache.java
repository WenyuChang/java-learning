package wenyu.learning.CacheDemo;

import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

class CacheUnit {
	String name = null;
	Object value = null;
	Refresher ref = null;
	long expireTime = 0;
	boolean isAlive = false;
	TimeOutTask task = null;
	Object lock = new Object();
}

class TimeOutTask extends TimerTask {
    private CacheUnit unit = null;
	public TimeOutTask(CacheUnit unit) {
		this.unit = unit;
    }

	@Override
	public void run() {
		synchronized (unit.lock) {
			unit.value = null;
			unit.isAlive = false;
		}
    }
}

public class MyCache {
	private static ConcurrentHashMap<String, CacheUnit> cacheMgmt;
	private static Timer timer = new Timer(true);
	/**
	 * Singleton entry.
	 */
	public static MyCache getInstance() {
		return instance;
	}
	private static MyCache instance = new MyCache();
	
	/**
	 * Constructor.
	 */
	private MyCache() {
		cacheMgmt = new ConcurrentHashMap<String, CacheUnit>();
	}
	
	private long getExpireTime(String key) {
		String configStr = "CACHE_TIME_" + key.toUpperCase();
		//get(configStr);
		return 1001;
	}
	
	public void addEntry(String key, Refresher ref) {
		if (cacheMgmt.containsKey(key)) {
			return;
		} else {
			CacheUnit unit = new CacheUnit();
			unit.name = key;
			unit.ref = ref;
			unit.expireTime = getExpireTime(key);
			unit.isAlive = false;
			
			cacheMgmt.put(key, unit);
		}
	}
	
	public Object retrive(String key) {
		if (cacheMgmt.containsKey(key)) {
			CacheUnit unit = cacheMgmt.get(key);
			synchronized (unit.lock) {
				if (unit.isAlive) {
					unit.task.cancel();
				} else {
					unit.value = unit.ref.refresh(key);
					unit.isAlive = true;
				}
				TimeOutTask task = new TimeOutTask(unit);
				unit.task = task;
				timer.schedule(task, unit.expireTime);
				return unit.value;
			}
		} else {
			return null;
		}
	}
	
	public void invalidate(String key) {
		if (cacheMgmt.containsKey(key)) {
			CacheUnit unit = cacheMgmt.get(key);
			synchronized (unit.lock) {
				// TODO: cancel task
				unit.value = null;
				unit.isAlive = false;
			}
		}
	}
	
	public void invalidateAll() {
		Iterator<Map.Entry<String, CacheUnit>> units = cacheMgmt.entrySet().iterator();
	    while (units.hasNext()) {
	    	Map.Entry<String, CacheUnit> pairs = units.next();
	    	CacheUnit unit = pairs.getValue();
	    	synchronized (unit.lock) {
	    		// TODO: cancel task
		    	unit.value = null;
		    	unit.isAlive = false;
	    	}
	    }
	}
	
	public void removeEntry(String key) {
		cacheMgmt.remove(key);
	}
	
	public void cleanUpEntry() {
		cacheMgmt.clear();
	}
}
