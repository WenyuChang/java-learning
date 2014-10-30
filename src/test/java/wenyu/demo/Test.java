package wenyu.demo;

public class Test {

	public static int func(int aa) {
		System.out.println("This is in func");
		return aa;
	}
	
	public String func(String bb) {
		System.out.println("This is also in func");
		return bb;
	}
	
	public static void main(String[] args) {
		System.out.println("This is Test...");
		
		System.out.println(func(3));
		
		System.out.println(new Test().func("3"));
		
		System.out.println(new Test().func(3));
	}
}
 