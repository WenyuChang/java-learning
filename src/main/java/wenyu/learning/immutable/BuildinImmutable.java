package wenyu.learning.immutable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class BuildinImmutable {
	private static void sampleInteger() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Collection<Integer> arrUnMod = Collections.unmodifiableCollection(arr);
		for(Integer i : arrUnMod) {
			System.out.println(i);
		}
		
		try {
			arrUnMod.add(3);
		} catch (Exception ex) {
			System.out.println("Got exception when trying to modify unmodifiable collection.");
			System.out.println("However, only the reference of item in unmodifiable collection cannot be "
					+ "modified, the internal value still can be modified.");
			return;
		}
	}
	
	private static void sampleArray() {
		ArrayList<Integer[]> arr = new ArrayList<Integer[]>();
		Collection<Integer[]> arrUnMod = Collections.unmodifiableCollection(arr);
		
		try {
			Iterator<Integer[]> it = arrUnMod.iterator();
			while(it.hasNext()) {
				Integer[] intArr = it.next();
				intArr = new Integer[1];
				intArr[0] = 3;
			}
		} catch (Exception ex) {
			System.out.println("Got exception when trying to modify unmodifiable collection.");
			System.out.println("However, only the reference of item in unmodifiable collection cannot be "
					+ "modified, the internal value still can be modified.");
			return;
		}
		
		System.out.println("No exception for modifing internal value in array.");
	}
	
	public static void main(String[] args) {
		sampleInteger();
		
		System.out.println("====================");
		
		sampleArray();
	}
}
