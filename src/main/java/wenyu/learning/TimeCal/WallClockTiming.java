package wenyu.learning.TimeCal;

public class WallClockTiming {

	public static void doSth() {
		// TODO do something
	}
	
	public static void main(String[] args) {
		long startTimeMs = System.currentTimeMillis();
		doSth();
		long endTimeMs = System.currentTimeMillis();
		long taskTimeMs = endTimeMs - startTimeMs;
		System.out.println("taskTimeMs: " + taskTimeMs);
		
		
		long startTimeNano = System.nanoTime();
		doSth();
		long endTimeNano = System.nanoTime();
		long taskTimeNano = endTimeNano - startTimeNano;
		System.out.println("taskTimeNano: " + taskTimeNano);
	}

}
