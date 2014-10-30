package wenyu.learning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionUsage {
	public static void usage() {
		String str = "43224s";
		Pattern pattern = Pattern.compile("[0-9]+(d|h|m|s|D|H|M|S)");
		Matcher mather = pattern.matcher(str);
		if (mather.matches()) {
			System.out.println("Match...");
		} else {

			System.out.println("No Match...");
		}
	}
}
