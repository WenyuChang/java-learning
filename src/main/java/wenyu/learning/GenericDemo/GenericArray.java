package wenyu.learning.GenericDemo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericArray<T> {
	public T[] arrayT;
	
	@SuppressWarnings("unchecked")
	public void checkedInitialTArray(Class<T> classT, int capacity) {
		arrayT = (T[]) Array.newInstance(classT, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public void uncheckedInitialTArray(int capacity) {
		arrayT = (T[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public void uncheckedInitialTArray1(int capacity) {
		arrayT = (T[]) new Object();
	}
	
	public static void main(String[] args) {
		GenericArray<Integer> ins = new GenericArray<Integer>();
		ins.uncheckedInitialTArray(10);
		System.out.println(Arrays.toString(ins.arrayT));
		
		
	}

}
