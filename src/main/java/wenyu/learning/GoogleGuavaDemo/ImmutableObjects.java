package wenyu.learning.GoogleGuavaDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ImmutableList;

public class ImmutableObjects {
	/*
	 * Collection				ImmutableCollection
	 * List						ImmutableList
	 * Set						ImmutableSet
	 * SortedSet/NavigableSet	ImmutableSortedSet
	 * Map						ImmutableMap
	 * SortedMap				ImmutableSortedMap
	 * Multiset					ImmutableMultiset
	 * SortedMultiset			ImmutableSortedMultiset
	 * Multimap					ImmutableMultimap
	 * ListMultimap				ImmutableListMultimap
	 * SetMultimap				ImmutableSetMultimap
	 * BiMap					ImmutableBiMap
	 * ClassToInstanceMap		ImmutableClassToInstanceMap
	 * Table					ImmutableTable
	 */
	
	public static class MyClass {
		MyClass(int value) {
			this.value = value;
		}
		int value = 0;
	}
	
	public static void main(String[] args) {
		ImmutableList<MyClass> immutableMyClass = ImmutableList.<MyClass>builder()
				.add(new MyClass(1))
				.add(new MyClass(2))
				.add(new MyClass(3))
				.build();
		try {
			immutableMyClass.set(0, new MyClass(3));
		} catch (Exception ex) {
			System.out.println("Cannot modify immutable object.");
		}
		
		for(MyClass myClass : immutableMyClass) {
			myClass.value = 10;
		}
		System.out.println("Guava immutable objects still can be modified with internal values.");
	}
}
