package wenyu.learning.JCA;

import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;

public class KeyFactoryDemo {
	public static final String algorithm = "RSA";  // For demo, pls don't change
	
	// public-key
	public PublicKey pubKey;
	public PrivateKey privKey;
	public RSAPublicKeySpec RSAPubSpec;
	public RSAPrivateKeySpec RSAPrivSpec;
	public EncodedKeySpec privateEncodedKeySpec;
	public EncodedKeySpec publicEncodedKeySpec;

	// Symmetric-key
	public SecretKey secretKey;
	public SecretKeySpec secretKeySpec;
	public EncodedKeySpec secretEncodedKeySpec;
	
	private void initKey() throws Exception {
		SecureRandomDemo.ExportValue();
		
		if(GeneralProperties.privKey == null || GeneralProperties.pubKey == null) {
			KeyPairGeneratorDemo.ExportValue();
		}
		
		if(GeneralProperties.secKey == null) {
			KeyGeneratorDemo.ExportValue();
		}
		
		privKey = GeneralProperties.privKey;
		pubKey = GeneralProperties.pubKey;
		secretKey = GeneralProperties.secKey;
	}
	
	public void toRSASpec() throws Exception {
		if(pubKey==null || privKey==null) {
			initKey();
		}
		
		if(StandardAlgorithmNameMapping.KeyFactory.ifValid(algorithm)) {
			
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		KeyFactory factory = KeyFactory.getInstance(algorithm);
		RSAPubSpec = factory.getKeySpec(pubKey, RSAPublicKeySpec.class);
		RSAPrivSpec = factory.getKeySpec(privKey, RSAPrivateKeySpec.class);
	}
	
	public void toSecKeySpec() throws Exception {
		if(secretKey == null) {
			initKey();
		}
		
		if(StandardAlgorithmNameMapping.KeyFactory.ifValid(algorithm)) {
			
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		secretKeySpec = (SecretKeySpec) factory.getKeySpec(secretKey, SecretKeySpec.class);
	}
	
	public void toEncodedSpec() throws Exception {
		if(pubKey==null || privKey==null || secretKey==null) {
			initKey();
		}
		
		if(StandardAlgorithmNameMapping.KeyFactory.ifValid(algorithm)) {
			
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		// Get bytes of the public and private keys
		byte[] privateKeyBytes = privKey.getEncoded();
		byte[] publicKeyBytes = pubKey.getEncoded();
		
		// Get key pair Objects from their respective byte arrays
		// We initialize encoded key specifications based on the encoding formats
		privateEncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		publicEncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
	}
	
	public void toFile() throws Exception {
		if(pubKey==null || privKey==null || secretKey==null) {
			initKey();
		}
		
		/* save the public key in a file */
		byte[] privateKeyBytes = privKey.getEncoded();
		byte[] publicKeyBytes = pubKey.getEncoded();
		byte[] secretKeyBytes = secretKey.getEncoded();
		
		FileOutputStream keyfos = new FileOutputStream("src/main/java/wenyu/demo/JCA/privateKey.key");
		keyfos.write(privateKeyBytes);
		keyfos.close();
		
		keyfos = new FileOutputStream("src/main/java/wenyu/demo/JCA/publicKey.key");
		keyfos.write(privateKeyBytes);
		keyfos.close();
		
		keyfos = new FileOutputStream("src/main/java/wenyu/demo/JCA/secretKey.key");
		keyfos.write(privateKeyBytes);
		keyfos.close();
	}
	
	public static void main(String[] args) throws Exception {
		new KeyFactoryDemo().toFile();
	}

	// For exporting random to GeneralProperties
	public static void ExportValue() throws Exception {
		KeyFactoryDemo demo = new KeyFactoryDemo();
		
		demo.toRSASpec();
		demo.toSecKeySpec();
		GeneralProperties.RSAPrivSpec = demo.RSAPrivSpec;
		GeneralProperties.RSAPubSpec = demo.RSAPubSpec;
		GeneralProperties.secretKeySpec = demo.secretKeySpec;
	}
}
