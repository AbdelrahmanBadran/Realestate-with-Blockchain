package blockchain;

import hashing.Hashing;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.javatuples.Septet;

public class Block implements Serializable{        
     
    public Block(long blockID,long  trnxcount, List<List<String>> data, String previoushash) {
        List<Septet<String, String, String, String, String, String, String>> hashes = data
                    .stream()
                    .map( elem -> Septet.fromCollection(elem) )
                    .collect(Collectors.toList());

        this.blockID = blockID;
        this.transactionCount = trnxcount;
        this.transactions = hashes;
        this.previousHash = previoushash;
        this.currentHash = this.blockHashCode(Block.genByteArr(this.transactions), this.previousHash, this.timestamp);
        this.timestamp = Calendar.getInstance().getTimeInMillis();          
        this.nonce = Base64.getEncoder().encodeToString(hashing.Hashing.getSalt());
    }
        
    private long blockID;
    private String previousHash;
    private String currentHash;    
    private long transactionCount; 
    private List<Septet<String, String, String, String, String, String, String>> transactions; 
    private long timestamp;            
    private String nonce;            

    public long getBlockID() {
        return blockID;
    }

    public void setBlockID(long blockID) {
        this.blockID = blockID;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public List<Septet<String, String, String, String, String, String, String>> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Septet<String, String, String, String, String, String, String>> transactions) {
        this.transactions = transactions;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }  
    
    public String blockHashCode(byte[] data, String prevhash, long timestamp) {
        return Hashing.hash(data + prevhash + timestamp ,"SHA-256");
    }	    
        
    private static byte[] genByteArr(List<Septet<String, String, String, String, String, String, String>> b) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        ObjectOutputStream out;
        if (b != null) {
            try {
                out = new ObjectOutputStream(boas);
                out.writeObject(b);
                out.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return null; 
            }
            return boas.toByteArray();
        } else {
            return null; 
        }
    }
        
    @Override
    public String toString() {
        return transactions + "\n";
    }
}