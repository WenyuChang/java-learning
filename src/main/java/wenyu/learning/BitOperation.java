package wenyu.learning;

public class BitOperation {

	private static void print(int i) {
		String num = Integer.toBinaryString(i);
		int len = num.length();
		System.out.println(i + "'s bit expression is " + num + "(" + len + ").");
	}
	
	private static void print(float f) {
		String num = Float.toHexString(f);
		int len = num.length();
		System.out.println(f + "'s bit expression is " + num + "(" + len + ").");
	}
	
	public static void showBit() {
		int i = 0;
		print(i); // 00000000000000000000000000000000 (32)
		
		i = 1;
		print(i); // 00000000000000000000000000000001 (32)
		
		i = 2;
		print(i); // 00000000000000000000000000000010 (32)
		
		i = 999;
		print(i); // 00000000000000000000001111100111 (32)
		
		i = Integer.MAX_VALUE;
		print(i); // 01111111111111111111111111111111 (32)
		
		// x (negative number) = !|x| + 1 
		i = -1;
		print(i); // 11111111111111111111111111111111 (32)
		
		i = -2;
		print(i); // 11111111111111111111111111111110 (32)
		
		i = -999;
		print(i); // 11111111111111111111110000011001 (32)
		
		i = Integer.MIN_VALUE;
		print(i); // 10000000000000000000000000000000(32).

		// max+1 = min
		i = Integer.MAX_VALUE+1;
		print(i); // 10000000000000000000000000000000(32).
		
		// min-1 = max
		i = Integer.MIN_VALUE-1;
		print(i); // 01111111111111111111111111111111 (32)
		
		i = Integer.MIN_VALUE * -1;
		System.out.println(i); // 10000000000000000000000000000000(32) 
		// The result is still Integer.MIN_VALUE

		i = Integer.MAX_VALUE * -1;
		System.out.println(i); // 10000000000000000000000000000001(32)
		// The result is Integer.MIN_VALUE+1
		
		float f = 2.9f;
		print(f); // 0x1.733334p1 (12)
	}
	
	public static void bitOp() {
		int i=1;
		i = i<<1;
		print(i); // 2's bit expression is 10(2).
		
		i=1;
		i = i<<3;
		print(i); // 8's bit expression is 1000(4).
		
		i=1;
		i = i>>1;
		print(i); //0's bit expression is 0(1).
		
		i=1;
		i = i>>2;
		print(i); //0's bit expression is 0(1).
		
		i=999;
		print(i); //999's bit expression is 1111100111(10).
		i = i>>3;
		print(i); //124's bit expression is 1111100(7).
		
		i=-1;
		print(i); //-1's bit expression is 11111111111111111111111111111111(32).
		i  = i<<1;
		print(i); //-2's bit expression is 11111111111111111111111111111110(32).
		
		i=-2;
		print(i); //-2's bit expression is 11111111111111111111111111111110(32).
		i = i<<1;
		print(i); //-4's bit expression is 11111111111111111111111111111100(32).
		
		i=-3;
		i = i<<2;
		print(i); //-12's bit expression is 11111111111111111111111111110100(32).
		
		i=-1;
		i = i>>1;
		print(i); //-1's bit expression is 11111111111111111111111111111111(32).
		
		
		/** 
		 * Attention:
		 * The right-shift operator in m >> n shifts m to the right by n bits. 
		 * It inserts n 0s at the higher-order bits if m is positive and inserts n 1s if m is negative.
		 */
		i=-2;
		print(i); //-2's bit expression is 11111111111111111111111111111110(32).
		i = i>>1;
		print(i); //-1's bit expression is 11111111111111111111111111111111(32).
		
		i=-4;
		i = i>>1;
		print(i); //-2's bit expression is 11111111111111111111111111111110(32).
		
		/**
		 * Attention:
		 * The right operator >> is called a signed right-shift in Java, 
		 * and a new operator >>> named unsigned right-shift is introduced. 
		 * The operator in m >>> n also shifts m to the right by n bits, 
		 * and it always inserts 0s at the higher-order bits no matter 
		 * what the sign of m is.
		 */
		i=-1;
		print(i); //-1's bit expression is 11111111111111111111111111111111(32).
		i = i>>>1;
		print(i); //2147483647's bit expression is 01111111111111111111111111111111(32).
	}
	
	public static void main(String[] args) {
		// showBit();
		// bitOp();

		int i = Integer.MAX_VALUE;
		System.out.println(i);
		System.out.println(Math.pow(2, 31) - 1);
	}

}
