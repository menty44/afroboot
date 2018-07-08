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

    @GetMapping("/number/{id}")
    public Phonenumbers getGenderById(@PathVariable(value = "id") Long phoneId) {
        return phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));
    }


    @PutMapping("/number/{id}")
    public Phonenumbers updateGender(@PathVariable(value = "id") Long phoneId,
                               @Valid @RequestBody Phonenumbers GenderName) {

        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        //gender.setTitle(noteDetails.getTitle());
        pn.setName(GenderName.getName());

        Phonenumbers updatedGender = phonenumberRepository.save(pn);
        return updatedGender;
    }


    @DeleteMapping("/gender/{id}")
    public ResponseEntity<?> deleteNumber(@PathVariable(value = "id") Long phoneId) {
        Phonenumbers pn = phonenumberRepository.findById(phoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Phonenumber", "id", phoneId));

        phonenumberRepository.delete(pn);

        return ResponseEntity.ok().build();
    }



}
