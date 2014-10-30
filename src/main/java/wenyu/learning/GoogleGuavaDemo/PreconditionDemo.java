package wenyu.learning.GoogleGuavaDemo;

import com.google.common.base.Preconditions;

public class PreconditionDemo {

	public static void demoEnrty() {
		// Ensures the truth of an expression involving one or more parameters to the calling method.
		try {
			Preconditions.checkArgument(2<1);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		}
		
		// 
		try {
			Preconditions.checkArgument(-1 >= 0, "Argument was %s but expected nonnegative", -1);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		}
		
		try {
			String nullstr = null;
			Preconditions.checkNotNull(nullstr, "Argument was null...");
		} catch (NullPointerException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		demoEnrty();
	}

}
