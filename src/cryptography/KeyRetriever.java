package cryptography;

import java.io.IOException;
import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class KeyRetriever {
            
    public static PrivateKey getPrivateKey (String filename, String algorithm) {        
        
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
            
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(algorithm);
            
            return factory.generatePrivate(spec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException ex) {}
        return null;
    }   
        
    public static PublicKey getPublicKey (String filename, String algorithm) {        
       
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
            
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(algorithm);
            
            return factory.generatePublic(spec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException ex) {}
        return null;
    }               
}
