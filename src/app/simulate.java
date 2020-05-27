package app;

import cryptography.Config;
import cryptography.Crypto;
import cryptography.KeyGenerator;
import signature.DigitalSignature;

public class simulate {
    public final static String SELLER = "Seller";  
    public final static String BUYER = "Buyer";   
    
    public static Crypto crypto = new Crypto();
    public static DigitalSignature digSign = new DigitalSignature(); 
    
    public static void main(String[] args) {  
        
//        KeyGenerator.generateAsymetricKeys(Config.CRYPTO_FILE, SELLER, Config.CRYPTO_ALGO);        
//        KeyGenerator.generateAsymetricKeys(Config.DS_FILE , SELLER, Config.DS_ALGO);                                        
        
        blockchain.Blockchain.chainBlock();   
        
//        String decrypted = crypto.decrypt(encrypted, KeyRetriever.getPublicKey(Config.CRYPTO_FILE + SELLER + Config.PUBLIC_FILE, Config.CRYPTO_ALGO));        
//        boolean isVerified = digSign.verify(SELLER, TRANSACTION, digitalSignature);     
//        System.out.println("\nVerified?: " + isVerified);
    }     
}