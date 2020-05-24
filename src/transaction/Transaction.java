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
    
    private String orderItem;
    private LocalDateTime orderDt;
    private String payment;
    private String email;
    private String deliveryAddr;
    private String status;
    private LocalDateTime trnxDt;

    public Transaction(String orderItem, LocalDateTime orderDt, String payment, String email, String deliveryAddr, String status, LocalDateTime trnxDt) {
        this.orderItem = orderItem;
        this.orderDt = orderDt;
        this.payment = payment;
        this.email = email;
        this.deliveryAddr = deliveryAddr;
        this.status = status;
        this.trnxDt = trnxDt;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public LocalDateTime getOrderDt() {
        return orderDt;
    }

    public String getPayment() {
        return payment;
    }

    public String getEmail() {
        return email;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTrnxDt() {
        return trnxDt;
    }
    
    public static List<String> getAll(){
        try {
            return Files.readAllLines(Paths.get(FILENAME)).stream().collect(Collectors.toList());
        } catch (IOException ex) {return null;}
    }
    
    public static void empty(){
        try {
            FileChannel.open(Paths.get(FILENAME), StandardOpenOption.WRITE).truncate(0).close();
        } catch (IOException ex) {}
    }

    @Override
    public String toString() {
        return "Transaction{" + "orderItem=" + orderItem + ", orderDt=" + orderDt + ", payment=" + payment + ", email=" + email + ", deliveryAddr=" + deliveryAddr + ", status=" + status + ", trnxDt=" + trnxDt + '}';
    }   
}
