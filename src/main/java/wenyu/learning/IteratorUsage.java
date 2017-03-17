package wenyu.learning;

import java.util.*;
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
			al.remove(element);
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

	private static class MyIteritor implements Iterator<Integer> {
		private Integer[] arr;
		private int currIdx;
		public MyIteritor(int max) {
			arr = new Integer[max];
			for (int i=0; i<max; i++) {
				arr[i] = new Random().nextInt();
			}
			currIdx = 0;
		}

		public boolean hasNext() {
			return currIdx < arr.length;
		}

		public Integer next() {
			return arr[currIdx++];
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> aa = new ArrayList<Integer>();
		aa.add(1);
		aa.add(2);
		aa.add(3);
		arrayListDemo(aa);


		MyIteritor myit = new MyIteritor(100);
		while (myit.hasNext()) {
			System.out.print(myit.next() + ", ");
		}
	}

}
