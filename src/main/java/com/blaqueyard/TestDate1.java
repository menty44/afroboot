package com.blaqueyard;

/**
 * Created by admin on 7/3/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import com.blaqueyard.repository.PhonenumberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



public class TestDate1 {

    @Autowired
    PhonenumberRepository phonenumberRepository;
    public static void main(String[] args) throws IOException {

        //Creating list of Books
        List<Book> mylist=new ArrayList<Book>();



        HashMap<String, Long> countresults= new HashMap<>();
        HashMap<String, Integer> strengthResults= new HashMap<>();
        AtomicLong totalTimeTalked=new AtomicLong();

        //Creating Books
        Book b1=new Book(101,0720106420,2);
        Book b2=new Book(102,0722222222,34);
        Book b3=new Book(104,0720106420,78);
        Book b4=new Book(105,0720106222,74);
        Book b5=new Book(106,0720106222,49);
        Book b6=new Book(107,0720106420,56);
        Book b7=new Book(108,0720106420,63);


        //Adding Books to list
        mylist.add(b1);
        mylist.add(b2);
        mylist.add(b3);
        mylist.add(b4);
        mylist.add(b5);
        mylist.add(b6);
        mylist.add(b7);

        // put values into map
        //map.put("A", list);


        //System.out.println(list);
        //Traversing list
        for(Book b:mylist){
            System.out.println(b.id+" "+b.phonenumber+" "+b.duration);
            System.out.println("\n");
            System.out.println(totalTimeTalked.addAndGet(b.duration));


                //Do we have this msisdn under incoming within the map?
                Long currentCount=countresults.get(b.phonenumber);
                //currentCount=currentCount+b.duration;
                countresults.put(Integer.toString(b.phonenumber), currentCount);
                //int percentage=Long.valueOf(totals/b.duration).intValue()*100;
//            int total = findSumWithoutUsingStream(arr);
//                System.out.println(total);

        }


    }

    public static int findSumWithoutUsingStream(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
