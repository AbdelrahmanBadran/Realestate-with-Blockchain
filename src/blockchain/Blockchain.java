package blockchain;

import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import transaction.Transaction;
import transaction.TrnxPoolAdapter;

public class Blockchain {
    
    private static final String CHAIN_OBJFILE = "master/ledgerbytes.dat";
    
    static LinkedList<Block> bchain = new LinkedList();
    
    public static void chainBlock() {                 
        List<List<String>> trnx_hashes = TrnxPoolAdapter.getTransactionsHashes();
        if(trnx_hashes.isEmpty()){return;}                
        long trnx_count = trnx_hashes.size();        
        
        Block block;
        boolean isGenesis = Blockchain.isChainEmpty();
        
        if (isGenesis){ 
            block = new Block(0, trnx_count, trnx_hashes, "0"); // Genesis block
        } 
        else{  
            bchain = Blockchain.get(); 
            long block_id = bchain.size();
            block = new Block(block_id, trnx_count, trnx_hashes, bchain.getLast().getCurrentHash()); 
        }
        
        bchain.add(block);
        
        //Save to ledgerobj file
        persist(bchain);         
        
        //clear the trnxpool.txt
        Transaction.empty();

        //distribute the blocks to ledger.txt
        distribute(bchain);        
    }   
        
    public static void persist(LinkedList<Block> chain) {
        
        try (FileOutputStream fos = new FileOutputStream(CHAIN_OBJFILE);
                ObjectOutputStream out = new ObjectOutputStream(fos)) {           
            out.writeObject(chain);
            
        } catch (Exception e) {}
    }  
        
    public static void distribute(LinkedList<Block> bchain){
        String temp = new GsonBuilder().setPrettyPrinting().create().toJson(bchain); 
        
        try {
            Files.write(Paths.get("master/ledger.txt"), temp.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {}        
        
        System.out.println( temp );
    }
    
    public static LinkedList<Block> get() {
        
        try (FileInputStream fis = new FileInputStream(CHAIN_OBJFILE);
                ObjectInputStream in = new ObjectInputStream(fis)) {            
            return (LinkedList<Block>) in.readObject();        
            
        } catch (Exception e) {return null;}
    }
    
    public static boolean isChainEmpty() {
        
        try (FileInputStream fis = new FileInputStream(CHAIN_OBJFILE);
                ObjectInputStream in = new ObjectInputStream(fis)) {            
            return in.readObject() == null;        
            
        } catch (Exception e) {return true;}
    }    
}