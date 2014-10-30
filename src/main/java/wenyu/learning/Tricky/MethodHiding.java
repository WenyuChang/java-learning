package wenyu.learning.Tricky;

class A {
	public int x = 1;
	public void foo() {
		System.out.println("A - foo");
	}
	
	public static void goo() {
		System.out.println("A - goo");
	}
}

class B extends A {
	public int x = 2;
	public void foo() {
		System.out.println("B - foo");
	}
	
	public static void goo() {
		System.out.println("B - goo");
	}
}

class C extends B {
	public int x = 3;
	public void foo() {
		System.out.println("C - foo");
	}
	
	public static void goo() {
		System.out.println("C - goo");
	}
}

public class MethodHiding {

	public static void main(String[] args) {
		A ab = new B();
		A ac = new C();
		B bc = new C();
		
		ab.foo(); //B - foo
		ab.goo(); //A - goo
		
		ac.foo(); //C - foo
		ac.goo(); //A - goo
		
		bc.foo(); //C - foo
		bc.goo(); //B - goo
	}
}
