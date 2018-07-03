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
import java.util.Base64;
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

    String appKey;
    String appSecret;

    public EmailController(String app_key, String app_secret){
        appKey=app_key;
        appSecret=app_secret;
    }

    public String authenticate() throws IOException {
        //String app_key = appKey/*"GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V"*/;
        String app_key = "Gxg0Thwl5rDAHh1swnXjMDCS836M8nIj";
        //String app_secret = appSecret;
        String app_secret = "PoxJvbGbT2f3gNno";
        String appKeySecret = app_key + ":" + app_secret;
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic "+encoded)
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject=new JSONObject(response.body().string());
        System.out.println(jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }
    public String C2BSimulation( String shortCode, String commandID, String amount, String MSISDN, String billRefNumber) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("Amount", amount);
        jsonObject.put("Msisdn", MSISDN);
        jsonObject.put("BillRefNumber", billRefNumber);

        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/safaricom/c2b/v1/simulate")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().toString();
    }

    public String B2CRequest( String initiatorName, String securityCredential,String commandID, String  amount, String partyA,String partyB, String remarks, String queueTimeOutURL, String resultURL, String occassion) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("InitiatorName", initiatorName);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Occassion", occassion);


        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");


        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url( "https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().toString();
    }

    public String B2BRequest( String initiatorName, String accountReference,String securityCredential,String commandID, String senderIdentifierType,String receiverIdentifierType,float  amount, String partyA,String partyB, String remarks, String queueTimeOutURL, String resultURL, String occassion) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("SenderIdentifierType", senderIdentifierType);
        jsonObject.put("RecieverIdentifierType",receiverIdentifierType);
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("AccountReference", accountReference);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);



        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/safaricom/b2b/v1/paymentrequest")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }


    public String STKPushSimulation(String businessShortCode, String password, String timestamp,String transactionType, String amount, String phoneNumber, String partyA, String partyB, String callBackURL, String queueTimeOutURL,String accountReference, String transactionDesc) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("BusinessShortCode", businessShortCode);
        jsonObject.put("Password", password);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("TransactionType", transactionType);
        jsonObject.put("Amount",amount);
        jsonObject.put("PhoneNumber", phoneNumber);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("CallBackURL", callBackURL);
        jsonObject.put("AccountReference", accountReference);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("TransactionDesc", transactionDesc);



        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");

        OkHttpClient client = new OkHttpClient();
        String url="https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")
                .build();


        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().toString();
    }
    public String STKPushTransactionStatus( String businessShortCode, String password, String timestamp, String checkoutRequestID) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("BusinessShortCode", businessShortCode);
        jsonObject.put("Password", password);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("CheckoutRequestID", checkoutRequestID);




        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");


        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query")
                .post(body)
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().toString();

    }
    public String reversal(String initiator, String securityCredential, String commandID, String transactionID, String amount, String receiverParty, String recieverIdentifierType, String resultURL,String queueTimeOutURL, String remarks, String ocassion) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Initiator", initiator);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("TransactionID", transactionID);
        jsonObject.put("Amount",amount);
        jsonObject.put("ReceiverParty", receiverParty);
        jsonObject.put("RecieverIdentifierType", recieverIdentifierType);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("Occasion", ocassion);



        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/safaricom/reversal/v1/request")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer xNA3e9KhKQ8qkdTxJJo7IDGkpFNV")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();
    }
    public String balanceInquiry(String initiator, String commandID, String securityCredential, String partyA, String identifierType, String remarks, String queueTimeOutURL, String resultURL) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Initiator", initiator);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("IdentifierType",identifierType);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);




        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/safaricom/accountbalance/v1/query")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer fwu89P2Jf6MB1A2VJoouPg0BFHFM")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "2aa448be-7d56-a796-065f-b378ede8b136")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public  String registerURL(String shortCode, String responseType, String confirmationURL, String validationURL) throws IOException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("ResponseType", responseType);
        jsonObject.put("ConfirmationURL", confirmationURL);
        jsonObject.put("ValidationURL", validationURL);




        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();
    }

//    end mpesa

}
