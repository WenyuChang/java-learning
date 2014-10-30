package wenyu.learning.RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldImpl extends UnicastRemoteObject implements IHelloWorld {

	private static final long serialVersionUID = 1L;

	public HelloWorldImpl() throws RemoteException {
		
	}
	
	public static void main(String[] args) {
		try {
			HelloWorldImpl ins = new HelloWorldImpl();
			Naming.rebind("HelloWorldServer", ins);
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
            e.printStackTrace(); 
		}

	}

	@Override
	public String sayHello() throws RemoteException {
		System.out.println("Got request from client. And say hello world...");
		return "Return Hello World";
	}

}
