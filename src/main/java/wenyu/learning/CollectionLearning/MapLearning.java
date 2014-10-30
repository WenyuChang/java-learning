package wenyu.learning.CollectionLearning;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;

public class MapLearning {

	private static <K,V> void printMap(Map<K,V> map) {
		// Get a set of the entries
		Set<Entry<K, V>> set = map.entrySet();
		// Get an iterator
		Iterator<Entry<K, V>> i = set.iterator();
		// Display elements
		while (i.hasNext()) {
			Map.Entry<K, V> me = (Map.Entry<K, V>) i.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
	}
	
	public static void HashMapDemo() {
		/*
		 * Hash map is the simplest hash things in java
		 * it is not synchronized and don't have order.
		 * The output of HashMap is also not the same as insert's 
		 */
		// Create a hash map
		HashMap<String, Double> hm = new HashMap<String, Double>();
		// Put elements to the map
		hm.put("aaa", new Double(0));
		hm.put("bbb", new Double(1));
		hm.put("ccc", new Double(2));
		hm.put("ddd", new Double(3));
		hm.put("eee", new Double(4));
		printMap(hm);
		
		// Deposit 1000 into ccc's account
		double balance = ((Double) hm.get("ccc")).doubleValue();
		hm.put("ccc", new Double(balance + 1000)); // put will replace the older one
		System.out.println("CCC's new value: " + hm.get("ccc"));
	}

	public static void LinkedHashMapDemo() {
		/*
		 * The output of LinkedHashMap is also the same as insert's 
		 */
		// Create a linked hash map
		LinkedHashMap<String, Double> lhm = new LinkedHashMap<String, Double>();
		// Put elements to the map
		lhm.put("ddd", new Double(3));
		lhm.put("aaa", new Double(0));
		lhm.put("eee", new Double(4));
		lhm.put("ccc", new Double(2));
		lhm.put("bbb", new Double(1));
		printMap(lhm);
		
		// Deposit 1000 into Zara's account
		double balance = ((Double) lhm.get("ccc")).doubleValue();
		lhm.put("ccc", new Double(balance + 1000)); // put will replace the older one
		System.out.println("CCC's new value: " + lhm.get("ccc"));
	}

	public static void TreeMapDemo() {
		// Create a tree map
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
		
		Comparator<String> comparator1 = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};

		TreeMap<String, Double> tm = new TreeMap<String, Double>(comparator1);
		// Put elements to the map
		tm.put("aa", new Double(0));
		tm.put("bbb", new Double(1));
		tm.put("dd", new Double(3));
		tm.put("e", new Double(4));
		tm.put("cccc", new Double(2));
		printMap(tm);
		// Deposit 1000 into Zara's account
		double balance = ((Double) tm.get("cccc")).doubleValue();
		tm.put("cccc", new Double(balance + 1000)); // put will replace the older one
		System.out.println("CCCC's new balance: " + tm.get("cccc"));
	}

	public static void weakHashMapDemo() {
		// Storing only weak references allows a key-value pair to be 
		// garbage collected when its key is no longer referenced 
		// outside of the WeakHashMap
		
		WeakHashMap<String, String> map = new WeakHashMap<String, String>();
		map.put(new String("Maine"), "Augusta");
		printMap(map);
	}

	public static void sortViaValues() {
		// Create a tree map
		Comparator<Entry<String, Double>> comparator = new Comparator<Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				if(o1.getValue()>o2.getValue()) {
					return 1;
				} else if(o1.getValue()<o2.getValue()) {
					return -1;
				} else {
					return 0;
				}
			}
		};

		// Create a hash map
		HashMap<String, Double> hm = new HashMap<String, Double>();
		// Put elements to the map
//		hm.put("Zara", new Double(3434.34));
		hm.put("Mahnaz", new Double(123.22));
		hm.put("Ayan", new Double(1378.00));
		hm.put("Daisy", new Double(99.22));
		hm.put("Qadir", new Double(-19.08));
		printMap(hm);
		
		TreeSet<Entry<String, Double>> set = new TreeSet<Entry<String, Double>>(comparator);
		set.addAll(hm.entrySet());
		SetLearning.printSet(set);
	}
	
	public static void main(String[] args) {
		System.out.println("HashMap Demo: ");
		HashMapDemo();

		System.out.println("==================================");
		System.out.println("LinkedHashMap Demo: ");
		LinkedHashMapDemo();

		System.out.println("==================================");
		System.out.println("TreeMap Demo: ");
		TreeMapDemo();
		
		System.out.println("==================================");
		System.out.println("WeakHashMap Demo: ");
		weakHashMapDemo();
		
		System.out.println("==================================");
		System.out.println("Sort Map via Values Demo: ");
		sortViaValues();
	}

}
