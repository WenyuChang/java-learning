package wenyu.learning.JCA;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneratorDemo {
	public static String algorithm = "AES"; // For demo, pls don't change
	public SecretKey secretKey;
	public KeyGenerator generator;
	
	public void initKeyGenerator(int keySize, SecureRandom random, AlgorithmParameterSpec spec) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		if(StandardAlgorithmNameMapping.KeyGenerator.ifValid(algorithm)) {
			generator = KeyGenerator.getInstance(algorithm);
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		if(random==null && spec==null && keySize>0) {
			generator.init(keySize);
			secretKey = generator.generateKey();
		} else if(random!=null && spec==null && keySize>0) {
			generator.init(keySize, random);
			secretKey = generator.generateKey();
		} else if(random==null && spec!=null) {
			generator.init(spec);
			secretKey = generator.generateKey();
		} else if(random!=null && spec!=null) {
			generator.init(spec, random);
			secretKey = generator.generateKey();
		}
		
		// Get the formats of the encoded bytes
		String secretKeyFormat = secretKey.getFormat();
		System.out.println("SecretKey format : " + secretKeyFormat);
	}
	
	public static void main(String[] args) throws Exception {
		new KeyGeneratorDemo().initKeyGenerator(256, null, null);
	}

	// For exporting random to GeneralProperties
	public static void ExportValue() throws Exception {
		KeyGeneratorDemo demo = new KeyGeneratorDemo();
		demo.initKeyGenerator(256, GeneralProperties.random, GeneralProperties.spec);
		GeneralProperties.secKey = demo.secretKey;
	}
}
