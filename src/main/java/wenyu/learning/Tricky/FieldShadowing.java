package wenyu.learning.Tricky;

class AA {
	public int x = 1;
}

class BB extends AA {
	public int x = 2;
}

class CC extends BB {
	public int x = 3;
}

public class FieldShadowing {

	public static void main(String[] args) {
		AA ab = new BB();
		AA ac = new CC();
		BB bc = new CC();
		
		System.out.println("X: " + ab.x); //X: 1
		System.out.println("X: " + ac.x); //X: 1
		System.out.println("X: " + bc.x); //X: 2
	}
}
