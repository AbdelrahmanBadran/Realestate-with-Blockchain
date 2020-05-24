package cryptography;

import java.io.*;
import java.nio.file.*;
import java.security.*;

public class KeyGenerator {
    
    KeyPairGenerator keygen;
    KeyPair pair;

    //Generate the keys and store in files perminantly (to change it, I need to rerun: carefull)
    public static KeyPair generateAsymetricKeys () throws NoSuchAlgorithmException{
        KeyGenerator maker = new KeyGenerator();
        
        maker.keygen = KeyPairGenerator.getInstance(Config.CRYPTO_ALGO);        
        maker.keygen.initialize(2084);
        maker.pair = maker.keygen.generateKeyPair();
        
        PublicKey pubkey = maker.pair.getPublic();
        PrivateKey prvkey = maker.pair.getPrivate();
        
        //##################### CAN ADD USERNAME FOR FILE NAME ####################################
        
        //Write these keys to the file
        KeyGenerator.persist(Config.PUBLIC_FILE, pubkey.getEncoded());
        KeyGenerator.persist(Config.PRIVATE_FILE, prvkey.getEncoded());
        
        return maker.pair;
    }
    
    private static void persist(String path, byte[] key){        
        File file = new File(path);
        file.getParentFile().mkdirs();
        
        try {
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);            
            
        } catch (IOException ex) {System.out.println("err: writing keys to file");}
    }
}
