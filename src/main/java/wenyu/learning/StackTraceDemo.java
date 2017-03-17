package wenyu.learning;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceDemo {

	public static void demoEntry() {
		Throwable throwable = new Throwable();
		
		// Just output the stack trace by Throwable
		throwable.printStackTrace();
		
		// Output the trace manually
		StackTraceElement[] stackTraces = throwable.getStackTrace();
		for (StackTraceElement elem : stackTraces) {
			System.out.println(elem.toString());
			//System.out.println(StackTraceElement.class.getName());
		}
	}
	
	public static String stackTraceStr() {
		Throwable throwable = new Throwable();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		demoEntry();
	}
}
