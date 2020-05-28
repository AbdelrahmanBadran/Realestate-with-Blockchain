package cryptography;

/**
 * Algorithms
 * Location folders for storing public/private keys
 */

public class Config {
 
    //Cryptography Algorithm & Parent File
    public static final String CRYPTO_ALGO = "RSA";    
    public static final String CRYPTO_FILE = "Crypto/"; 
        
    //Digital Signature Algorithm and Public Key Generation Algorithm
    public static final String DS_ALGO_COMBO = "SHA256WithDSA";
    public static final String DS_ALGO = "DSA";    
    public static final String DS_FILE = "DigSign/";
    
    //Sub Files for Parent Files
    public static final String PUBLIC_FILE = "/PublicKey";    
    public static final String PRIVATE_FILE = "/PrivateKey"; 
}
