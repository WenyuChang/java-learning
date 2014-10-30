package wenyu.learning.GenericDemo;

/**
 * 
 * Generic Methods:
 * 
 * You can write a single generic method declaration that can be 
 * called with arguments of different types. Based on the types of
 * the arguments passed to the generic method, the compiler handles 
 * each method call appropriately. Following are the rules to define 
 * Generic Methods: 
 * 1. All generic method declarations have a type parameter section delimited 
 *    by angle brackets (< and >) that precedes the method's return type 
 *    ( < E > in the next example).
 * 2. Each type parameter section contains one or more type parameters separated 
 *    by commas. A type parameter, also known as a type variable, is an identifier 
 *    that specifies a generic type name.
 * 3. The type parameters can be used to declare the return type and act as place holders 
 *    for the types of the arguments passed to the generic method, which are known as 
 *    actual type arguments.
 * 4. A generic method's body is declared like that of any other method. Note that type 
 *    parameters can represent only reference types not primitive types (like int, double 
 *    and char).
 */

public class GenericMethod {

	public <T, Q, R> T setValue(T arg1, Q arg2, R arg3) {
		if (arg2 instanceof Integer) {
			int value = (Integer)arg2;
			System.out.println("arg1 is an integer...");
			
			value++;
		}
		
		if (arg3 instanceof String) {
			String str = (String)arg3;
			System.out.println("arg3 is a String...");
			
			str = "new value...";
		}
		
		return arg1;
	}
}
