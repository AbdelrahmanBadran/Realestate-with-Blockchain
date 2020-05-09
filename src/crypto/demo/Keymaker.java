package crypto.demo;

import java.io.*;
import java.nio.file.*;
import java.security.*;

public class Keymaker {
    
    KeyPairGenerator keygen;
    KeyPair pair;
    
    //Generate the keys and store in files perminantly (to change it, I need to rerun: carefull)
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Keymaker maker = new Keymaker();
        
        maker.keygen = KeyPairGenerator.getInstance(AppConfig.ALGO);        
        maker.keygen.initialize(2084);
        maker.pair = maker.keygen.generateKeyPair();
        
        PublicKey pubkey = maker.pair.getPublic();
        PrivateKey prvkey = maker.pair.getPrivate();
        
        //Write these keys to the file
        Keymaker.persist(AppConfig.PUBLIC_FILE, pubkey.getEncoded());
        Keymaker.persist(AppConfig.PRIVATE_FILE, prvkey.getEncoded());
    }
        
    private static void persist(String path, byte[] key){        
        File file = new File(path);
        file.getParentFile().mkdirs();
        
        try {
            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
            System.out.println("Done!");
            
        } catch (IOException ex) {
            System.out.println("Error: Unable to write to file");            
        }
    }
}
