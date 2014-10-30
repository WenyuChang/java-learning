package wenyu.learning.AESDemo;
/**
 * 
 */

/**
 * @author wenychan
 *
 */
public class AESMain 
{
	public static String key;
	
	/**
	 * File encrypt and decrypt
	 * @throws Exception 
	 */
	public static void fileOperate() throws Exception {
		if(key == null) {
			key = AESUtils.getSecretKey(null);
		}
		
    	String file_path = "src/main/java/self/demo/javaLearning/AESDemo/file_for_encryption";
    	String encfile_path = AESUtils.startJob(key, AESUtils.ENCRYPT_FILE, file_path);
    	//AESUtils.startJob(key, AESUtils.ENCRYPT_FILE, encfile_path);
	}
	
	/**
	 * Text encrypt and decrypt
	 * @throws Exception 
	 */
	public static void textOperate() throws Exception {
		if(key == null) {
			key = AESUtils.getSecretKey(null);
		}
		
        String plainText = "message1,message2,message3";
		String encryptText = AESUtils.startJob(key, AESUtils.ENCRYPT_TEXT, plainText);
		AESUtils.startJob(key, AESUtils.ENCRYPT_TEXT, encryptText);
	}
	
    public static void demoEntry() throws Exception
    {
    	//textOperate();
    	fileOperate();
    }
    
    public static void main(String[] args) throws Exception 
	{
    	demoEntry();
	}
}
