package com.blaqueyard.controller;
/**
 * Created by admin on 6/29/18.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

//import org.springframework.ws.mime.MimeMessage;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */


@RestController
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());




    //@CrossOrigin(origins = {"http://localhost:8080, http://localhost:8100"}, maxAge = 6000)
    @CrossOrigin
    @RequestMapping(value = "emailtest", method = RequestMethod.GET, produces = "application/json")
    public Map getRevs(@RequestParam(value="name", defaultValue="not available") String name) {
        System.out.print(name);

        return Collections.singletonMap("response", "ok");
    }


    @CrossOrigin
    @RequestMapping(value = "mpesa", method = RequestMethod.GET, produces = "application/json")
    public Map getReMpesa(@RequestParam(value="number", defaultValue="not available") String number ,
                      @RequestParam(value="amount", defaultValue="not available") String amount) throws IOException {

        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print(number);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(amount);
        System.out.print("\n");
        System.out.print("###########################################################################################\n");


        return Collections.singletonMap("response", "ok");
    }


    //@CrossOrigin(origins = {"http://localhost:8080, http://localhost:8100"}, maxAge = 6000)
    @CrossOrigin
    @RequestMapping(value = "emailfinal", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value="name", defaultValue="not available") String name ,
                      @RequestParam(value="bodys", defaultValue="not available") String bodys) {

        String from = "info@blaqueyard.com";
        String to = name;
        String subject = "Welcome ;) here are your Credentials!";

        System.out.print("\n");
        System.out.print("\n");
        System.out.print("###############################################################\n");
        System.out.print(name);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(bodys);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(to);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(subject);
        System.out.print("\n");
        System.out.print("###############################################################\n");

        sendMail(from, to, subject , bodys);

        return Collections.singletonMap("response", "ok");
    }


    public void sendMail( String from, String to, String subject, String body) {

        if (from != null && to != null && subject != null && body != null) {
            SimpleMailMessage mail = new SimpleMailMessage();

            try {
                mail.setFrom(from);
                mail.setTo(to);
                mail.setSubject(subject);
                mail.setText(body);

                logger.info("Sending...");

                javaMailSender.send(mail);

                logger.info("Done!");
            }catch (Exception ex){
                System.out.println(ex);
            }
        }else {
            System.out.println("Missing Parameters");
        }


    }

    @CrossOrigin
    @RequestMapping(value = "email", method = RequestMethod.GET, produces = "application/json")
    public Map getRevss(@RequestParam(value="name", defaultValue="not available") String name ,
                      @RequestParam(value="bodys", defaultValue="not available") String bodys) {

        System.out.print("\n");
        System.out.print("\n");
        System.out.print("###############################################################");
        System.out.print(name);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(bodys);
        System.out.print("###############################################################\n");


        if (name != null && bodys != null) {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            try {

                helper.setTo("set-your-recipient-email-here@gmail.com");
                helper.setText("How are you?");
                helper.setSubject("Hi");

                //return helper;
                sender.send(message);

                return Collections.singletonMap("response", helper);

            } catch (Exception ex) {
                //return "Error in sending email: " + ex;
                return Collections.singletonMap("error_response", ex);
            }

        } else {

        }

        return Collections.singletonMap("response", "ok");
    }


//start mpesa



//    end mpesa

}
