package wenyu.learning.Reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String annoName = "MyRuntimePolicyAnnotation";
}

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation1 {
    String annoName = "MyRuntimePolicyAnnotation1";
}


public class MyClass {
	// class fields
	@MyAnnotation
	public static String pubStaticParam;
	
	// instance fields
	@MyAnnotation
	public String pubParam;
	@MyAnnotation
	protected String proParam;
	@MyAnnotation
	private String privParam;
	
	// Constructors
	@MyAnnotation
	public MyClass(String param1, int param2, ArrayList<String> strs) {
		System.out.println("Invoke public constructor... and parameter is " + param1 + "/" + param2);
	}
	public MyClass() {
		System.out.println("Invoke public default constructor...");
	}
	private MyClass(String param1, int param2, String[] strs) {
		System.out.println("Invoke public constructor... and parameter is " + param1 + "/" + param2);
	}
	
	// static method
	@MyAnnotation
	public static String clsMethod(@MyAnnotation String param, @MyAnnotation1 @MyAnnotation String param1) throws Exception {
		String returnVal = "Invoke public class method... and parameter is " + param;
		System.out.println("Invoke public class method... and parameter is " + param);
		return returnVal;
	}
	
	@MyAnnotation
	private static String clsPrivateMethod(@MyAnnotation String param, @MyAnnotation1 @MyAnnotation String param1) throws Exception {
		String returnVal = "Invoke private class method... and parameter is " + param;
		System.out.println("Invoke private class method... and parameter is " + param);
		return returnVal;
	}
	
	// instance methods
	@MyAnnotation 
	public String insMethod(@MyAnnotation String param, String param1) throws Exception {
		String returnVal = "Invoke public instance method... and parameter is " + param;
		System.out.println("Invoke public instance method... and parameter is " + param);
		return returnVal;
	}
}
