package wenyu.learning.JCA;

public enum StandardAlgorithmNameMapping {
	SecureRandom(new String[] {"SHA1PRNG"}),
	MessageDigest(new String[] {"MD2", "MD5", "SHA-1", "SHA-256", "SHA384", "SHA512"}),
	KeyPairGenerator(new String[] {"DiffieHellman", "DSA", "RSA", "EC"}),
	KeyGenerator(new String[] {"AES", "ARCFOUR", "Blowfish", "DES", "DESede", "HmacMD5"
			, "HmacSHA1", "HmacSHA256", "HmacSHA384", "HmacSHA512", "RC2"}),
	Signature(new String[] {"NONEwithRSA", "MD2withRSA", "MD5withRSA", "SHA1withRSA"
			, "SHA256withRSA", "SHA384withRSA" , "SHA512withRSA", "NONEwithDSA", "SHA1withDSA"
			, "NONEwithECDSA", "SHA1withECDSA", "SHA256withECDSA", "SHA384withECDSA", "SHA512withECDSA"}),
	KeyFactory(new String[] {"DiffieHellman", "DSA", "RSA", "EC"});
	
	private final String[] algs;
	private StandardAlgorithmNameMapping(String[] algs) {
		this.algs = algs;
	}
	
	public String[] getValidAlgorithms() {
		return algs;
	}
	
	public boolean ifValid(String algorithm) {
		for(String alg:algs) {
			if(algorithm == alg) {
				return true;
			}
		}
		return false;
	}
}
