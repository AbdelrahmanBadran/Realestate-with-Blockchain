package signature;

import cryptography.Config;
import java.security.*;
import java.util.Base64;

public class DigitalSignature {
    Signature signature;
    SecureRandom rand = new SecureRandom();
    
    KeyPairGenerator keygen;
    KeyPair keypair;
            
    public DigitalSignature(){        
        try{
            keygen = KeyPairGenerator.getInstance(Config.DS_ALGO);            
            
            //############################# USE USER GENERATED KEYS ##########################

            keypair = keygen.generateKeyPair();        
            signature = Signature.getInstance("SHA256WithDSA"); //Combines Hashing and Encyption         
            
        }catch(NoSuchAlgorithmException e){}
    }    
    
    //    PASS IN USER PRIVATE KEY
    public String sign(String data) throws Exception{
        String digitalSignature = null;

        signature.initSign(keypair.getPrivate(), rand);
        
        signature.update(data.getBytes());
        
        //Generate DS
        byte[] digital_sign = signature.sign();
        digitalSignature = Base64.getEncoder().encodeToString(digital_sign);
        
        return digitalSignature;
    }
    
    //    PASS IN OTHER'S PUBLIC KEY
    public boolean verify(String data, String digitalSignature) throws Exception{        
        signature.initVerify(keypair.getPublic());
        
        signature.update(data.getBytes());              
        
        return signature.verify(Base64.getDecoder().decode(digitalSignature));
    }  
}
