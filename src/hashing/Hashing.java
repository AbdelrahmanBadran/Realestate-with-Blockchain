package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.io.UnsupportedEncodingException;

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

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }	
}
