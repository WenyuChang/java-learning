package wenyu.learning.Tricky;

public class ReturnInCatch {

	public static void test() {
		String str = "abc";
		
		try {
			System.out.println("Before parsing...");
			Integer num = Integer.parseInt(str);
			System.out.println("After parsing..."); //will not run...
		} catch(Exception ex) {
			System.out.println("Getting exception and try return...");
			return;
		} finally {
			System.out.println("Finally...");
		}
	}
	
	public static void test1() {
		String str = "abc";
		
		try {
			System.out.println("Before parsing...");
			Integer num = Integer.parseInt(str);
			System.out.println("After parsing..."); //will not run...
		} catch(Exception ex) {
			System.out.println("Getting exception and try return...");
			System.exit(0); 
			// Terminates the currently running Java Virtual Machine. 
			// The argument serves as a status code; by convention, 
			// a nonzero status code indicates abnormal termination. 
		} finally {
			System.out.println("Finally...");
		}
	}
	
	public static int test2() throws Exception {
		String str = "abc";
		
		try {
			System.out.println("Before parsing...");
			Integer num = Integer.parseInt(str);
			System.out.println("After parsing..."); //will not run...
		} catch(Exception ex) {
			throw new Exception("My Exception");
		} finally {
			System.out.println("Finally...");
		}
		
		return 123;
	}
	
	public static int test3() throws Exception {
		String str = "abc";
		
		try {
			System.out.println("Before parsing...");
			Integer num = Integer.parseInt(str);
			System.out.println("After parsing..."); //will not run...
		} catch(Exception ex) {
			throw new Exception("My Exception");
		} finally {
			System.out.println("Finally...");
			return 123;
		}	
	}
	
	public static int test5() throws Exception {
		String str = "abc";
		
		try {
			System.out.println("Before parsing...");
			Integer num = Integer.parseInt(str);
			System.out.println("After parsing..."); //will not run...
		} finally {
			System.out.println("Finally...");
			return 123;
		}	
	}
	
	public static void main(String[] args) {
		System.out.println("=================== test ======================");
		System.out.println("Before testing...");
		test();
		System.out.println("After testing...");
		
		/*
		 * Output:
		 *  Before testing...
			Before parsing...
			Getting exception and try return...
			Finally...
			After testing...
		 */

		System.out.println("=================== test2 ======================");
		
		try {
			System.out.println("Before testing...");
			int returnInt = test2();
			System.out.println(returnInt);
			System.out.println("After testing...");
		} catch(Exception ex) {
			System.out.println("Excpetion from outsite");
		}
		
		/*
		 * Output:
		 *  Before testing...
			Before parsing...
			Finally...
			Excpetion from outsite
		 */

		System.out.println("=================== test3 ======================");
		
		try {
			System.out.println("Before testing...");
			int returnInt = test3();
			System.out.println(returnInt);
			System.out.println("After testing...");
		} catch(Exception ex) {
			System.out.println("Excpetion from outsite");
		}
		
		/*
		 * Output: ("return" in the finally will make JVM ignore exception above)
		 *  Before testing...
			Before parsing...
			Finally...
			123
			After testing...
		 */

		System.out.println("=================== test1 ======================");
		
		System.out.println("Before testing...");
		test1();
		System.out.println("After testing...");
		
		/*
		 * Output: (directly terminate the JVM, so no finally print shows)
		 *  Before testing...
			Before parsing...
			Getting exception and try return...
		 */

		System.out.println("=================== test5 ======================");
		
		try {
			System.out.println("Before testing...");
			int returnInt = test5();
			System.out.println(returnInt);
			System.out.println("After testing...");
		} catch(Exception ex) {
			System.out.println("Excpetion from outsite");
		}
		
		/*
		 * Output: ("return" in the finally will make JVM ignore exception above)
		 *  Before testing...
			Before parsing...
			Finally...
			123
			After testing...
		 */

		
	}

}
