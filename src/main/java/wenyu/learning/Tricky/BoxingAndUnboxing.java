package wenyu.learning.Tricky;

public class BoxingAndUnboxing {

	/*
	 * Autoboxing is the automatic conversion that the Java compiler makes 
	 * between the primitive types and their corresponding object wrapper classes. 
	 * 
	 * For example, converting an int to an Integer, a double to a Double, and so on. 
	 * If the conversion goes the other way, this is called unboxing.
	 */
	
	public static void cacheDemo() {
		/*
		 * Caching of immutable wrapper objects
			The Java specification indicates that certain primitives 
			are always to be boxed into the same immutable wrapper objects. 
			These objects are then cached and reused, with the expectation 
			that these are commonly used objects.
			
			These special values are 
				1. the boolean values true and false, 
				2. all byte values, 
				3. short int values between -128 and 127
				4. any char in the range \u0000 to \u007F.
		 */
		
		Integer i1 = 100;
		Integer i2 = 100;
		System.out.println("If i1(100) == i2(100): " + (i1==i2)); // True
		
		Integer i3 = 200;
		Integer i4 = 200;
		System.out.println("If i3(200) == i4(200): " + (i3==i4)); // False
		
		System.out.println("This's because integer only cache value which is within -127 ~ 127.\n");
		

		System.out.println("If i1(100).equal i2(100): " + (i1.equals(i2))); // True
		System.out.println("If i3(100).equal i4(100): " + (i3.equals(i4))); // True
	}
	
	public static void printSum(Integer value) {
		System.out.println("printSum(Integer value) is called...");
	}
	
	public static void printSum(int value) {
		System.out.println("printSum(int value) is called...");
	}
	
	public static void nullValueBoxing() {
		try {
			Integer i = null;
			int j = i;
		} catch (Exception ex) {
			System.out.println("Got exception when unboxing from a null object.");
			System.out.println(ex);
		}
	}
	
	public static void equalDemo() {
		int i = 1;
		Integer j = new Integer(1);
		
		System.out.println("int i(1) == Integer j(1): " + (i == j));
	}
		
	
	public static void main(String[] args) {
//		integerDemo();
		
		
		/*
		 * Method resolution Rules

		    1. The compiler attempts to locate the correct method without any boxing, 
		       unboxing, or vararg invocations. This will find any method that would 
		       have been invoked under Java 1.4 rules
		    2. If the first pass fails, the compiler tries method resolution again, this 
		       time allowing boxing and unboxing conversions. Methods with varargs are 
		       not considered in this pass
		    3. If the second pass fails, the compiler tries method resolution one last time, 
		       allowing boxing and unboxing, and also considers vararg methods (... method(int a, String... b))
		 */
//		printSum(13);
//		printSum(new Integer(13));
		
//		nullValueBoxing();
		
		equalDemo();
	}

}
 