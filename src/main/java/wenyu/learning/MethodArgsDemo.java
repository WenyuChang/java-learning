package wenyu.learning;

import java.util.ArrayList;

/**
 * Summary: All parameters to methods in Java are pass-by-value.
 * http://www.cs.toronto.edu/~dianeh/tutorials/params/
 * 
 * 
 * We use the term formal parameters to refer to the parameters in the definition of the method. 
 * In the example, x and y are the formal parameters. You can remember to call them ``formal'' 
 * because they are part of the method's definition, and you can think of a definition as being formal.
 *
 * We use the term actual parameters to refer to variables in the method call, in this case length and width. 
 * They are called ``actual'' because they determine the actual values that are sent to the method. 
 * 
 * You may have heard the term "argument" used, or just "parameter" (without specifying actual or formal). 
 * You can usually tell by the context which sort of parameter is being referred to. 
 */


class Method {
	
	public void primitiveTypesPassing(int i, double f, char c, boolean bool) {
		i += 10; //change int
	    c = 'z'; //change char
	    bool = (bool)?false:true; //change boolean    
	    f = 1.5; //change float
	    
	    System.out.println("Try to set i=i+10; c='z', reverse bool, f=1.5.");
	}
	
	public void objectReferencesPassing(ArrayList<String> arrayList) {
		arrayList.add("str add in method..."); // change object
		
		System.out.println("Try to add new value to list.");
	}
	
	public void objectReferencesPassing1(ArrayList<String> arrayList) {
		arrayList = new ArrayList<String>(); // new object
		arrayList.add("new object with new value...");
		
		System.out.println("Try to new an object and set new value it.");
	}
	
	public void stringPassing(String str) {
		str = "New value set in method...";
		
		System.out.println("Try to set str to 'New value set in method...'.");
	}
	
	public void integerPassing(Integer integer) {
		integer = 2;
		
		System.out.println("Try to set integer to 2.");
	}
	
	public void arraysPassing(char[] arrays) {
		arrays[0] = 'x';
		arrays[1] = 'y';
		arrays[2] = 'z';
		
		System.out.println("Try to set arrays new values...");
	}
}

public class MethodArgsDemo {
	boolean ifTestPrimitiveType = true;
	boolean ifTestObjectRef = true;
	boolean ifTestStringLike = true;
	boolean ifTestArrays = true;
	
	public void demoEntry() {
		
		Method method = new Method();
		
		if (ifTestPrimitiveType) {
			/**
			 * Passing Primitive Types
			 * 
			 * When Java calls a method, it makes a copy of its actual parameters' 
			 * values and sends the copies to the method where they become the values 
			 * of the formal parameters. Then when the method returns, those copies are 
			 * discarded and the actual parameters have remained unchanged. 
			 */
			
			// Initialize Passing Primitive Types
			int i = 1;
			double f = 1.0;
			char c = 'c';
			boolean bool = true;
			
			System.out.println("===============================================================");
			System.out.println("Passing Primitive Types:");
			method.primitiveTypesPassing(i, f, c, bool);
			System.out.println("After Call Method:");
			System.out.println("  i: " + i);
			System.out.println("  f: " + f);
			System.out.println("  c: " + c);
			System.out.println("  bool: " + bool);
			System.out.println("Result: value not changed!!!");
			System.out.println("===============================================================");
		}
		
		
		if (ifTestObjectRef) {
			/**
			 * Passing Object References
			 * 
			 * What if a parameter to a method is an object reference? 
			 * We can manipulate the object in any way, but we cannot 
			 * make the reference refer to a different object. 
			 */
			
			// Initialize Passing Object References
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("first value...");
			
			System.out.println("===============================================================");
			System.out.println("Passing Object References:");
			method.objectReferencesPassing(arrayList);
			System.out.println(arrayList);
			System.out.println("Result: value changed!!!");
			
			
			/**
			 * We cannot however make the object parameter refer to a 
			 * different object by reassigning the reference or calling 
			 * new on the reference.
			 */
			System.out.println("*****************************");
			method.objectReferencesPassing1(arrayList);
			System.out.println(arrayList);
			System.out.println("Result: value not changed!!!");
			System.out.println("===============================================================");
		}
		
		
		if (ifTestStringLike) {
			/**
			 * Passing Strings
			 * 
			 * string is an object, not a primitive. A new is indeed required; 
			 * it's just that Java does it behind the scenes. Java creates a 
			 * new object of class String and initializes it to contain the 
			 * string literal we have given it.
			 * 
			 * 
			 * Because str is an object, we might think that the string it 
			 * contains can be changed when we pass str as a parameter to a method.
			 * But that is wrong!!!
			 * Like other objects, when we pass a string to a method, we can in principle, 
			 * change things inside the object (although we can't change which string is referenced, 
			 * as we just saw). However, this capability is not useful with string because strings 
			 * are "immutable". They cannot be changed because the String class does not provide 
			 * any methods for modification. 
			 */
			
			// Initialize String
			String str = "initialized value...";
			
			System.out.println("===============================================================");
			System.out.println("Passing String:");
			method.stringPassing(str);
			System.out.println(str);
			System.out.println("Result: String value not changed!!!");
			
			// Initialize Integer
			Integer integer = 1;
			
			System.out.println("*****************************");
			System.out.println("Passing Integer:");
			method.integerPassing(integer);
			System.out.println(integer);
			System.out.println("Result: Integer value not changed!!!");
			System.out.println("===============================================================");
		}
		
		
		if (ifTestArrays) {
			/**
			 * Passing Arrays
			 * 
			 * Arrays are references. This means that when we pass an array as a parameter, 
			 * we are passing its handle or reference. So, we can change the contents of 
			 * the array inside the method. 
			 */
			
			// Initialize Arrays
			char[] array = {'a', 'b', 'c'};
			
			System.out.println("===============================================================");
			System.out.println("Passing Arrays:");
			method.arraysPassing(array);
			System.out.println(array);
			System.out.println("Result: value changed!!!");
			System.out.println("===============================================================");
		}
	}
	
	public static void main(String[] args) {
		new MethodArgsDemo().demoEntry();
	}

}
