package collection.test;

import java.util.*;

/*To test the collection APIs that is potentially used for the data property in Block class
  You're required to choose an appropriate APIs for data collection inside block */

public class CollectionApis {
    public static void main(String[] args) {
        testSetColl();
        testMapColl();
    }
    
    //collection API: Set (a Java interface: Implemented class: HashSet, TreeSet, LinkedHashSet)    
    static void testSetColl(){
//        List<String> lst = new ArrayList();  

//        Set<String> names = new HashSet();
//        Only key value elements are displayed since no duplication in a Set

//        Set<String> names = new LinkedHashSet();
//        Like Hashset and also garauntees order of entry

        Set<String> names = new TreeSet();
        //Sorts key value entries
        
        names.add("John");
        names.add("Helen");
        names.add("John");
        names.add("Ali");
        names.add("Mary");
        names.add("Bill");
                
        System.out.println("Elements: " + names);
    }
    
    static void testMapColl(){
        //Map-JavaInterface (Implemented class: HashMap, LinkedHashMap, TreeMap)
        //Map stores key, value
        
        Map<Integer, String> nameLst = new HashMap();
        //No duplicated Key but can duplicate Values (Similar to DB primary key and date)
        
        nameLst.put(1, "John");
        nameLst.put(2, "Helen");
        nameLst.put(3, "John");
        nameLst.put(4, "Ali");
        nameLst.put(5, "Mary");
        nameLst.put(6, "Bill");
                
//        Map<String, Student> nameLst = new HashMap();
//        nameLst.put("TP101010", new Student());

        System.out.println("Elements: " + nameLst);
    }
    
    class Student{}
}
