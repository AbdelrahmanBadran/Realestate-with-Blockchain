package blockchain;

import hashing.HashingUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.javatuples.Septet;

public class Block implements Serializable{
    
    public Block(List<List<String>> data, String previoushash) {
        List<Septet<String, String, String, String, String, String, String>> hashes = 
            data.stream().map( elem -> Septet.fromCollection(elem) ).collect(Collectors.toList());
        
        this.data = hashes;
        this.previoushash = previoushash;
        this.timestamp = Calendar.getInstance().getTimeInMillis();
        this.currentHash = this.blockHashCode(Block.genByteArr(this.data), this.previoushash, this.timestamp);
    }
    
//    private int block_ID;
//    private int prev_block_ID;
//    private int next_block_ID;    
    
    private String previoushash;
    private String currentHash;    
    private List<Septet<String, String, String, String, String, String, String>> data; 
    private long timestamp;        
    
    //private int transaction_ID
    //pivate transaction_count;    
    //private int nonce;

    public String getPrevioushash() {
        return previoushash;
    }

    public void setPrevioushash(String previoushash) {
        this.previoushash = previoushash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public List<Septet<String, String, String, String, String, String, String>> getData() {
        return data;
    }

    public void setData(List<Septet<String, String, String, String, String, String, String>> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
 
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public String blockHashCode(byte[] data, String prevhash, long timestamp) {
        return HashingUtils.newhash(data + prevhash + timestamp ,"SHA-256");
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
        return "Current Hash: " + this.currentHash + "\nPrevious Hash:" + this.previoushash + "\n";
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

//DATA and DIGITAL SIGNATURE:
//        Pair<String, String> pair1 = Pair.with("sample_hash1", "digital_signature1");
//        Pair<String, String> pair2 = Pair.with("sample_hash2", "digital_signature2");
//        Pair<String, String> pair3 = Pair.with("sample_hash2", "digital_signature3");
//        
//        //collect 3 items and etc
//        Triplet<Pair<String, String>, Pair<String, String>, Pair<String, String>> signed_data = Triplet.with(pair1, pair2, pair3);
//        System.out.println( signed_data );

// BLOCK CLASS
//public class DataStructure {    
//        Triplet<String, String, String> metaData = new Triplet("OrderID", "Payment", "DataTranx");           //        
//        List<String> dataList = Arrays.asList("1001", "200.00", "20/4/2020");        
//        Triplet<String, String, String> orderData = Triplet.fromCollection(dataList);        
//}