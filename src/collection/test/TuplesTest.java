package collection.test;
import java.util.*;
import org.javatuples.Triplet;

/*
javatuples offers you tuple classes from one to ten elements:
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

public class TuplesTest {
    public static void main(String[] args) {
        
        //assume that we are to colect 3 items
        Triplet<String, String, String> metaData = new Triplet("OrderID", "Payment", "DataTranx");
        System.out.println(metaData + "\n");                
        
        List<String> dataList = Arrays.asList("1001", "200.00", "20/4/2020");        
        Triplet<String, String, String> orderData = Triplet.fromCollection(dataList);
        
        System.out.println(orderData);
    }
}

//In Block Class,
//Private String data: -> Private BlockchainDataWrapper data
class BlockchainDataWrapper{
    int id;
    String data;
}
