package crypto.demo;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SymetricKeyCryptoDemo {
    
    private static String algorithm = "AES";    
    private static byte[] secret = new byte[]{'a', 'p', 'u'};
    
    //The main thing is the key (secret) (has to be safe)
    private static Key genKey(){        
        Key key = new SecretKeySpec(Arrays.copyOf(secret, 16), algorithm); //Has to use 16 bits
        return key;
    }
    
    public static String encrypt(String data) throws Exception{
        //Generate the key
        Key mKey = genKey();
        
        //Cipher Algorithm
        Cipher cipher = Cipher.getInstance(algorithm);
        
        //Initialize
        cipher.init(Cipher.ENCRYPT_MODE, mKey);
        
        //Encryption : in byte array
        //Update (Large data: chunk by chunk)/doFinal (Small data: all at once)
        byte[] cipherText = cipher.doFinal(data.getBytes());
        
        //To string just for demonstration
        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    
    public static String decrypt(String cypherText) throws Exception{
        //Generate Key: Same key every time since secret is same
        Key myKey = genKey();
        
        //Cipher Algorithm
        Cipher cipher = Cipher.getInstance(algorithm);
        
        //Initialize
        cipher.init(Cipher.DECRYPT_MODE, myKey);
        
        //Convert String to byte[] for Decryption
        byte[] ciphTxt = Base64.getDecoder().decode(cypherText);
        
        //Decryption (has to be bytes)
        byte[] decrypted = cipher.doFinal(ciphTxt);

        //To string just for demonstration        
        return new String (decrypted);
    }
    
    
    public static void main(String[] args) throws Exception {
        //Original Data     
        String data = "Hello World";    
        System.out.println("Original Data: " + data);
        
        //Encryption
        String encrypted = encrypt(data);        
        System.out.println("ENCRYPTED DATA:" + encrypted);        

        //Decryption
        String decrypted = decrypt(encrypted);        
        System.out.println("DECRYPTED DATA:" + decrypted);
        
        //System.out.println("HASHCODE:" + encrypted.hashCode());
    }
}
