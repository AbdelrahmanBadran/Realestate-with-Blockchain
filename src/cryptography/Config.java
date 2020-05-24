package cryptography;

/**
 * Algorithm
 * Location folders for storing public/private keys
 */
public class Config {
 
    //Cryptography Algorithm
    public static final String CRYPTO_ALGO = "RSA";
    
    //Digital Signature Algorithm
    public static final String DS_ALGO = "DSA";
    
    //Location folders for storing public/private keys
    public static final String PUBLIC_FILE = "KeyPair/PublicKey";    
    public static final String PRIVATE_FILE = "KeyPair/PrivateKey";             
}
