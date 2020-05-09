package digitalsignature.demo;

import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.*;

/*
Integrity: the message hasn't been altered in transit
Authenticity: the auther of the message is really who they claim tobe
Non-repudiation: the author of the message can't later deny that they were the source
*/


public class DemoWithBasicDS {
    
    KeyPairGenerator keygen;
    KeyPair keypair;        
    Cipher cipher;
    
    public DemoWithBasicDS(){
        //Instantiate neccessary objects
        try{
            keygen = KeyPairGenerator.getInstance("RSA"); //RSA, DSA, etc
            keypair = keygen.generateKeyPair();            
            cipher = Cipher.getInstance("RSA");
        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException e){
        }
    }
    
//Methods: hash, encrypt, verify
    String hash(String data) throws Exception{
        String dataHash = null;
        
        //SHA-256, MD5, SHA-384, SHA-512
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] newhash = md.digest(data.getBytes());
        
        //convert the new hash to string
        dataHash = Base64.getEncoder().encodeToString(newhash);
        return(dataHash);
    }
    
    //Encrypting the hash output of the given Data
    String encrypt(String dataHash) throws Exception{
        cipher.init(Cipher.ENCRYPT_MODE, keypair.getPrivate()); //encrypt with private key
        byte[] digitalSignature = cipher.doFinal(Base64.getDecoder().decode(dataHash));
        
        return Base64.getEncoder().encodeToString(digitalSignature);
    }
    
    //Verifiying the Digital Signature with data
    boolean verify(String data, String digitalSignature) throws Exception{
        //Decrypt the DS with public key
        cipher.init(Cipher.DECRYPT_MODE, keypair.getPublic());
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(digitalSignature));
        
        //Original hash out of data
        byte[] newHash = Base64.getDecoder().decode(this.hash(data));
        
        //compare between newHash and decryptedHash
        return Arrays.equals(newHash, decrypted);
    }
    
    public static void main(String[] args) throws Exception {
        DemoWithBasicDS demo = new DemoWithBasicDS();
        
        String data = "#staysafe";
        //Hashing
        System.out.println("Date: " + data);
        
        try{
            //Hashing
            String hash = demo.hash(data);
            System.out.println("Data (hash -SHA-256): " + hash);
            
            //Encrypting
            String digitalSignature = demo.encrypt(hash);
            System.out.println("Encrypted Hash (DS): " + digitalSignature);
            
            Thread.sleep(2000);
            
            //Verifying     
            boolean isMatch = demo.verify(data, digitalSignature);
            System.out.println("\nMatch? " + isMatch);
            
            System.out.println("Done!");
        }
        catch(Exception e){}
    }
}