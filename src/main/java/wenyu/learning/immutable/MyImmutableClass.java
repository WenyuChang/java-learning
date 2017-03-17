package wenyu.learning.immutable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/*
 * Make a class immutable by following these guidelines:
 * 1. ensure the class cannot be overridden - make the class final, 
 *    or use static factories and keep constructors private make fields private and final
 * 2. force callers to construct an object completely in a single step, instead of using a 
 *    no-argument constructor combined with subsequent calls to setXXX methods
 *    (that is, avoid the Java Beans convention)
 * 3. do not provide any methods which can change the state of the object in any way - 
 *    not just setXXX methods, but any method which can change state
 * 4. if the class has any mutable object fields, then they must be defensively copied when 
 *    they pass between the class and its call
 */

class MyClass implements Serializable  {
	MyClass(int intVar, int[] arrVar) {
		this.intVar = intVar;
		this.arrVar = arrVar;
	}
	int intVar;
	int[] arrVar;
	
	public String toString() {
		String out = "[intVar: " + intVar + ", ";
		out += "arrVar: " + arrVar.hashCode() + "]";
		return out;
	}
}

public final class MyImmutableClass {
	private final int primitiveVar;
	private final String strVar;
	private final int[] simpleObj;
	private final MyClass complexObj;
	
	public MyImmutableClass(int var1, String var2, int[] var3, MyClass var4) {
		primitiveVar = var1;
		strVar = var2;
		simpleObj = var3.clone();
		complexObj = (MyClass) deepCopy(var4);
	}
	
	private Object deepCopy(Object originObj) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(originObj);
			oos.flush();
			oos.close();
			bos.close();
			byte[] byteData = bos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
			Object copiedObj = (Object) new ObjectInputStream(bais).readObject();
			return copiedObj;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public String toString() {
		String out = "primitiveVar: " + primitiveVar + "\n";
		out += "strVar: " + strVar + "\n";
		out += "simpleObj: " + Arrays.toString(simpleObj) + "\n";
		out += "complexObj: " + complexObj;
		return out;
	}
	
	public static void main(String[] args) {
		int primitiveVar = 10;
		String strVar = "Wenyu";
		int[] simpleObj = {1,2,3};
		MyClass complexObj = new MyClass(3, new int[] {1,2,3});
		
		MyImmutableClass immuIns = new MyImmutableClass(primitiveVar, strVar, simpleObj, complexObj);
		System.out.println(immuIns);
		
		primitiveVar = 30;
		strVar = "Chang";
		simpleObj[0] = 30;
		complexObj.intVar = 30;
		complexObj.arrVar[0] = 30;
		
		System.out.println("==============");
		System.out.println(immuIns);
	}
}
