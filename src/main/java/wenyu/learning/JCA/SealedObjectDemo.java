package wenyu.learning.JCA;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;

class MyObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PROP_1 = "prop1";
	public static final String PROP_2 = "prop2";
	public static final String PROP_3 = "prop3";
	
	public String toString() {
		return PROP_1 + "/" + PROP_2 + "/" + PROP_3;
	}
}

public class SealedObjectDemo {
	public PrivateKey privKey;
	public PublicKey pubKey;
	
	public MyObject obj; // Object will be sealed
	public SealedObject so;
	
	public void initSealedObj() throws Exception {
		if(obj == null) {
			obj = new MyObject();
		}

		Cipher cipher = Cipher.getInstance("RSA");
		if(GeneralProperties.privKey == null || GeneralProperties.pubKey == null) {
			SecureRandomDemo.ExportValue();
			KeyPairGeneratorDemo.ExportValue();
			
			privKey = GeneralProperties.privKey;
			pubKey = GeneralProperties.pubKey;
		}
		cipher.init(Cipher.ENCRYPT_MODE, privKey);
		
		so = new SealedObject(obj, cipher);
		System.out.println("Successed to seal object.");
	}
	
	public void compareSealedObj(Object comObj) throws Exception {
		if(so == null) {
			initSealedObj();
		}
		
		boolean ifSame = so.equals(comObj);
		if(ifSame) {
			System.out.println("The object is the same as sealed one.");
		} else {
			System.out.println("The object is not the same as sealed one.");
		}
	}
	
	public void decryptSealedObj() throws Exception {
		if(so == null) {
			initSealedObj();
		}
		
		Cipher cipher = Cipher.getInstance("RSA");
		if(GeneralProperties.privKey == null || GeneralProperties.pubKey == null) {
			SecureRandomDemo.ExportValue();
			KeyPairGeneratorDemo.ExportValue();
			
			privKey = GeneralProperties.privKey;
			pubKey = GeneralProperties.pubKey;
		}
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		MyObject decrypted = (MyObject) so.getObject(cipher);
		System.out.println(decrypted);
	}
	
	public static void main(String[] args) throws Exception {
		SealedObjectDemo demo = new SealedObjectDemo();
		
		demo.initSealedObj();
		//demo.compareSealedObj(demo.so);
		demo.decryptSealedObj();
	}

}
