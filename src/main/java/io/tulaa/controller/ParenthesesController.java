package io.tulaa.controller;/**
 * Created by admin on 6/22/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class ParenthesesController {

    @RequestMapping(value = "parentheses", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="value", defaultValue="1") int num) {

        int n = num;
        char[] str = new char[num*2];
        printParenthesis(str, n);

        return Collections.singletonMap("response", "ok");


    }

    // Function that print all combinations of
    // balanced parentheses
    // open store the count of opening parenthesis
    // close store the count of closing parenthesis
    static void _printParenthesis(char str[], int pos, int n, int open, int close)
    {
        if(close == n)
        {
            // print the possible combinations
            for(int i=0;i<str.length;i++)
                System.out.print(str[i]);
            System.out.println();
            return;
        }
        else
        {
            if(open > close) {
                str[pos] = '}';
                _printParenthesis(str, pos+1, n, open, close+1);
            }
            if(open < n) {
                str[pos] = '{';
                _printParenthesis(str, pos+1, n, open+1, close);
            }
        }
    }

    // Wrapper over _printParenthesis()
    static void printParenthesis(char str[], int n)
    {
        if(n > 0){
            _printParenthesis(str, 0, n, 0, 0);
        }else {
            System.out.println("error");
        }

        return;
    }
}
