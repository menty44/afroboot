package com.blaqueyard;/**
 * Created by admin on 7/16/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

// Java program to illustrate
// Java.util.HashMap

import java.util.Arrays;
import java.util.HashMap;

public class GFG {

    public HashMap<String, Integer> countIntem( String[] array ) {

        Arrays.sort(array);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Integer count = 0;
        String first = array[0];
        for( int counter = 0; counter < array.length; counter++ ) {
            if(first.hashCode() == array[counter].hashCode()) {
                count = count + 1;
            } else {
                map.put(first, count);
                count = 1;
            }
            first = array[counter];
            map.put(first, count);
        }

        return map;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] array = { "name1", "name1", "name2", "name2", "name2",
                "name3", "name1", "name1", "name2", "name2", "name2", "name3" , "name0"};

        System.out.println("Array Length   :    " + array.length);


        HashMap<String, Integer> countMap = new GFG().countIntem(array);
        System.out.println(countMap);



    }
}