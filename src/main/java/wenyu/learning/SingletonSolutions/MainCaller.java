package wenyu.learning.SingletonSolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import wenyu.learning.SingletonSolutions.safest.Solution7;


public class MainCaller {
	/*
	 * http://technonstop.com/java-singleton-reflection-and-lazy-initialization
	 */
	public static void main(String[] args) {
		// Workable Only in Single-Threading Applications
	    // Solution1 ins1 = Solution1.getInstance();
		
		// Works with Multiple Threads but Is Inefficient
		//  ins2 = Solution2.getInstance();
		
		// Double-Check around Locking, much more efficient than solution2
		// Solution3 ins3 = Solution3.getInstance();
		
		// Utilization of Static Constructors, but create an instance too early, and it impairs the space efficiency
		// Solution4 ins4 = Solution4.getInstance();
		// ins4.print();
		
		// Almost best solution, creating an instance When Necessary
		// Solution5 ins5 = Solution5.getInstance();
		
		// Best solution, but not popular
		// Solution6 ins6 = Solution6.INSTANCE;
		
//		Solution7 ins7 = Solution7.getInstance();
//		System.out.println(ins7);
//		try {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			ObjectOutputStream oos = new ObjectOutputStream(bos);
//			oos.writeObject(ins7);
//			oos.flush();
//			oos.close();
//			bos.close();
//			byte[] byteData = bos.toByteArray();
//			ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
//			Solution7 copiedObj = (Solution7) new ObjectInputStream(bais).readObject();
//			System.out.println(copiedObj);
//		} catch (Exception ex) {
//			
//		}
	}

	public void breakSingleton() {
		/*
		 * It can break if the class is Serializable
		 * It can break if its 'Clonable`
		 * You can break by Reflection (I believe)
		 * it can break if multiple classloaders are loaded the class
		 */
	}
}
