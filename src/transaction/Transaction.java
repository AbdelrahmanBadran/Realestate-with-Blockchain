package transaction;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Transaction {
    
    private static final String FILENAME = "data/trnxpool.txt";        
    
    private String propertyID;
    private String ownerID;
    private String buyerID;
    private String payment;
    private String trnxID;                
    private LocalDateTime trnxDate;
    private String signature;

    public Transaction(String propertyID, String ownerID, 
            String buyerID, String payment, String trnxID, 
            LocalDateTime trnxDt, String signature) {
        this.propertyID = propertyID;
        this.ownerID = ownerID;
        this.buyerID = buyerID;
        this.payment = payment;
        this.trnxID = trnxID;        
        this.trnxDate = trnxDt;
        this.signature = signature;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTrnxID() {
        return trnxID;
    }

    public void setTrnxID(String trnxID) {
        this.trnxID = trnxID;
    }

    public LocalDateTime getTrnxDate() {
        return trnxDate;
    }

    public void setTrnxDate(LocalDateTime trnxDate) {
        this.trnxDate = trnxDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
     
    public static List<String> getAll(){
        try {
            return Files.readAllLines(Paths.get(FILENAME)).stream().collect(Collectors.toList());
        } catch (IOException ex) {return null;}
    }
    
    public static long transactionCount() {
        try {        
            return Files.lines(Paths.get(FILENAME)).count();
        } catch (IOException ex) {return 0;}
    }
    
    public static void empty(){
        try {
            FileChannel.open(Paths.get(FILENAME), StandardOpenOption.WRITE).truncate(0).close();
        } catch (IOException ex) {}
    }
    
    @Override
    public String toString() {
        return "Transaction{" + "propertyID=" + propertyID + ", ownerID=" + ownerID + ", buyerID=" + buyerID + ", payment=" + payment + ", trnxID=" + trnxID + ", trnxDate=" + trnxDate + ", signature=" + signature + '}';
    }   
}
