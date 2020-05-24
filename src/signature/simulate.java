package signature;

public class simulate {
    
    public static void main(String[] args) {        
        DigitalSignature digitalSign = new DigitalSignature();
        
        String data = "Digitally signed data";
        System.out.println("Data: " + data);
        
        try{            
            String digitalSignature = digitalSign.sign(data);            
            
            System.out.println("\nDS: " + digitalSignature);   
            
            boolean isVerified = digitalSign.verify(data, digitalSignature);
            System.out.println("\nVerified?: " + isVerified);
        
        }catch(Exception e){}        
    }
}
