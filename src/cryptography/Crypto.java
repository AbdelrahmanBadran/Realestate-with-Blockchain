package cryptography;

import java.security.*;
import java.util.Base64;
import javax.crypto.*;

public class Crypto {
        
    private Cipher cipher;

    public Crypto() {
        try {
            cipher = Cipher.getInstance(Config.CRYPTO_ALGO);
        
        } catch (Exception ex) {System.out.println("err: Crypto Algorithm");}
    }
        
    public String encrypt(String rawText, PrivateKey key){
        String cipherText = "";
        
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] cipherBytes = cipher.doFinal(rawText.getBytes());
            
            //Convert the cipherBytes into String object
            cipherText = Base64.getEncoder().encodeToString(cipherBytes);
            
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {System.out.println("err: Encyption");} 
        
        return cipherText;
    }
        
    public String decrypt(String cipherText, PublicKey key){
        String originalText = "";
        try{
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            //convert the string cipherText object into bytes[]
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            
            originalText = new String(cipher.doFinal(cipherBytes));
        
        }catch(InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex){System.out.println("err: Decryption");}
        
        return originalText;
    }
}
