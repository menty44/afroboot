package com.blaqueyard.repository;/**
 * Created by admin on 7/8/18.
 */

import com.blaqueyard.model.Phonenumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@Repository
public interface PhonenumberRepository extends JpaRepository<Phonenumbers, Long> {
}
