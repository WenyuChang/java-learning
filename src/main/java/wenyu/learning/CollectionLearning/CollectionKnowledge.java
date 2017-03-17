package wenyu.learning.CollectionLearning;

/*
 *            Collection                        Map
	   ___________|___________                   |
	  |           |           |              SortedMap
	 Set         List       Queue
	  |
	SortedSet
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class CollectionKnowledge {
	/*
	 * Notes about collection classes:
    	1. HashSet and HashMap are fast, always use them if order is not required.
    	2. TreeSet and TreeMap, implements SortedSet and SortedMap, 
    	   offer the ordered Set and Map, but they are generally slow 
    	   in comparison with the Hash version.
    	3. Stack is a class while it should be an interface. It extends 
    	   Vector so you know you should not use it any more (performance impact). 
    	   Interestly, when you need stack, don’t use Stack, 
    	   use Deque instead: Deque<Integer> stack = new ArrayDeque<Integer>();
    	4. ArrayDeque is an implementation of Queue. Notice the java doc: 
    	   This class is likely to be faster than Stack when used as a stack, 
    	   and faster than LinkedList when used as a queue.
	 */
	
	/*
	 * The Collections class contains these useful static methods.
		    sort(List) - Sorts a list using a merge sort algorithm, which provides average case performance comparable to a high quality quicksort, guaranteed O(n*log n) performance (unlike quicksort), and stability (unlike quicksort). A stable sort is one that does not reorder equal elements.
		    binarySearch(List, Object) - Searches for an element in an ordered list using the binary search algorithm.
		    reverse(List) - Reverses the order of the elements in a list.
		    shuffle(List) - Randomly changes the order of the elements in a list.
		    fill(List, Object) - Overwrites every element in a list with the specified value.
		    copy(List dest, List src) - Copies the source list into the destination list.
		    min(Collection) - Returns the minimum element in a collection.
		    max(Collection) - Returns the maximum element in a collection.
		    rotate(List list, int distance) - Rotates all of the elements in the list by the specified distance.
		    replaceAll(List list, Object oldVal, Object newVal) - Replaces all occurrences of one specified value with another.
		    indexOfSubList(List source, List target) - Returns the index of the first sublist of source that is equal to target.
		    lastIndexOfSubList(List source, List target) - Returns the index of the last sublist of source that is equal to target.
		    swap(List, int, int) - Swaps the elements at the specified positions in the specified list.
		    frequency(Collection, Object) - Counts the number of times the specified element occurs in the specified collection.
		    disjoint(Collection, Collection) - Determines whether two collections are disjoint, in other words, whether they contain no elements in common.
		    addAll(Collection<? super T>, T...) - Adds all of the elements in the specified array to the specified collection.
	 */
	public static void main(String[] args) {
	}

}
