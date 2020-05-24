package cryptography;

import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*    
    Encryption: private key to encypt 
    Decryption: public key to decypt
 */

public class KeyRetriever {
            
    public static PrivateKey getPrivateKey (String filename) throws Exception{        
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        
        //Intantiated to read the bytes array of private key from file
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(Config.CRYPTO_ALGO);
        
        return factory.generatePrivate(spec);
    }   
        
    public static PublicKey getPublicKey (String filename) throws Exception{        
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        
        //Intantiated to read the bytes array of public key from file
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(Config.CRYPTO_ALGO);
        
        return factory.generatePublic(spec);
    }               
}
