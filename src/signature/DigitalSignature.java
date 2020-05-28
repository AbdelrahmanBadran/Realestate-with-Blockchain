package signature;

import cryptography.*;
import java.security.*;
import java.util.Base64;

public class DigitalSignature {
    
    Signature signature;            
    SecureRandom rand = new SecureRandom();
            
    public DigitalSignature(){                                                       
        try {            
            signature = Signature.getInstance(Config.DS_ALGO_COMBO);   
        } 
        catch (NoSuchAlgorithmException ex) {}                    
    }    
        
    public String sign(String userID, String data) {        

        try {
            signature.initSign(KeyRetriever.getPrivateKey(Config.DS_FILE + userID, Config.DS_ALGO), rand);
            signature.update(data.getBytes());
            
            return Base64.getEncoder().encodeToString(signature.sign());
            
        } catch (InvalidKeyException | SignatureException ex) {}
        return null;        
    }    
    
    public boolean verify(String userID, String data, String digitalSignature) {        
        
        try {
            signature.initVerify(KeyRetriever.getPublicKey(Config.DS_FILE + userID, Config.DS_ALGO));
            signature.update(data.getBytes());
            
            return signature.verify(Base64.getDecoder().decode(digitalSignature));
            
        } catch (SignatureException | InvalidKeyException ex) {}
        return false;
    }      
}
