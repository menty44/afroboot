//package com.blaqueyard.controller;/**
// * Created by admin on 7/6/18.
// */
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * Fredrick Oluoch
// * http://www.blaqueyard.com
// * 0720106420 | 0722508906
// * email: menty44@gmail.com
// */
//
//public class Res {
//
//    public static void main(String[]args) throws IOException {
//
//        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V","oOpJICRVlyrGSAkM");
//
//        a.authenticate();
//
//        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // Setting basic post request
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", "USER_AGENT");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        con.setRequestProperty("Content-Type","application/json");
//        con.setRequestProperty("authorization","Bearer "+a.authenticate());
//
//        JSONArray jsonArray=new JSONArray();
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("BusinessShortCode", "174379");
//        jsonObject.put("Password", "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMDYyMDIy");
//        jsonObject.put("Timestamp", "20180703062022");
//        jsonObject.put("TransactionType", "CustomerPayBillOnline");
//        jsonObject.put("Amount","2");
//        jsonObject.put("PhoneNumber", "254720106420");
//        jsonObject.put("PartyA", "254720106420");
//        jsonObject.put("PartyB", "174379");
//        jsonObject.put("CallBackURL", "http://callback.blaqueyard.com/mpesa/callback.php");
//        jsonObject.put("AccountReference", "MAKAOLTD");
//        jsonObject.put("QueueTimeOutURL", "http://callback.blaqueyard.com/mpesa/callback.php");
//        jsonObject.put("TransactionDesc", "MAKAO");
//
//
//        jsonArray.put(jsonObject);
//
//        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
//
//        //String postJsonData = "{\"id\":5,\"countryName\":\"USA\",\"population\":8000}";
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(requestJson);
//        wr.flush();
//        wr.close();
//
//
//        int responseCode = con.getResponseCode();
//        //System.out.println("\nSending 'POST' request to URL : " + url);
//        //System.out.println("Post Data : " + requestJson);
//        System.out.println(requestJson);
//        //System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer response = new StringBuffer();
//
//        while ((output = in.readLine()) != null) {
//            response.append(output);
//        }
//        in.close();
//
//        //printing result from response
//        System.out.println(response.toString());
//    }
//}
