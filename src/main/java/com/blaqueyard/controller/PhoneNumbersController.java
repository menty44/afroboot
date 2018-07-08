package com.blaqueyard.controller;/**
 * Created by admin on 7/8/18.
 */

import com.blaqueyard.model.Phonenumbers;
import com.blaqueyard.repository.PhonenumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class PhoneNumbersController {

    @Autowired
    PhonenumberRepository phonerepo;

    @CrossOrigin
    @PostMapping("/number")
    public Phonenumbers createnumbers(@Valid @RequestBody Phonenumbers pn) {
        return phonerepo.save(pn);
    }


}
