package wenyu.learning.JCA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.xml.bind.DatatypeConverter;

public class SignatureDemo {
	public static final String algorithm = "SHA512withRSA"; // For demo, pls don't change
	public PrivateKey privKey;
	public PublicKey pubKey;
	public Signature sign;
	public byte[] signedResult;
	
	public void initKey() throws Exception {
		if(GeneralProperties.privKey == null || GeneralProperties.pubKey == null) {
			SecureRandomDemo.ExportValue();
			KeyPairGeneratorDemo.ExportValue();
			
			privKey = GeneralProperties.privKey;
			pubKey = GeneralProperties.pubKey;
		}
	}
	
	public void initSignSignature() throws Exception {
		if(StandardAlgorithmNameMapping.Signature.ifValid(algorithm)) {
			sign = Signature.getInstance(algorithm);
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		initKey();
		sign.initSign(privKey);
	}
	
	public void initVeriferSignature() throws Exception {
		if(StandardAlgorithmNameMapping.Signature.ifValid(algorithm)) {
			sign = Signature.getInstance(algorithm);
		} else {
			System.out.println("Pls input correct algorithm.");
			return;
		}
		
		initKey();
		sign.initVerify(pubKey);
	}
	
	public void sign(String[] messages) throws Exception {
		initSignSignature();
		
		for(String message:messages) {
			sign.update(message.getBytes());
		}
		signedResult = sign.sign();
		System.out.println("The signed result is: " + DatatypeConverter.printBase64Binary(signedResult));
	}
	
	public void verify(String[] messages) throws Exception {
		if(signedResult == null) {
			return;
		}
		
		initVeriferSignature();
		
		for(String message:messages) {
			sign.update(message.getBytes());
		}
		boolean result = sign.verify(signedResult);
		String resultMessage = result?"Successed":"Failed";
		System.out.println(String.format("%s to verify!", resultMessage));
	}
	
	public static void main(String[] args) throws Exception {
		String[] messages = {"message-1", "message-2", "message-3"};
		String[] messages1 = {"message-3", "message-4", "message-5"};
		
		SignatureDemo demo = new SignatureDemo();
		
		demo.sign(messages);
		
		demo.verify(messages);
		demo.verify(messages1);
	}
}
