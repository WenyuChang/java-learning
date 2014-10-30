package wenyu.learning;

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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		demoEntry();
	}
}
