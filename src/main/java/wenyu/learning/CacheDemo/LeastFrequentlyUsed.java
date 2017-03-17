package wenyu.learning.CacheDemo;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * Least-Frequently Used (LFU)
 * Counts how often an item is needed. Those that are used least often are discarded first.
 */
public class LeastFrequentlyUsed {

	protected static int DEFAULT_SIZE = 10;
	private static class LFUEntry implements Comparable<LFUEntry> {
		private final String key;
		private Object value;
		private final Refresher refresher;
		private int hit = 0;
		private LFUEntry(String key, Refresher refresher) {
			this.key = key;
			this.refresher = refresher;
			if(refresher!=null) {
				this.value = this.refresher.refresh(this.key);
			} else {
				this.value = key;
			}
		}

		public int compareTo(LFUEntry o) {
			return this.hit - o.hit;
		}
	}
	
	private long totalAccess = 0;
	private long hitCount = 0;
	private final PriorityQueue<LFUEntry> pq;
	private final HashMap<String, LFUEntry> entryMap;
	private final HashMap<String, Refresher> refresherMap;
	private boolean debug;
	private final int size;
	
	public LeastFrequentlyUsed() {
		this(DEFAULT_SIZE, false);
	}
	public LeastFrequentlyUsed(int size, boolean debug) {
		this.size = size;
		this.debug = debug;
		this.pq = new PriorityQueue<LFUEntry>(size);
		this.entryMap = new HashMap<String, LFUEntry>();
		this.refresherMap = new HashMap<String, Refresher>();
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
			LFUEntry entry = entryMap.get(key);
			pq.remove(entry);
			entry.hit++;
			pq.add(entry);
			
			if(debug) System.out.println("Hit cache entry: " + key);
			return entry.value;
		} else if(refresherMap.containsKey(key)) {
			LFUEntry newEntry = new LFUEntry(key, refresherMap.get(key));
			newEntry.hit++;
			if(size > 0) {
				if(entryMap.size() >= size) {
					LFUEntry pollEntry = pq.poll();
					entryMap.remove(pollEntry.key);
				}
				pq.add(newEntry);
				entryMap.put(key, newEntry);
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
		
		for(Object obj : pq.toArray()) {
			LFUEntry entry = (LFUEntry) obj;
			System.out.print(entry.value + "(" + entry.hit + ") ");
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
		LeastFrequentlyUsed cache = new LeastFrequentlyUsed(3, true);
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
