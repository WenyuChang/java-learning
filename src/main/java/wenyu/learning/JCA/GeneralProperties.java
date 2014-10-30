package wenyu.learning.JCA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class GeneralProperties {
	public static SecureRandom random;
	
	public static AlgorithmParameterSpec spec;
	
	// public-key
	public static PrivateKey privKey;
	public static PublicKey pubKey;
	public static RSAPublicKeySpec RSAPubSpec;
	public static RSAPrivateKeySpec RSAPrivSpec;

	// Symmetric-key
	public static SecretKey secKey;
	public static SecretKeySpec secretKeySpec;
}
