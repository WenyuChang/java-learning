package wenyu.learning.AESDemo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @author wenychan
 *
 */
public class AESUtils 
{
	public static final String ENCRYPT_FILE = "encrypt_file";
	public static final String ENCRYPT_TEXT = "encrypt_text";
	public static final String DECRYPT_FILE = "decrypt_file";
	public static final String DECRYPT_TEXT = "decrypt_text";
	
	private static final String ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
	private static final int CACHE_SIZE = 1024;
	
	/**
     * Generate Key
     * 
     * @param seed
     * @return
     * @throws Exception
     */
    public static String getSecretKey(String seed) throws Exception 
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom;
        if (seed != null && !"".equals(seed)) 
        {
            secureRandom = new SecureRandom(seed.getBytes());
        } 
        else 
        {
            secureRandom = new SecureRandom();
        }
        keyGenerator.init(KEY_SIZE, secureRandom); 
        SecretKey secretKey = keyGenerator.generateKey();

        return new BASE64Encoder().encode(secretKey.getEncoded());
    }
	
    /**
     * start to encrypt or decrypt
     * 
     * @param key
     * @param opType
     * @param opObject
     * @return
     * @throws Exception
     */
	public static String startJob(String key, String opType, String obj) throws Exception
	{
		if(opType == AESUtils.ENCRYPT_FILE)
		{
			String sourceFilePath = obj;
			String destFilePath = sourceFilePath + "_encrypted";
	        
	        AESUtils.encryptFile(key, sourceFilePath, destFilePath);
	        return destFilePath;
		}
		else if(opType == AESUtils.ENCRYPT_TEXT)
		{
			String plainText = obj;
			String encText = AESUtils.encryptText(plainText, key);
			return encText;
		}
		else if(opType == AESUtils.DECRYPT_FILE)
		{
			String sourceFilePath = obj;
			String destFilePath = "";
			String[] pathSplits = sourceFilePath.split("\\.");
			pathSplits[pathSplits.length-2] += "_decrypted";
			for (int i=0;i<pathSplits.length-1;i++)
			{
				destFilePath += pathSplits[i];
			}
			destFilePath += "." + pathSplits[pathSplits.length-1];
			
			AESUtils.decryptFile(key, sourceFilePath, destFilePath);
			return "SUCCESS";
		}
		else if(opType == AESUtils.DECRYPT_TEXT)
		{
			String text = obj;
			AESUtils.decryptText(text, key);
			return "SUCCESS";
		}
		return null;
	}
    
    
	/**
     * File Encrypt
     * 
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void encryptFile(String key, String sourceFilePath, String destFilePath) throws Exception 
    {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath); 
        
        if (sourceFile.exists() && sourceFile.isFile()) 
        {
            if (!destFile.getParentFile().exists()) 
            {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destFile);
            BASE64Decoder decoder = new BASE64Decoder();
            Key k = new SecretKeySpec(decoder.decodeBuffer(key),ALGORITHM);
            byte[] raw = k.getEncoded(); 
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM); 
            Cipher cipher = Cipher.getInstance(ALGORITHM); 
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            CipherInputStream cin = new CipherInputStream(in, cipher);
            
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = cin.read(cache)) != -1) 
            {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            cin.close();
            in.close();
        }
        else
        {
        	//no file found
        }
    }
    
    /**
     * <p>
     * File Decrypt
     * </p>
     * 
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void decryptFile(String key, String sourceFilePath, String destFilePath) throws Exception 
    {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath); 
        if (sourceFile.exists() && sourceFile.isFile()) 
        {
            if (!destFile.getParentFile().exists()) 
            {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            FileInputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(destFile);
            BASE64Decoder decoder = new BASE64Decoder();
            Key k = new SecretKeySpec(decoder.decodeBuffer(key),ALGORITHM);
            byte[] raw = k.getEncoded(); 
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM); 
            Cipher cipher = Cipher.getInstance(ALGORITHM); 
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            CipherOutputStream cout = new CipherOutputStream(out, cipher);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) 
            {
                cout.write(cache, 0, nRead);
                cout.flush();
            }
            cout.close();
            out.close();
            in.close();
        }
    }
    
    /**
     * <p>
     * Text Encrypt
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptText(String content, String key) throws Exception 
    {
    	byte[] data = new BASE64Decoder().decodeBuffer(content);
    	Key k = new SecretKeySpec(new BASE64Decoder().decodeBuffer(key), ALGORITHM);
        byte[] raw = k.getEncoded(); 
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM); 
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        System.out.println("Encrypt Text: " + new BASE64Encoder().encode(cipher.doFinal(data)));
        
        return new BASE64Encoder().encode(cipher.doFinal(data));
    }
    
    /**
     * <p>
     * Text Decrypt
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static void decryptText(String content, String key) throws Exception 
    {
    	byte[] data = new BASE64Decoder().decodeBuffer(content);
    	Key k = new SecretKeySpec(new BASE64Decoder().decodeBuffer(key), ALGORITHM);
        byte[] raw = k.getEncoded(); 
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM); 
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        System.out.println("Decrypt Text: " + new BASE64Encoder().encode(cipher.doFinal(data)));
    }
}
