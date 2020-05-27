package app;

import cryptography.Config;
import cryptography.Crypto;
import java.util.List;
import java.util.ArrayList;
import cryptography.KeyGenerator;
import cryptography.KeyRetriever;
import signature.DigitalSignature;


public class simulate {
    public final static String SELLER = "Seller";  
    public final static String BUYER = "Buyer";   
    
    public static Crypto crypto = new Crypto();
    public static DigitalSignature digSign = new DigitalSignature(); 
    
    public static void main(String[] args) { 
        
        //RUN THIS KEYGENERATOR ONLY ONCE
        //Generate & Store Keys for Crypto & DSignature (run it once for each user only)
        KeyGenerator.generateAsymetricKeys(Config.CRYPTO_FILE, SELLER, Config.CRYPTO_ALGO);         
        KeyGenerator.generateAsymetricKeys(Config.DS_FILE , SELLER, Config.DS_ALGO);                                        

        
        //BlockChain from trnxpool to be on ledger
        blockchain.Blockchain.chainBlock();   
        
        
         // ### Decryption ###
        //Decrypt Encrypted Seller Transaction Property ID
        String decryptedPropertyID = 
                crypto.decrypt(blockchain.Blockchain.get().getFirst().getTransactions().get(0).getValue0() , 
                        KeyRetriever.getPublicKey(Config.CRYPTO_FILE + SELLER + Config.PUBLIC_FILE, Config.CRYPTO_ALGO));        
        System.out.println(decryptedPropertyID);        
            
        
        // ### Verification ###
        //Get Transaction hash codes except last one (Signature)
        List<String> transactionList = new ArrayList();
        for (int i = 0; i < 6 ; i++) {
            transactionList.add((String) blockchain.Blockchain.get().getFirst().getTransactions().get(0).getValue(i));
        }                
        
        //Verify (SELLER | Transaction data from for loop above as a list | Digital Signature on dat)
        boolean isVerified = digSign.verify(SELLER , String.join("|", transactionList) , 
                        blockchain.Blockchain.get().getFirst().getTransactions().get(0).getValue6());               
        System.out.println("\nVerified?: " + isVerified);
    }     
}