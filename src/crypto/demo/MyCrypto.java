package crypto.demo;

import java.security.*;
import java.util.Base64;
import javax.crypto.*;

public class MyCrypto {
    
    //cipher object    
    private Cipher cipher;

    public MyCrypto() {
        try {
            cipher = Cipher.getInstance(AppConfig.ALGO);
        } catch (Exception ex) {
            System.out.println("Error with Algorithm");
        }
    }
        
    public String encrypt(String rawInput, PrivateKey key){
        String cipherText = "";
        
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherBytes = cipher.doFinal(rawInput.getBytes());
            
            //Convert the cipherBytes into String object
            cipherText = Base64.getEncoder().encodeToString(cipherBytes);
            
        } catch (Exception ex) {
            System.out.println("Encyption failed");
        } 
        
        return cipherText;
    }
    
    
    public String decrypt(String cipherText, PublicKey key){
        String originalText = "";
        try{
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            //convert the string cipherText object into bytes[]
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            originalText = new String(cipher.doFinal(cipherBytes));
        }
        catch(Exception ex){
            System.out.println("Decription failed");
        }
        
        return originalText;
    }
}
