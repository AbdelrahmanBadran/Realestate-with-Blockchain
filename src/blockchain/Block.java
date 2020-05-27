package blockchain;

import hashing.Hashing;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.javatuples.Septet;

public class Block implements Serializable{        
     
    public Block(long blockID,long  trnxcount, List<List<String>> data, String previoushash) {
        List<Septet<String, String, String, String, String, String, String>> hashes = 
            data.stream().map( elem -> Septet.fromCollection(elem) ).collect(Collectors.toList());
        
        this.blockID = blockID;
        this.transactionCount = trnxcount;
        this.transactions = hashes;
        this.previousHash = previoushash;
        this.currentHash = this.blockHashCode(Block.genByteArr(this.transactions), this.previousHash, this.timestamp);
        this.timestamp = Calendar.getInstance().getTimeInMillis();          
        this.nonce = new SecureRandom().nextLong();
    }
    
    //private String encrypted_key_used for decryption
    private long blockID;
    private String previousHash;
    private String currentHash;    
    private long transactionCount; 
    private List<Septet<String, String, String, String, String, String, String>> transactions; 
    private long timestamp;            
    private long nonce;            

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

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
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
        return "Current Hash: " + this.currentHash + "\nPrevious Hash:" + this.previousHash + "\n";
    }
}

/*Javatuples
Unit<A> (1 element)
Pair<A,B> (2 elements)
Triplet<A,B,C> (3 elements)
Quartet<A,B,C,D> (4 elements)
Quintet<A,B,C,D,E> (5 elements)
Sextet<A,B,C,D,E,F> (6 elements)
Septet<A,B,C,D,E,F,G> (7 elements)
Octet<A,B,C,D,E,F,G,H> (8 elements)
Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
*/