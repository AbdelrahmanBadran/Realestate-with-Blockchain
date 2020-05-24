package app;

import blockchain.Block;
import blockchain.Blockchain;
import com.google.gson.GsonBuilder;
import java.util.LinkedList;
import java.util.List;
import transaction.TrnxPoolAdapter;
import transaction.Transaction;

public class simulate {
    
    static LinkedList<Block> bchain = new LinkedList();
    
    public static void main(String[] args) {
        
        if(Blockchain.get() == null){ firstBlock(); }
        else{ nextBlock(); }
    }

    public static void firstBlock() {                 
        List<List<String>> trnx_hashes = TrnxPoolAdapter.getTransactionsHashes();
        if(trnx_hashes.isEmpty()){return;};

        Block b1 = new Block(trnx_hashes, "0"); //genesis block
        bchain.add(b1);
        
        //Save to ledgerobj file
        Blockchain.persist(bchain);         
        //clear the trnxpool.txt
        Transaction.empty();
        //distribute/display the linkedlist elements/blocks
        out(bchain);        
    }
    
    public static void nextBlock(){
        List<List<String>> trnx_hashes = TrnxPoolAdapter.getTransactionsHashes();        
        if(trnx_hashes.isEmpty()){return;};
        
        bchain = Blockchain.get();
        
        Block block = new Block(trnx_hashes, bchain.getLast().getCurrentHash() );
        bchain.add(block);
                
        Blockchain.persist(bchain);
        Transaction.empty();        
        out(bchain);
    }
    
    public static void out(LinkedList<Block> bchain){
        String temp = new GsonBuilder().setPrettyPrinting().create().toJson(bchain);        
        Blockchain.distribute(temp);
        System.out.println( temp );
    }
}
        
//        List<Transaction> trnxPool = TrnxPoolAdapter.getTransactions();
//        System.out.println("--- Transactions ---");
//        trnxPool.stream().forEach( System.out::println );   

//        System.out.println("--- Hashed Transactions ---");
//        System.out.println( trnx_hashes );      


//for definite 7 items - optional
//        List<Septet<String, String, String, String, String, String, String>> hashes = hashLstAll.stream()
//                .map( elem -> Septet.fromCollection(elem) )
//                .collect(Collectors.toList());
//        System.out.println( hashes );