package digitalsignature.demo;

import java.security.*;
import java.util.Base64;

public class DemoWithSignatureApi {
    Signature signature;
    SecureRandom rand = new SecureRandom();
    
    KeyPairGenerator keygen;
    KeyPair keypair;
            
    public DemoWithSignatureApi(){        
        try{
            keygen = KeyPairGenerator.getInstance("DSA");
            keypair = keygen.generateKeyPair();         
            signature = Signature.getInstance("SHA256WithDSA"); //Combines Hashing and Encyption
        }
        catch(NoSuchAlgorithmException e){
        }
    }
    
    //Methods: Sign & Verify    
    String sign(String data) throws Exception{
        String digitalSignature = null;
        
        signature.initSign(keypair.getPrivate(), rand);
        signature.update(data.getBytes());
        
        //Generate DS
        byte[] digital_sign = signature.sign();
        digitalSignature = Base64.getEncoder().encodeToString(digital_sign);
        
        return digitalSignature;
    }
    
    boolean verify(String data, String digitalSignature) throws Exception{        
        signature.initVerify(keypair.getPublic());
        signature.update(data.getBytes());              
        
        return signature.verify(Base64.getDecoder().decode(digitalSignature));
    }
    
    public static void main(String[] args) {
        DemoWithSignatureApi demo = new DemoWithSignatureApi();
        String data = "#stayathome";
        System.out.println("Data: " + data);
        try{            
            String digitalSignature = demo.sign(data);
            System.out.println("Digital Signature:" + digitalSignature);
            
            Thread.sleep(2000);            
//            data = "#stayoutside";

            boolean isMatch = demo.verify(data, digitalSignature);
            System.out.println("\nMatch?: " + isMatch);
        }
        
        catch(Exception e){}        
    }
}
