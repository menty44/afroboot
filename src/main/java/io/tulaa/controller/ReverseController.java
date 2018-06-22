package io.tulaa.controller;

/**
 * Created by admin on 6/22/18.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class ReverseController {

    public static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "rev", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="name", defaultValue="World") String name) {
        return Collections.singletonMap("response", reverseString(name));
    }

    public static String reverseString(String input) {
        char[] inputArr = input.toCharArray();
        char[] tempArr = new char[input.length()];
        int i = 0;
        int j = 0;

        for (char ch : inputArr) {
            if (Character.isAlphabetic(ch)) {
                tempArr[i] = ch;
                i++;
            }
        }
        i--;

        while (j < i) {
            char temp = tempArr[i];
            tempArr[i] = tempArr[j];
            tempArr[j] = temp;
            j++;
            i--;
        }

        for (i = 0, j = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(inputArr[i])) {
                inputArr[i] = tempArr[j++];
            }
        }

        return new String(inputArr);
    }
}
