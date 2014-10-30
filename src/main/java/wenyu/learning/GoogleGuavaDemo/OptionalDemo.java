package wenyu.learning.GoogleGuavaDemo;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class OptionalDemo {

	public static void demoEnrty() {
		
		Optional<Integer> int_value = Optional.of(5);
		int_value.isPresent(); // returns true
		System.out.println(int_value.get()); // returns 5
		
		String initStr = null; // set string to null
		String str = Strings.nullToEmpty(initStr);
		System.out.println("Converted Str: <" + str + ">"); // won't cause null point exception.
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		demoEnrty();
	}

}
