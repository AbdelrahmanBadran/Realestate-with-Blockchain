package cryptography;

public class simulate {
    
    public static void main(String[] args) throws Exception {
        KeyGenerator.generateAsymetricKeys();
        Crypto crypto = new Crypto();
        
        String original = "Test Public Key Cryptography!";    
        System.out.println("Original: " + original + "\n");        
        
        String encrypted = crypto.encrypt(original, KeyRetriever.getPrivateKey(Config.PRIVATE_FILE)); 
        System.out.println("Encrypted: " + encrypted + "\n");   
        
        String decrypted = crypto.decrypt(encrypted, KeyRetriever.getPublicKey(Config.PUBLIC_FILE));
        System.out.println("Decrypted: " + decrypted + "\n");  
    }
}
