package wenyu.learning;


/**
 * String vs StringBuffer vs StringBuilder
 * 
 * The most important difference between 
 * String and StringBuffer/StringBuilder 
 * in java is that String object is immutable 
 * whereas StringBuffer/StringBuilder objects 
 * are mutable.
 * 
 * StringBuffer is synchronized whereas 
 * StringBuilder is not synchronized
 */

public class StringOptimization {

	public static void stringVersion() {
		/*
		 * It is rather pointless to use StringBuffer if the concatenation is very simple like:
		 * 		String a = "aa" + "bb";
		 * BUT it is very unperformant to concatenate strings in a loop, like:
		 * 		String a = "";
		 * 		for (int i = 0; i < 10; i++) {
		 * 			s = s + Integer.toString(i);
		 * 		}
		 * Because using string concatenation will generate intermediate string objects in memory
		 */
		
		String str = "This string is formed by string concatenation.\n";
		for (int i = 0; i < 10; i++) {
		    str += i;
		}
		System.out.println(str);
	}
	
	public static void stringBufferVersion() {
		StringBuffer sb = new StringBuffer("This string is formed by string buffer (synchronized).\n");
		for (int i = 0; i < 10; i++) {
		    sb.append(i);
		}
		String str = sb.toString();
		System.out.println(str);
	}

	public static void stringBuilderVersion() {
		StringBuilder sb = new StringBuilder("This string is formed by string builder (unsynchronized).\n");
		for (int i = 0; i < 10; i++) {
		    sb.append(i);
		}
		String str = sb.toString();
		System.out.println(str);
	}
	
	public static void demoEntry() {
		
		stringVersion();
		stringBufferVersion();
		stringBuilderVersion();
		
	}
	
	public static void main(String[] args) {
		demoEntry();
	}

}
