package wenyu.learning.CacheDemo;

import java.util.HashMap;
import java.util.Scanner;

/*
 * Least Recently Used (LRU)
 * Discards the least recently used items first. This algorithm requires keeping 
 * track of what was used when, which is expensive if one wants to make sure the 
 * algorithm always discards the least recently used item. 
 */
public class LeastRecentlyUsed {
	protected static int DEFAULT_SIZE = 10;
	private static class LRUEntry {
		private final String key;
		private Object value;
		private final Refresher refresher;
		LRUEntry pre;
		LRUEntry next;
		private LRUEntry(String key, Refresher refresher) {
			this.key = key;
			this.refresher = refresher;
			if(refresher!=null) {
				this.value = this.refresher.refresh(this.key);
			} else {
				this.value = key;
			}
		}
	}
	
	private long totalAccess = 0;
	private long hitCount = 0;
	private boolean debug;
	private LRUEntry internalHead;
	private LRUEntry tail;
	private final int size;
	private final HashMap<String, LRUEntry> entryMap;
	private final HashMap<String, Refresher> refresherMap;
	public LeastRecentlyUsed() {
		this(DEFAULT_SIZE, false);
	}
	public LeastRecentlyUsed(int size, boolean debug) {
		this.size = size;
		this.entryMap = new HashMap<String, LRUEntry>();
		this.refresherMap = new HashMap<String, Refresher>();
		this.internalHead = new LRUEntry(null, null);
		this.tail = internalHead;
		this.debug = debug;
	}
	
	public void addEntry(String key, Refresher refresher) {
		if(!refresherMap.containsKey(key)) {
			refresherMap.put(key, refresher);
		}
	}
	
	public Object retrieve(String key) {
		if(key == null) {
			return null;
		}

		totalAccess++;
		if(entryMap.containsKey(key)) {
			LRUEntry entry = entryMap.get(key);
			if(entry != internalHead.next) {
				if(entry == tail) {
					tail = tail.pre;
					tail.next = null;
				} else {
					entry.pre.next = entry.next;
					entry.next.pre = entry.pre;
				}
				
				entry.next = internalHead.next;
				entry.next.pre = entry;
				internalHead.next = entry;
				entry.pre = internalHead;
			}
			
			hitCount++;
			if(debug) System.out.println("Hit cache entry: " + key);
			return entry.value;
		} else if(refresherMap.containsKey(key)) {
			LRUEntry newEntry = new LRUEntry(key, refresherMap.get(key));
			if(size > 0) {
				newEntry.next = internalHead.next;
				newEntry.pre = internalHead;
				if(internalHead.next != null) {
					internalHead.next.pre = newEntry;
				}
				internalHead.next = newEntry;
				
				if(tail == internalHead) {
					tail = newEntry;
				}
				
				entryMap.put(key, newEntry);
				if(entryMap.size() > size) {
					entryMap.remove(tail.key);
					tail = tail.pre;
					tail.next = null;
				}
			}
			
			if(debug) System.out.println("New cache entry: " + key);
			return newEntry.value;
		} else {
			if(debug) System.out.println("Please provide according refresher to initilize key!");
			return null;
		}
	}
	
	public void list() {
		if(!debug) return;
		
		LRUEntry entry = internalHead.next;
		while(entry!=null) {
			System.out.print(entry.value.toString() + " ");
			entry = entry.next;
		}
		System.out.println();
	}
	
	public void metrics() {
		if(totalAccess == 0) {
			System.out.println("No data right now.");
		} else {
			double result = (double)hitCount/totalAccess;
			System.out.println("Hit rate is " + result*100 + "%.");
		}
	}
	
	public static void main(String[] args) {
		LeastRecentlyUsed cache = new LeastRecentlyUsed(3, true);
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("Input key:");
			String value = scanner.nextLine();
			if(value.trim().equals("exit")) {
				break;
			} else if(value.trim().equals("list")) {
				cache.list();
			} else {
				cache.addEntry(value, null);
				cache.retrieve(value);
				cache.metrics();
			}
		}
		scanner.close();
	}

}
