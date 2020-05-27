package transaction;

import app.simulate;
import cryptography.Config;
import cryptography.KeyRetriever;
import hashing.Hashing;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrnxPoolAdapter {    
    
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
           
    public static List<Transaction> getTransactions(){
        List<String> trnxLst = Transaction.getAll();
        return trnxLst.stream()
                .map( record -> record.split("\\|") )
                .filter( arr -> !arr[0].equals("PROPERTYID") )                
                .map(arr -> new Transaction( arr[0], arr[1] , arr[2], arr[3], arr[4], LocalDateTime.parse(arr[5], FORMATTER), arr[6] ))
                .collect( Collectors.toList() );
    }
    
    //2D collection
    public static List<List<String>> getTransactionsHashes(){
        
        List<Transaction> trnxPool = getTransactions();
        
        //generate hash value of each data in Transaction                
        List<List<String>> hashLstAll = new ArrayList();                
        for (Transaction trnx : trnxPool) {              
            List<String> hashLst = new ArrayList();
                hashLst.add( simulate.crypto.encrypt(trnx.getPropertyID(), KeyRetriever.getPrivateKey(Config.CRYPTO_FILE + simulate.SELLER + Config.PRIVATE_FILE, Config.CRYPTO_ALGO)) );
                hashLst.add(Hashing.hash(trnx.getOwnerID(), "SHA-256") );
                hashLst.add(Hashing.hash(trnx.getBuyerID(), "SHA-256") );
                hashLst.add(Hashing.hash(trnx.getTrnxID(), "SHA-256") );
                hashLst.add(Hashing.hash(trnx.getPayment(), "SHA-256") );                
                hashLst.add(Hashing.hash(trnx.getTrnxDate().toString(), "SHA-256") );                
                hashLst.add(simulate.digSign.sign(simulate.SELLER, String.join("|", hashLst)));                
            hashLstAll.add(hashLst);
        }
        return hashLstAll;
    }    
}



//DATA and DIGITAL SIGNATURE:
//        Pair<String, String> pair1 = Pair.with("sample_hash1", "digital_signature1");
//        Pair<String, String> pair2 = Pair.with("sample_hash2", "digital_signature2");
//        Pair<String, String> pair3 = Pair.with("sample_hash2", "digital_signature3");
//        
//        //collect 3 items and etc
//        Triplet<Pair<String, String>, Pair<String, String>, Pair<String, String>> signed_data = Triplet.with(pair1, pair2, pair3);
//        System.out.println( signed_data );

//For definite 7 items - optional
//        List<Septet<String, String, String, String, String, String, String>> hashes = 
//             hashLstAll.stream()
//                .map( elem -> Septet.fromCollection(elem) )
//                .collect(Collectors.toList());