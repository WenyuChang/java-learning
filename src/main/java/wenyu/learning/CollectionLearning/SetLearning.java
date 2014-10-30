package wenyu.learning.CollectionLearning;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class SetLearning {

	public static <T> void printSet(Set<T> set) {
		Iterator<T> it = set.iterator();
		while(it.hasNext()) {
			T next = it.next();
			System.out.print(next + "(" + next.hashCode() + ") ");
		}
		System.out.println();
	}
	
	public static void HashSetDemo() {
		/*
		 * public class HashSet<E> 
		 * extends AbstractSet<E> 
		 * implements Set<E>, Cloneable, Serializable
		 */
		Set<Integer> inthashset = new HashSet<Integer>();
		inthashset.add(1);
		inthashset.add(3);
		inthashset.add(6);
		inthashset.add(3);
		inthashset.add(2);
		inthashset.add(3);
		inthashset.add(-1);
		printSet(inthashset);
		
		
		Set<String> strhashset = new HashSet<String>();
		strhashset.add("aa");
		strhashset.add("cc");
		strhashset.add("dd");
		strhashset.add("cc");
		strhashset.add("bb");
		strhashset.add("abc");
		printSet(strhashset);
		
		class MyClass {
			public int x = 1;
			public MyClass(int x) {
				this.x = x;
			}
			public String toString() {
				return "[X: " + x + "]";
			}
		}
		Set<MyClass> objhashset = new HashSet<MyClass>();
		MyClass ins1 = new MyClass(1);
		MyClass ins2 = new MyClass(2);
		MyClass ins3 = new MyClass(3);
		objhashset.add(ins1);
		objhashset.add(ins2);
		objhashset.add(ins3);
		objhashset.add(ins2);
		printSet(objhashset);
		ins2.x = 8;
		objhashset.add(ins2);
		printSet(objhashset);
	}
	
	public static void linkedHashSet() {
		/*
		 * public class LinkedHashSet<E> 
		 * extends HashSet<E>
		 * implements Set<E>, Cloneable, Serializable
		 */
		Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
		linkedHashSet.add(1);
		linkedHashSet.add(3);
		linkedHashSet.add(6);
		linkedHashSet.add(3);
		linkedHashSet.add(2);
		linkedHashSet.add(3);
		linkedHashSet.add(-1);
		printSet(linkedHashSet);
		
		class MyObj {
			public int X;
			public String toString() {
				return String.valueOf(X);
			}
		}
		Set<MyObj> objLinkedHashSet = new LinkedHashSet<MyObj>();
		MyObj ins = new MyObj();
		ins.X=1;
		objLinkedHashSet.add(ins);
		ins.X=2;
		objLinkedHashSet.add(ins);
		printSet(objLinkedHashSet);
	}
	
	public static void TreeSet() {
		/*
		 * Red-black tree implementation of the NavigableSet interface.
		 * public class TreeSet<E>
		 * extends AbstractSet<E>
		 * implements NavigableSet<E>, Cloneable, Serializable
		 */
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		};
		Set<String> strtreeset = new TreeSet<String>(comparator);
		strtreeset.add("a");
		strtreeset.add("cc");
		strtreeset.add("ddd");
		strtreeset.add("cc");
		strtreeset.add("bb");
		strtreeset.add("abcd");
		printSet(strtreeset);
		
		
		Set<Integer> treeset = new TreeSet<Integer>();
		treeset.add(1);
		treeset.add(3);
		treeset.add(6);
		treeset.add(3);
		treeset.add(2);
		treeset.add(3);
		treeset.add(-1);
		printSet(treeset);
		
		//Since treeSet is implement NavigableSet
		NavigableSet<Integer> natreeset = new TreeSet<Integer>();
		natreeset.add(1);
		natreeset.add(3);
		natreeset.add(6);
		natreeset.add(3);
		natreeset.add(2);
		natreeset.add(3);
		natreeset.add(-1);
		System.out.println("Lower than 6 is " + natreeset.lower(6));
		System.out.println("Lower than 5 is " + natreeset.lower(5));
		System.out.println("Ceiling than 5 is " + natreeset.ceiling(5));
		System.out.println("Ceiling than 3 is " + natreeset.ceiling(3));
		System.out.println("first is " + natreeset.first());
		System.out.println("last is " + natreeset.last());
	}
	
	public static void main(String[] args) {
		//hashSet is not sorted
		System.out.println("HashSet Sample: ");
		HashSetDemo(); 

		//LinkedHashSet is ordered by the insertion order
		System.out.println("=================================");
		System.out.println("LinkedHashSet Sample: ");
		linkedHashSet();
		
		//TreeSet is ordered
		System.out.println("=================================");
		System.out.println("TreeSet Sample: ");
		TreeSet();
	}

}
