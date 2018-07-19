package com.blaqueyard.controller;

/**
 * Created by admin on 7/8/18.
 */


import com.blaqueyard.exception.ResourceNotFoundException;
import com.blaqueyard.model.Phonenumbers;
import com.blaqueyard.repository.PhonenumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class PhoneNumbersController {

    @Autowired
    PhonenumberRepository phonenumberRepository;

    @CrossOrigin
    @PostMapping("/createnumber")
    public Phonenumbers createnumbers(@Valid @RequestBody Phonenumbers pn) {
        return phonenumberRepository.save(pn);
    }

    @CrossOrigin
    @GetMapping("/numbers")
    public List<Phonenumbers> getAllNumbers() {
        return phonenumberRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/getnumber/{id}")
    public Phonenumbers getnumberById(@PathVariable(value = "id") Long phoneId) {
        return phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));
    }

    @CrossOrigin
    @PutMapping("/updatenumber/{id}")
    public Phonenumbers updatenumber(@PathVariable(value = "id") Long phoneId,
                               @Valid @RequestBody Phonenumbers phonenumber) {

        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        //pn.setN(phonenumber.getPhonenumber());
        pn.setPhonenumber(phonenumber.getPhonenumber());

        Phonenumbers updatenumber = phonenumberRepository.save(pn);
        return updatenumber;
    }

    @CrossOrigin
    @PostMapping("/deletenumber/{id}")
    public ResponseEntity<?> deleteNumber(@PathVariable(value = "id") Long phoneId) {
        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        phonenumberRepository.delete(pn);

        return ResponseEntity.ok().build();
    }


    @CrossOrigin
    @GetMapping("/phoneno/{moboperator}")
    public Optional<Phonenumbers> getnumberByMoboperator(@PathVariable(value = "phonenumber") String phonenumber) {

        System.out.println("###########################################################################\n");
        System.out.println("\n");
        System.out.println(phonenumber);
        System.out.println("\n");
        System.out.println("###########################################################################\n");

        Optional<Phonenumbers> pn = phonenumberRepository.findByNameIgnoreCase(phonenumber);

        return pn;

    }

    @CrossOrigin
    @PostMapping("/testme")
    public List<Phonenumbers>  createtest(@PathVariable(value = "phonenumber") String pn) {

        System.out.println("###########################################################################\n");
        System.out.println("\n");
        System.out.println(pn);
        System.out.println("\n");
        System.out.println("###########################################################################\n");
        //return phonenumberRepository.save(pn);

        return phonenumberRepository.findAll();
    }

}
