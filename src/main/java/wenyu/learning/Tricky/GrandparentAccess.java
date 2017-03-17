package wenyu.learning.Tricky;

public class GrandparentAccess {
	// filename Main.java
	static class Grandparent {
	    public void print() {
	        System.out.println("Grandparent's print()");
	    }
	}
	  
	static class Parent extends Grandparent {
	    public void print() {       
	        System.out.println("Parent's print()");
	    }
	    public void printParent() {
	    	super.print();
	    }
	}
	  
	static class Child extends Parent {
	    public void print() {
	        // super.super.print();  // Trying to access Grandparent's print(), but compile error
	        System.out.println("Child's print()");
	    }
	    public void printGrandparent() {
	    	super.printParent();
	    }
	}
	  
	public static void main(String[] args) {
        Child c = new Child();
        c.print();
        c.printParent();
        c.printGrandparent();
    }
}
