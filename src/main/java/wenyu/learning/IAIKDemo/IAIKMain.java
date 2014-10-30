package wenyu.learning.IAIKDemo;

import iaik.pkcs.pkcs8.EncryptedPrivateKeyInfo;
import iaik.security.ec.provider.ECCelerate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class IAIKMain {
	private boolean ifUsingEncryptedPrivKey = false;
	public static ECCelerate eccProvider;
	
	public PrivateKey privKey;
	public PublicKey pubKey;
	
	
	static {
		eccProvider  = new ECCelerate();
		Security.addProvider(eccProvider);
	}
	
	public void getKeysFromFile() throws Exception {
		FileInputStream fs = null;
		byte[] encoded = null;
		
		if(ifUsingEncryptedPrivKey) {
			fs = new FileInputStream("src/main/java/wenyu/demo/IAIKDemo/EncryptedPrivateKey.der");
			encoded = new byte[fs.available()];
			fs.read(encoded);
			EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(encoded);
			char[] password = "".toCharArray();
			privKey = epki.decrypt(password);
			fs.close();
		} else {
			fs = new FileInputStream("src/main/java/wenyu/demo/IAIKDemo/PrivateKey.der");
			encoded = new byte[fs.available()];
			fs.read(encoded);
			KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", eccProvider.getName());
			PKCS8EncodedKeySpec keyspec = new PKCS8EncodedKeySpec(encoded);
			privKey = keyFactory.generatePrivate(keyspec);
		}
		
		
		if(ifUsingEncryptedPrivKey) {
			fs = new FileInputStream("src/main/java/wenyu/demo/IAIKDemo/EncryptedPublicKey.der");
		} else {
			fs = new FileInputStream("src/main/java/wenyu/demo/IAIKDemo/PublicKey.der");
		}
		encoded = new byte[fs.available()];
		fs.read(encoded);
		fs.close();
		
		
		KeyFactory keyFactory = KeyFactory.getInstance("ECDSA",eccProvider.getName());
		pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encoded));
	}
	
	public void testKeys() throws Exception {
		if(pubKey==null || privKey==null) {
			getKeysFromFile();
		}
		
		Signature sig = Signature.getInstance("SHA256WithECDSA");
		sig.initSign(privKey);
		sig.update("wenyu chang".getBytes());
		byte[] sigDigest = sig.sign();
		
		
		sig.initVerify(pubKey);
		sig.update("wenyu chang".getBytes());
		String verifyResult = sig.verify(sigDigest)?"Successed":"Failed";
		System.out.println(String.format("%s to test keys.", verifyResult));
	}
	
	public static void main(String[] args) throws Exception {
		IAIKMain demo = new IAIKMain();
		demo.testKeys();
	}
}
