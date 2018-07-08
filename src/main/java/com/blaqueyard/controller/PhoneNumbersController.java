package com.blaqueyard.controller;/**
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
    @GetMapping("/number/{id}")
    public Phonenumbers getnumberById(@PathVariable(value = "id") Long phoneId) {
        return phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));
    }

    @CrossOrigin
    @PutMapping("/number/{id}")
    public Phonenumbers updatenumber(@PathVariable(value = "id") Long phoneId,
                               @Valid @RequestBody Phonenumbers phonenumber) {

        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        pn.setName(phonenumber.getPhonenumber());

        Phonenumbers updatenumber = phonenumberRepository.save(pn);
        return updatenumber;
    }

    @CrossOrigin
    @PostMapping("/number/{id}")
    public ResponseEntity<?> deleteNumber(@PathVariable(value = "id") Long phoneId) {
        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        phonenumberRepository.delete(pn);

        return ResponseEntity.ok().build();
    }



}
