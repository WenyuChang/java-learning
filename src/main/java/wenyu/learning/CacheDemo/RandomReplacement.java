package wenyu.learning.CacheDemo;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RandomReplacement {

	protected static int DEFAULT_SIZE = 10;
	private static class RREntry {
		private final String key;
		private Object value;
		private final Refresher refresher;
		private RREntry(String key, Refresher refresher) {
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
	private final int size;
	private final String[] keyArr;
	private final HashMap<String, RREntry> entryMap;
	private final HashMap<String, Refresher> refresherMap;
	public RandomReplacement() {
		this(DEFAULT_SIZE, false);
	}
	public RandomReplacement(int size, boolean debug) {
		this.size = size;
		keyArr = new String[size];
		this.entryMap = new HashMap<String, RREntry>();
		this.refresherMap = new HashMap<String, Refresher>();
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
			RREntry entry = entryMap.get(key);
			hitCount++;
			if(debug) System.out.println("Hit cache entry: " + key);
			return entry.value;
		} else if(refresherMap.containsKey(key)) {
			RREntry newEntry = new RREntry(key, refresherMap.get(key));
			if(size > 0) {
				int idx = -1;
				if(entryMap.size() < size) {
					idx = entryMap.size();
				} else {
					Random random = new Random();
					idx = random.nextInt(size);
					entryMap.remove(keyArr[idx]);
				}
				keyArr[idx] = key;
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
		
		for(String key : keyArr) {
			System.out.print(entryMap.get(key).value.toString() + " ");
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
		RandomReplacement cache = new RandomReplacement(3, true);
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
