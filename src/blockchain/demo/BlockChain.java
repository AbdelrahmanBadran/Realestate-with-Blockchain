package blockchain.demo;

import blockchain.core.Block;
import com.google.gson.GsonBuilder;
import java.util.LinkedList;

public class BlockChain {
    
    //Data structure    
    static LinkedList<Block> bchain = new LinkedList();
    
    public BlockChain() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        tstBlock();		
    }
    
    public static void tstBlock() {
        Block b1 = new Block("Data1", "0");        
        bchain.add(b1);
        
        Block b2 = new Block("Data2", b1.getCurrentHash());        
        bchain.add(b2);
        
        Block b3 = new Block("Data3", b2.getCurrentHash());        
        bchain.add(b3);
        
        Block b4 = new Block("Data4", b3.getCurrentHash());        
        bchain.add(b4);
        
//        List<String> dataList = Arrays.asList("1001", "200.00", "20/4/2020");
//        Block b5 = new Block(dataList, b4.getCurrentHash());        
//        bchain.add(b5);
        
        //Display the Linkedlist elements/blocks
        String temp = new GsonBuilder().setPrettyPrinting().create().toJson(bchain);        
        System.out.println(temp);        
    }    
}
