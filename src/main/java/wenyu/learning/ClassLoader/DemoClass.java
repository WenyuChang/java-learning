package wenyu.learning.ClassLoader;

public class DemoClass {
	public String value = "";
	
	public DemoClass() {
		value = "Default";
	}
	
	public DemoClass(String value) {
		this.value = value;
	}
	
	public String toString() {
		return "Class: DemoClass - Value: " + value;
	}
}
