package wenyu.learning.JCA;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomDemo {
	public static String algorithms = "SHA1PRNG";
	public SecureRandom random;
	
	public void initSecureRandom() throws NoSuchAlgorithmException {
		if(algorithms == null || algorithms == "") {
			random = new SecureRandom();
		} else if (StandardAlgorithmNameMapping.SecureRandom.ifValid(algorithms)) {
			random = SecureRandom.getInstance(algorithms);
		} else {
			throw new NoSuchAlgorithmException();
		}
	}
	
	public void setSeed(Object seed) throws Exception {
		if(random == null) {
			initSecureRandom();
		}
		
		if(seed == null) {
			return;
		}
		
		if(seed instanceof Long) {
			// void java.security.SecureRandom.setSeed(long seed)
			random.setSeed((Long)seed);
		} else if(seed instanceof byte[]) {
			// void java.security.SecureRandom.setSeed(byte[] seed)
			random.setSeed((byte[])seed);
		}
	}
	
	public void getRandomNextValue(int times) throws Exception {
		if(random == null) {
			initSecureRandom();
		}
		
		for(int i=0; i<times; i++) {
			System.out.println("Next Boolean: " + random.nextBoolean());
			System.out.println("Next Float: " + random.nextFloat());
			System.out.println("Next Gaussian: " + random.nextGaussian());
			System.out.println("Next Integer: " + random.nextInt());
			System.out.println("Next Integer from 0 to 999999: " + random.nextInt(999999));
			System.out.println("Next Long: " + random.nextLong());
			System.out.println("=======================================================");
		}
	}
	
	public static void main(String[] args) throws Exception {
		int loopCount = 10;
		SecureRandomDemo demo = new SecureRandomDemo();
		
		demo.initSecureRandom();
		demo.setSeed(321); // Optional
		demo.getRandomNextValue(loopCount);
	}

	// For exporting random to GeneralProperties
	public static void ExportValue() throws Exception {
		SecureRandomDemo demo = new SecureRandomDemo();
		demo.initSecureRandom();
		demo.setSeed(321); // Optional
		GeneralProperties.random = demo.random;
	}
}
