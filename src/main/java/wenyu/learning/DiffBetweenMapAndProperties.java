package wenyu.learning;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

class InnerClass {
	public String toString() {
		return Integer.toString(this.hashCode());
	}
}

public class DiffBetweenMapAndProperties {

	private static void mapDemo() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("aaa", "bbb");
		map.put("bbb", null);
		map.put(null, null); // Hashmap allows null as key value, but hashtable doesn't.
		
		Set<Entry<Object,Object>> entries = map.entrySet();
		String returnStr = "Map - {";
		for(Entry<Object, Object> entry : entries) {
			returnStr += "(" + entry.getKey() + "/" + entry.getValue() + "),";
		}
		
		System.out.println(returnStr);
	}
	
	private static void propDemo() {
		Properties prop = new Properties();
		prop.put("aaa", "bbb");
		prop.put(new InnerClass(), new InnerClass());
		//prop.put(null, new InnerClass()); // Will cause exception
		//prop.put(new InnerClass(), null); // Will cause exception
		
		Set<Entry<Object,Object>> entries = prop.entrySet();
		String returnStr = "Properties - {";
		for(Entry<Object, Object> entry : entries) {
			returnStr += "(" + entry.getKey() + "/" + entry.getValue() + "),";
		}
		
		System.out.println(returnStr);
	}
	
	public static void main(String[] args) {
		mapDemo();
		propDemo();
	}

}
