package wenyu.learning.GenericDemo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericArray<T> {
	public T[] arrayT;
	
	@SuppressWarnings("unchecked")
	public void checkedInitialTArray(Class<T> classT, int capacity) throws Exception {
		arrayT = (T[]) Array.newInstance(classT, capacity);
		if(capacity > 0) {
			arrayT[0] = classT.newInstance();
		}
		System.out.println("aaa");
	}
	
	@SuppressWarnings("unchecked")
	public void uncheckedInitialTArray(int capacity) throws Exception  {
		arrayT = (T[]) new Object[capacity];
		System.out.println("aaa");
	}
	
	public static void main(String[] args) throws Exception  {
		GenericArray<Integer> ins = new GenericArray<Integer>();
		ins.checkedInitialTArray(Integer.class, 10);
		ins.uncheckedInitialTArray(10);
		System.out.println(Arrays.toString(ins.arrayT));
		
		
	}

}
