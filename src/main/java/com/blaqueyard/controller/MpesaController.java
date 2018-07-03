package com.blaqueyard.controller;/**
 * Created by admin on 7/3/18.
 */

//import org.apache.catalina.connector.Response;
//import org.omg.CORBA.Request;
//import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class MpesaController {

    @CrossOrigin
    @RequestMapping(value = "emailfinal", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="name", defaultValue="not available") String name ,
                      @RequestParam(value="bodys", defaultValue="not available") String bodys) {

        return Collections.singletonMap("response", "ok");
    }



}
