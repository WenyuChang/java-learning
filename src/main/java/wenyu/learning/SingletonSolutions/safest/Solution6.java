package wenyu.learning.SingletonSolutions.safest;

public enum Solution6 {
	INSTANCE;
	
	public int value = 0;;
	public void doSth() {
		System.out.println("value:" + value);
	}
}
