//package com.blaqueyard;/**
// * Created by admin on 7/3/18.
// */
//
///**
// * Fredrick Oluoch
// * http://www.blaqueyard.com
// * 0720106420 | 0722508906
// * email: menty44@gmail.com
// */
//
//import okhttp3.*;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Base64;
//import java.util.Date;
//
//public class TestDate1 {
//
//    String appKey;
//    String appSecret;
//
//    public TestDate1(String app_key, String app_secret){
//
//        appKey=app_key;
//        appSecret=app_secret;
//
//        //appKey = "GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V";
//        //appSecret = "oOpJICRVlyrGSAkM";
//    }
//
//    public String authenticate() throws IOException {
//        String app_key = appKey/*"GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V"*/;
//        //String app_key = "Gxg0Thwl5rDAHh1swnXjMDCS836M8nIj";
//        String app_secret = appSecret;
//        //String app_secret = "PoxJvbGbT2f3gNno";
//        String appKeySecret = app_key + ":" + app_secret;
//        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
//        String encoded = Base64.getEncoder().encodeToString(bytes);
//
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
//                .get()
//                .addHeader("authorization", "Basic "+encoded)
//                .addHeader("cache-control", "no-cache")
//
//                .build();
//
//        Response response = client.newCall(request).execute();
//        JSONObject jsonObject=new JSONObject(response.body().string());
//        System.out.println(jsonObject.getString("access_token"));
//        return jsonObject.getString("access_token");
//    }
//
//    public String STKPushSimulation(String businessShortCode, String password, String timestamp,String transactionType, String amount, String phoneNumber, String partyA, String partyB, String callBackURL, String queueTimeOutURL,String accountReference, String transactionDesc) throws IOException {
//        JSONArray jsonArray=new JSONArray();
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("BusinessShortCode", businessShortCode);
//        jsonObject.put("Password", password);
//        jsonObject.put("Timestamp", timestamp);
//        jsonObject.put("TransactionType", transactionType);
//        jsonObject.put("Amount",amount);
//        jsonObject.put("PhoneNumber", phoneNumber);
//        jsonObject.put("PartyA", partyA);
//        jsonObject.put("PartyB", partyB);
//        jsonObject.put("CallBackURL", callBackURL);
//        jsonObject.put("AccountReference", accountReference);
//        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
//        jsonObject.put("TransactionDesc", transactionDesc);
//
//
//
//        jsonArray.put(jsonObject);
//
//        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
//
//        OkHttpClient client = new OkHttpClient();
//        String url="https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, requestJson);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .addHeader("content-type", "application/json")
//                .addHeader("authorization", "Bearer "+authenticate())
//                .addHeader("cache-control", "no-cache")
//                .build();
//
//
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//        return response.body().toString();
//    }
//
//
//    public String STKPushTransactionStatus( String businessShortCode, String password, String timestamp, String checkoutRequestID) throws IOException {
//        JSONArray jsonArray=new JSONArray();
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("BusinessShortCode", businessShortCode);
//        jsonObject.put("Password", password);
//        jsonObject.put("Timestamp", timestamp);
//        jsonObject.put("CheckoutRequestID", checkoutRequestID);
//
//
//
//
//        jsonArray.put(jsonObject);
//
//        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
//
//        System.out.println(requestJson);
//
//
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, requestJson);
//        Request request = new Request.Builder()
//                .url("https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query")
//                .post(body)
//                .addHeader("authorization", "Bearer "+authenticate())
//                .addHeader("content-type", "application/json")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//        return response.body().toString();
//
//    }
//
//
//
//    public static void main(String[] args) throws IOException {
//
//        //Get current date time
//        LocalDateTime now = LocalDateTime.now();
//
//        System.out.println("Before : " + now);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        String formatDateTime = now.format(formatter);
//
//        System.out.println("After : " + formatDateTime);
//
//        String pattern = "yyyyMMddhhmmss"; SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String tsdate = simpleDateFormat.format(new Date());
//        System.out.println(tsdate);
//
//
//
//        //MpesaController m=new MpesaController("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V","oOpJICRVlyrGSAkM");
//        TestDate1 m =new TestDate1("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V","oOpJICRVlyrGSAkM");
//        //Mpesa m=new Mpesa("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V","oOpJICRVlyrGSAkM");
//        m.authenticate();
//
//
//        String TRANSACTIONDESC = "MAKAO";
//
//        String BUSINESSSHORTCODE = "174379";
//
//        String PASSWORD = "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMTIwODE4";
//
//
//        String TIMESTAMP = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//
//
//        String TRANSACTIONTYPE = "CustomerPayBillOnline";
//
//
//        String PARTYB = "174379";
//
//
//        String CALLBACK = "http://callback.blaqueyard.com/mpesa/callback.php";
//
//
//        String ACCOUNTREF = "MAKAOLTD";
//
//        System.out.println(BUSINESSSHORTCODE);
//
//        try {
//            m.STKPushSimulation("174379","MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMTIwODE4", "20180703120818", TRANSACTIONTYPE , "1" , "254720106420" , "254720106420" , PARTYB , CALLBACK , CALLBACK , ACCOUNTREF , TRANSACTIONDESC);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        try {
////            m.STKPushTransactionStatus("174379","MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMTIwODE4","20180703120818","ws_CO_DMZ_46511211_03072018223700985");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//
//    }
//
//
//
//}
