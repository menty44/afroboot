package com.blaqueyard.controller;

/**
 * Created by admin on 7/3/18.
 */


import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

//import org.springframework.web.bind.annotation.*;

//import org.springframework.web.bind.annotation.*;

//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class MpesaController {

    @CrossOrigin
    @RequestMapping(value = "mpesastk", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="number", defaultValue="not available") String number ,
                      @RequestParam(value="amount", defaultValue="not available") String amount) throws IOException {

        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print(number);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(amount);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");

        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V","oOpJICRVlyrGSAkM");

        a.authenticate();

        a.STKPushSimulation(amount , number );


        return Collections.singletonMap("response", "ok");
    }


}
