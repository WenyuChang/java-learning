package wenyu.learning.Tricky;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModification {
	private static ArrayList<Integer> arr = new ArrayList<Integer>();
	private static CopyOnWriteArrayList<Integer> copyOnWriteArr = new CopyOnWriteArrayList<Integer>();
	
	private static void insertDataToArrayList() {
		arr.clear();
		for(int i=0; i<10; i++) {
			arr.add(i);
		}
	}
	
	private static void insertDataToCopyOnWriteArrayList() {
		copyOnWriteArr.clear();
		for(int i=0; i<10; i++) {
			copyOnWriteArr.add(i);
		}
	}
	
	public static void tricky() {
		/*
		 * Concurrent Modification Exception
		 * When iterate array list, we cannot change the value in array list.
		 * Or will get ConcurrentModificationException
		 * 
		 * Also use iterator will get 
		 * 
		 * Work around:
		 * use: for(int i=0; i<arr.size(); i++) {arr.remove(i);}
		 * use: CopyOnWriteArrayList
		 * use: ListIterator
		 */
		try {
			// This will meet ConcurrentModificationException
			insertDataToArrayList();
			insertDataToCopyOnWriteArrayList();
			for(Integer item:arr) {
				System.out.print(item);
				if(item%2 == 0) {
					arr.remove(item);
				}
			}
		} catch(ConcurrentModificationException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println();
		}
		
		try {
			// This will meet ConcurrentModificationException
			insertDataToArrayList();
			insertDataToCopyOnWriteArrayList();
			Iterator<Integer> it = arr.iterator();
			while(it.hasNext()) {
				Integer item = it.next();
				System.out.print(item);
				arr.remove(item);
			}
		} catch(ConcurrentModificationException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println();
		}
		
		try {
			// This will run without exception but the result may not be what we want
			insertDataToArrayList();
			insertDataToCopyOnWriteArrayList();
			for(int i=0; i<arr.size(); i++) {
				System.out.print(arr.get(i));
				if(arr.get(i)%2 == 0) {
					arr.remove(i);
				}
			}
		} catch(ConcurrentModificationException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println();
		}
		
		try {
			// This will run correctly
			insertDataToArrayList();
			insertDataToCopyOnWriteArrayList();
			for(Integer item:copyOnWriteArr) {
				System.out.print(item);
				if(item%2 == 0) {
					arr.remove(item);
				}
			}
		} catch(ConcurrentModificationException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println();
		}
		
		try {
			// This will run correctly
			insertDataToArrayList();
			insertDataToCopyOnWriteArrayList();
			ListIterator<Integer> it = arr.listIterator();
			while(it.hasNext()) {
				Integer item = it.next();
				System.out.print(item);
				it.remove();
			}
		} catch(ConcurrentModificationException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		tricky();
	}
}
 