package wenyu.learning.JCA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class MessageDigestDemo {
	public static String algorithm = "MD5";
	public MessageDigest digest;
	
	public void initMessageDigest() throws NoSuchAlgorithmException {
		if(algorithm == null || algorithm == "") {
			System.out.println("Pls input correct algorithms.");
		} else if(StandardAlgorithmNameMapping.MessageDigest.ifValid(algorithm)) {
			digest = MessageDigest.getInstance(algorithm);
		} else {
			throw new NoSuchAlgorithmException();
		}
	}
	
	public void digest(String[] messages) throws Exception {
		if(digest == null) {
			initMessageDigest();
		}
		
		for(String message:messages) {
			System.out.println("Message:" + message + "'s byte[] is " + message.getBytes());
			digest.update(message.getBytes());
		}
		
		byte[] result = digest.digest();
		System.out.println("Byte[] result: " + result);
		System.out.println("String result: " + DatatypeConverter.printBase64Binary(result));
		System.out.println("Array result: " + Arrays.toString(result));
	}
	
	public static void main(String[] args) throws Exception {
		String[] messages = {"message-1", "message-2", "message-3", "message-4", "message-5", "message-6"};

		new MessageDigestDemo().digest(messages);
	}

}
