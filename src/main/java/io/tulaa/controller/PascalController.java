package io.tulaa.controller;/**
 * Created by admin on 6/22/18.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class PascalController {

    @RequestMapping(value = "pascal", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="value", defaultValue="1") int num) {

        int i;
        for (i = 0; i < num; i++) {

            System.out.println(getRow(i));
            if (i == num) {
                getRow(i);

                break;
            }
        }
        return Collections.singletonMap("response", "ok");
    }


    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }
        LinkedList<Integer> current = new LinkedList<Integer>();
        LinkedList<Integer> next = new LinkedList<Integer>();

        current.add(1);
        int start = 0;

        while (start < rowIndex) {
            current.addFirst(0);
            current.add(0);

            calculateNext(current, next);

            LinkedList<Integer> temp = next;
            next = current;
            current = temp;
            start++;

        }

        return current;
    }

    private static void calculateNext(LinkedList<Integer> current, LinkedList<Integer> next) {
        if (current == null) {
            return;
        }
        while (current.size() > 1) {
            int num1 = current.pop();
            int num2 = current.peek();
            next.add(num1 + num2);
        }
        current.clear();
    }
}
