package cryptography;

import java.io.*;
import java.nio.file.*;
import java.security.*;

public class KeyGenerator {
    
    KeyPairGenerator keygen;
    KeyPair pair;
    
    public static KeyPair generateAsymetricKeys (String parentFile, String userID, String algorithm) {
        KeyGenerator maker = new KeyGenerator();
        
        try {        
            maker.keygen = KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {ex.printStackTrace();}
        
        if (algorithm.equals(Config.CRYPTO_ALGO)) maker.keygen.initialize(2084);
        
        maker.pair = maker.keygen.generateKeyPair();
        
        PublicKey pubkey = maker.pair.getPublic();
        PrivateKey prvkey = maker.pair.getPrivate();               
                
        KeyGenerator.persist(parentFile + userID + Config.PRIVATE_FILE, prvkey.getEncoded());
        KeyGenerator.persist(parentFile + userID + Config.PUBLIC_FILE, pubkey.getEncoded());        
        
        return maker.pair;
    }
    
    public static void persist(String path, byte[] key){        
        File file = new File(path);
        file.getParentFile().mkdirs();
        
        try {
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);                        
        } 
        catch (IOException ex) {System.out.println("err: writing keys to file");}
    }
}
