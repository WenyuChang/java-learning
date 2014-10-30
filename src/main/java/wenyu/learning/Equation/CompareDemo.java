package wenyu.learning.Equation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * In Java, every object has access to the equals() 
 * method because it is inherited from the Object class. 
 * 
 * However, this default implementation just simply compares 
 * the memory addresses of the objects. You can override the 
 * default implementation of the equals() method defined in 
 * java.lang.Object. If you override the equals(), you MUST 
 * also override hashCode(). Otherwise a violation of the general 
 * contract for Object.hashCode will occur, which can have 
 * unexpected repercussions when your class is in conjunction 
 * with all hash-based collections.
 * 
 * ******************* Important ************************
 * 1. If two objects are equal according to the equals(Object) 
 *    method, then calling the hashCode method on each of the 
 *    two objects must produce the same integer result.
 * 2. If two objects have the same hashcode, they may or 
 *    may not be equal. 
 */

public class CompareDemo {

	private static void primitiveDemo() {
		int a=1;
		int b=1;
		float c = 2.3f;
		float d = 2.3f;
		float e = 1.0f;
		double f = 2.3;
		double g = 1;
		
//		System.out.println("a: " + a);
//		System.out.println("b: " + b);
//		System.out.println("c: " + c);
//		System.out.println("d: " + d);
//		System.out.println("e: " + e);
//		System.out.println("f: " + f);
//		System.out.println("g: " + g);
		
		
		System.out.println("a==b: " + (a==b)); //true
		System.out.println("c==d: " + (c==d)); //true
		System.out.println("a==e: " + (a==e)); //true
		System.out.println("c==f: " + (c==f)); //false
		System.out.println("a==g: " + (a==g)); //true
	}
	
	private static void buildInObjDemo() {
		Object a = new Object();
		Object b = new Object();
		System.out.println("a==b: " + (a==b)); //false
		System.out.println("a.equals(b): " + a.equals(b)); //false
		
		// Since Java has override hashcode() and equals() of these types
		String c = "abc";
		String d = "abc";
		System.out.println("c==d: " + (c==d)); //true
		System.out.println("c.equals(d): " + c.equals(d)); //true
		
		String e = new String("abc");
		String f = new String("abc");
		System.out.println("e==f: " + (e==f)); //false
		System.out.println("e.equals(f): " + e.equals(f)); //true
		
		String h = new String("abc").intern();
		System.out.println("h==c: " + (c==h)); //true
		System.out.println("h.equals(c): " + h.equals(c)); //true
		
		// Same as Integer, Double, Character....
		
		
		//Below is not sam as Integer, Double, Character... 
		// Since Integer, Double, Character... is related boxing and unboxing
		// See the code under tricky/BoxingAndUnboxing.java
		String i = "abc";
		String j = new String("abc");
		String k = new String("abc").intern();
		System.out.println("i==j: " + (i==j)); //false
		System.out.println("i==k: " + (i==k)); //true
		System.out.println("i.equals(j): " + i.equals(j)); //true
		System.out.println("i.equals(k): " + i.equals(k)); //true
	}
	
	private static void selfObjDemo() {
		class Obj {
			int value;
			public Obj(int val) {
				this.value = val;
			}
		}
		
		Obj a = new Obj(1);
		Obj b = new Obj(1);
		
		// Since we didn't override hashcode() and equals()
		System.out.println("a==b: " + (a==b)); //false
		System.out.println("a.equals(b): " + a.equals(b)); //false
	}
	
	private static void hashDemo() {
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("abc");
		hashSet.add("abc");
		Iterator<String> it=hashSet.iterator(); 
        while(it.hasNext()) 
        {
        	System.out.println(it.next()); 
        }

        hashSet.clear();
        System.out.println("======== clear ======");
        
        hashSet.add(new String("abc"));
        hashSet.add(new String("abc"));
        it=hashSet.iterator(); 
        while(it.hasNext()) 
        {
        	System.out.println(it.next()); 
        }
        
        hashSet.clear();
        System.out.println("======== clear ======");
        
        class MyString {
			String value;
			public MyString(String val) {
				this.value = val;
			}
			public String toString() {
				return value;
			}
		}
        
        Set<MyString> hashSet1 = new HashSet<MyString>();
        hashSet1.add(new MyString("abc"));
        hashSet1.add(new MyString("abc"));
        Iterator<MyString> it1=hashSet1.iterator(); 
        while(it1.hasNext()) 
        {
        	System.out.println(it1.next()); 
        }
        
        // Same as hashMap
	}
	
	private static void otherHashDemo() {
		Set<String> treeSet = new TreeSet<String>();
		treeSet.add("ghi");
		treeSet.add("abc");
		treeSet.add("abc");
		treeSet.add("def");
		Iterator<String> it = treeSet.iterator(); 
        while(it.hasNext()) 
        {
        	System.out.println(it.next()); 
        }
	}
	
	public static void demoEntry() {
		//primitiveDemo();
		
		// buildInObjDemo();
		
		//selfObjDemo();
		
		hashDemo();
		
//		otherHashDemo();
	}
	
	public static void main(String[] args) {
		demoEntry();
	}

}
