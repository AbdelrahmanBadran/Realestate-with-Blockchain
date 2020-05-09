package crypto.demo;

import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*
    Asymetric-key crypto AKA Public-key crypto
    Requires public key and private key
    Encryption: use private key to encypt the data
    Decryption: use public key to decypt the data
 */

public class AsymetricKeyCryptoDemo {
    
    //1. Generate keys: public key & private key
    //Then -> This
    private static String original = "Public key is tested!";
    
    //Main method
    public static void main(String[] args) throws Exception {
        System.out.println("Original: " + original + "\n");
        MyCrypto crypto = new MyCrypto();
                
        //String encrypted ="BcGKpplSRoLFul7CbT9aTUyKycYOKrzCcm4vU/kgFFlnChQHnfhhzTg0ILxWg3eCW0aPn2aChzokqXuv167if5gk0zVIdiAVBIA0lvY5qXKKA6jtKrfTb4TfGIzXH6V5adgGQ5PhZjQoNe4HiU1qQdqipymKhJfu6BEjJS9smtUr+26xaKmR5AfWYlSvQIoXEDE3+P2+1CPxfLo065bbDTImzFwUVZgbC4ym7pA0gSyTp5ABX04YJNAS/DGhKl8oY52B7C5xCdU/gXuT8cX1i+YvAfVPX6wRujRSuL26wgYAIF+c9yvEvyo/jHMH6ReRkmc8LQz/8QIoyUWiKBhFYywG2Ji1";
        String encrypted = crypto.encrypt(original, getPrvKey(AppConfig.PRIVATE_FILE));          
        System.out.println("Encrypted: " + encrypted + "\n");
        
        String decrypted = crypto.decrypt(encrypted, getPubKey(AppConfig.PUBLIC_FILE));
        System.out.println("Decrypted: " + decrypted + "\n");                
    }
    
    
    //Access the keypair file for public keys
    private static PublicKey getPubKey (String filename) throws Exception{
        
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        
        //Intantiated to read the bytes array of public key from file
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(AppConfig.ALGO);
        
        return factory.generatePublic(spec);
    }
    
    //Access the keypair file for private keys
    private static PrivateKey getPrvKey (String filename) throws Exception{
        
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        
        //Intantiated to read the bytes array of private key from file
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(AppConfig.ALGO);
        
        return factory.generatePrivate(spec);
    }
}
