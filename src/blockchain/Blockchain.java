package blockchain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class Blockchain {
    
    private static final String CHAIN_OBJFILE = "master/ledgerbytes.dat";

    public static void persist(LinkedList<Block> chain) {
        
        try (FileOutputStream fos = new FileOutputStream(CHAIN_OBJFILE);
                ObjectOutputStream out = new ObjectOutputStream(fos)) {           
            out.writeObject(chain);
            
        } catch (Exception e) {}
    }

    public static void distribute( String temp ){
        try {
            Files.write(Paths.get("ledger.txt"), temp.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {}
    }    
    
    public static LinkedList<Block> get() {
        
        try (FileInputStream fis = new FileInputStream(CHAIN_OBJFILE);
                ObjectInputStream in = new ObjectInputStream(fis)) {            
            return (LinkedList<Block>) in.readObject();        
            
        } catch (Exception e) {return null;}
    }
}

/* A) Lease Agreement using Smart Contracts 
   B) Automated Payments using Smart Contracts. 

“Private DID” tokens, residence and digital signature off chain  
“Public DID” real estate data, pictures and biography

1. The token (monetary value) and the property data are received by the Smart Contract
2. Value against token check
3. The buyer and seller digitally sign smart contract
4. Token value is to the seller and  property ownership to buyer
5. New blocks chained for both
*/       
    

