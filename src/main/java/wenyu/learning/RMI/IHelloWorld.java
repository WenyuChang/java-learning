package wenyu.learning.RMI;

import java.rmi.RemoteException;

/*
 * Reference: http://www.eg.bucknell.edu/~cs379/DistributedSystems/rmi_tut.html
 */

public interface IHelloWorld extends java.rmi.Remote {
	
	public String sayHello() throws RemoteException;
	
}
