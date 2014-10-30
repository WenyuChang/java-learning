package wenyu.learning.JCA;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class KeyPairGeneratorDemo {
	public static String algorithm = "RSA"; // For demo, pls don't change
	public KeyPairGenerator generator;
	public KeyPair pair;
	
	public void initKeyPairGenerator(int keySize, SecureRandom random, AlgorithmParameterSpec spec) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		if(StandardAlgorithmNameMapping.KeyPairGenerator.ifValid(algorithm)) {
			generator = KeyPairGenerator.getInstance(algorithm);
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		if(random==null && spec==null && keySize>0) {
			generator.initialize(keySize);
			pair = generator.generateKeyPair();
		} else if(random!=null && spec==null && keySize>0) {
			generator.initialize(keySize, random);
			pair = generator.generateKeyPair();
		} else if(random==null && spec!=null) {
			generator.initialize(spec);
			pair = generator.generateKeyPair();
		} else if(random!=null && spec!=null) {
			generator.initialize(spec, random);
			pair = generator.generateKeyPair();
		}
		
		// Get the formats of the encoded bytes
		String privateKeyFormat = pair.getPrivate().getFormat();
		System.out.println("PrivateKey format : " + privateKeyFormat);
		String publicKeyFormat = pair.getPublic().getFormat();
		System.out.println("PublicKey format : " + publicKeyFormat);
	}
	
	public static void main(String[] args) throws Exception {
		new KeyPairGeneratorDemo().initKeyPairGenerator(1024, null, null);
	}

	// For exporting random to GeneralProperties
	public static void ExportValue() throws Exception {
		KeyPairGeneratorDemo demo = new KeyPairGeneratorDemo();
		demo.initKeyPairGenerator(1024, GeneralProperties.random, GeneralProperties.spec);
		GeneralProperties.privKey = demo.pair.getPrivate();
		GeneralProperties.pubKey = demo.pair.getPublic();
	}
}
