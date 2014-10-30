package wenyu.learning.CollectionLearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class IteratorUsage {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void arrayListDemo(ArrayList al) {
		Iterator itr = al.iterator();
		while (itr.hasNext()) {
			Object element = itr.next();
			System.out.print(element + " ");
		}
		System.out.println();

		// Modify objects being iterated
		ListIterator litr = al.listIterator();
		while (litr.hasNext()) {
			Object element = litr.next();
			litr.set(element + "+");
		}

		// Now, display the list backwards
		while (litr.hasPrevious()) {
			Object element = litr.previous();
			System.out.print(element + " ");
		}
		System.out.println();
	}

	public static void mapDemo(Map<String, Integer> map) {
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) it
					.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}
	}

	public static void main(String[] args) {

	}

}
