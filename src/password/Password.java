package password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

	public Password() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
    {
        String passwordToHash = "password";
        
        
        //
//        String loginPwd = "password"; //valid
        String loginPwd = "password1234"; //invalid
        if ( md5( passwordToHash ).equals( md5( loginPwd ))){
            System.out.println( "valid" );
        } 
        else{
            System.out.println( "invalid" );
        }
        
    }

	private static String md5( String passwordToHash ) {
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update( getSalt() );
            //Get the hash's bytes 
            byte[] bytes = md.digest( passwordToHash.getBytes() );
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
            return null;
        }
	}
	
	private static byte[] getSalt() throws NoSuchAlgorithmException
	{
//	    //Always use a SecureRandom generator
//	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//	    //Create array for salt
//	    byte[] salt = new byte[16];
//	    //Get a random salt
//	    sr.nextBytes(salt);
//	    //return salt
//	    return salt;
		
            return "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*".getBytes();
	}

}
