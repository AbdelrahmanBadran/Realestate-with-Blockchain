package blockchain.core;

import java.io.Serializable;

import blockchain.shautils.HashingUtils;
import java.util.Calendar;

public class Block implements Serializable{

    public Block() {
        // TODO Auto-generated constructor stub
    }

    public Block(String data, String previoushash) {
        this.data = data;
        this.previoushash = previoushash;
        this.timestamp = Calendar.getInstance().getTimeInMillis();
        //gen current hash value
        this.currentHash = this.blockHashCode(this.data, this.previoushash, this.timestamp);
    }
    
    private String data;
    private String previoushash;
    private long timestamp;
    private String currentHash;   

    public void setPrevioushash(String previoushash) {
        this.previoushash = previoushash;
    }
        
    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public String getPrevioushash() {
        return previoushash;
    }
    
        
    public String getCurrentHash() {
        return currentHash;
    }

//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

//    public String blockHashCode() {
//        return HashingUtils.hash(Block.genByteArr(this), "SHA-256");
//    }

    public String blockHashCode(String data, String prevhash, long timestamp) {
        return HashingUtils.newhash(data + prevhash + timestamp ,"SHA-256");
    }	
	
//    private static byte[] genByteArr(Block b) {
//        ByteArrayOutputStream boas = new ByteArrayOutputStream();
//        ObjectOutputStream out;
//        if ( b != null ) {
//            try {
//                out = new ObjectOutputStream(boas);
//                out.writeObject(b);
//                out.flush();
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//                return null;
//            }
//            return boas.toByteArray();
//        } else {
//            return null;
//        }   
//    }
        
    @Override
    public String toString() {
        return "Current Hash: " + this.currentHash + "\nPrevious Hash:" + this.previoushash + "\n";
    }
}
