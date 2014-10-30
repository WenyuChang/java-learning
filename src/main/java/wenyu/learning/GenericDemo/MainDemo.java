package wenyu.learning.GenericDemo;

import wenyu.learning.GenericDemo.GenericClass;

public class MainDemo {

	public static void demoEntry() {
		
		// Generic Object Demo
		GenericClass<String> genericStr = new GenericClass<String>();
		genericStr.setValue("ccc");
		System.out.println(genericStr.getValue());
		
		GenericClass<Integer> genericInt = new GenericClass<Integer>();
		genericInt.setValue(11);
		System.out.println(genericInt.getValue());
		
		System.out.println("==============================================");
		
		// Generic Method Return Demo 
		GenericMethod obj = new GenericMethod();
		String arg1 = "arg1...";
		Integer arg2 = 0;
		String arg3 = "arg3...";
		obj.setValue(arg1, arg2, arg3);
		System.out.println("arg1:" + arg1);
		System.out.println("arg2:" + arg2);
		System.out.println("arg3:" + arg3);
		
		System.out.println("==============================================");
		
		GenericMethodWithBoundedType objBT = new GenericMethodWithBoundedType();
		System.out.printf("Max of %d, %d and %d is %d\n", 3, 4, 5, objBT.maximum(3, 4, 5));
		System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n", 6.6, 8.8, 7.7, objBT.maximum(6.6, 8.8, 7.7 ));
		System.out.printf("Max of %s, %s and %s is %s\n","pear", "apple", "orange", objBT.maximum("pear", "apple", "orange"));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		demoEntry();
	}

}
