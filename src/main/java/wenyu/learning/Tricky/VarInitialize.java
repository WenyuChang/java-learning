package wenyu.learning.Tricky;

public class VarInitialize {

	public static int var0;
	public int var2;
	
	public static void func() {
		// The following print statement will cause an error.
		// The error message is: 
		// The local variable var may not have been initialized
		// int var;
		// System.out.println(var);
		
		int var1 = 10;
		System.out.println(var1);
	}
	
	public static void func1() {
		// No exception and the result is 0
		System.out.println(var0);
		
		// No exception and the result is also 0
		VarInitialize ins = new VarInitialize();
		System.out.println(ins.var2);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
