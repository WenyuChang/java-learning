package wenyu.learning;

/*
 * This includes final classes, final variables, and final methods:
 * A final class cannot be extended by any other class
 * A final variable cannot be reassigned to another value
 * A final method cannot be overridden
 */
public class JavaFinalDemo {
	public final int pre = 0;
	
	/*
	 * A blank final variable of a 
	 * class must be definitely assigned
	 * in every constructor of the class 
	 * in which it is declared.
	 */
	public final int pre1; 
	public final int[] array;
	
	public JavaFinalDemo() {
		pre1 = 1; // pre1 (blank final variable) must be initialized in every constructor
		array = new int[1];
		array[0] = 1;
	}
	
	public JavaFinalDemo(String aa) {
		pre1 = 2; // pre1 (blank final variable) must be initialized in every constructor
		array = new int[2];
		array[0] = 1;
	}
	
	public void modifyFinal() {
		// The value of final can be changed!!!
		array[0] = 2;
		
		// Failed to do the following commands;
		// ii = 1;
		// array = new int[1];
	}
	
	public void finalArgumentDemo(int var1, final int var2) {
		var1 = 20; // this value can be changed
		
		// Failed to do the following commands;
		// var2 = 30;
		
		final int var3 = 30;
		// Failed to do the following commands;
		// var3 = 10;
	}
	
	public static void main(String[] args) {
		JavaFinalDemo instance = new JavaFinalDemo();
		System.out.println(instance.array[0]);
		instance.modifyFinal();
		System.out.println(instance.array[0]);
	}

}
