package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchProviderException;

public class Hashing {

    public Hashing() { }
        
    public static String hash(String in, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance( algorithm );
            md.update( getSalt() );

            byte[] bytes = md.digest( in.getBytes("UTF-8") );
            StringBuilder sb = new StringBuilder();

            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) 
        {
            return null;
        }
    }

    public static byte[] getSalt() {
        
        SecureRandom secureRandomGenerator = null;
        try {
            secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {}
        
        byte[] randomBytes = new byte[128];
        secureRandomGenerator.nextBytes(randomBytes);
        
        return randomBytes;
    }	
}
