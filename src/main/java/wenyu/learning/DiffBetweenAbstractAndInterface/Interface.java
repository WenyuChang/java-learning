package wenyu.learning.DiffBetweenAbstractAndInterface;

public interface Interface {
	int var = 1; // this variable in interface are all final by default
	
	// All method should be abstract and should be implemented.
	void fun();
	void fun1();
}

// Interface only can extends another interface!!!
interface SubInterface extends Interface {
	public void fun1();
	public void fun2();
}

class Cls implements SubInterface {

	@Override
	public void fun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fun1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fun2() {
		// TODO Auto-generated method stub
		
	}
	
}