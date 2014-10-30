package wenyu.learning.RMI;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class HelloClient {

	public static void main(String[] args) {
		String message = "blank";
		System.setSecurityManager(new RMISecurityManager());
		
		try {
			IHelloWorld ins = (IHelloWorld) Naming.lookup("//" + "localhost" + "/HelloWorldServer"); //objectname in registry 
			System.out.println(ins.sayHello());
		} catch (Exception ex) {
			System.out.println("HelloClient exception: " + ex.getMessage());
	        ex.printStackTrace(); 
		}

	}

}
