package wenyu.learning.CollectionLearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ListLearning {

	private static <T> void printList(List<T> list) {
		Iterator<T> it = list.iterator();

		while (it.hasNext()) {
			T value = it.next();
			System.out.print(value + "(" + value.hashCode() + ") ");
		}
		System.out.println();
	}

	public static void arrayListLearning() {
		// Constructs an empty list with an initial capacity of ten.
		List<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(1);
		arrayList.add(0, 2);
		arrayList.add(3);
		
		printList(arrayList);
		
		List<Integer> arrayListWithInitCapacity = new ArrayList<Integer>(3);
		arrayListWithInitCapacity.add(1);
		arrayListWithInitCapacity.add(0, 2);
		arrayListWithInitCapacity.add(3);
		arrayListWithInitCapacity.add(6);
		arrayListWithInitCapacity.add(9);
	}

	public static void VectorLearning() {
		// initial size is 3, increment is 2
		Vector<Integer> vector = new Vector<Integer>(3, 2);
		System.out.println("Initial size: " + vector.size());
		System.out.println("Initial capacity: " + vector.capacity());
		vector.add(1);
		vector.add(3);
		vector.add(5);
		vector.add(2);
		vector.add(6);
		vector.add(8);
		System.out.println("Initial capacity: " + vector.capacity());
		printList(vector);

		System.out.println("First element: " + vector.firstElement());
		System.out.println("Last element: " + vector.lastElement());
		if (vector.contains(3)) {
			System.out.println("Vector contains 3.");
		}
		
		//Stack is the subclass of Vector too.
		Vector<Integer> stack = new Stack<Integer>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VectorLearning();
		
		arrayListLearning();
	}

}