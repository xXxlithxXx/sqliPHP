package com.king.sqliPHP.PHP;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Truncate extends AsyncTask<String, Void, String> {
    public String URL = "https://jsonphp.000webhostapp.com/sqli2020/";
    public String URLAdd = URL + "Add.php";
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public String doInBackground(String... params) {
        if (params.length > 0) {
            if (params[0].equalsIgnoreCase("Truncate")) {
                return onTruncate(params);
            }
        }
        return null;
    }

    /*******************************************************************************/
    private String onTruncate(String... params) {
        try {
            // preparing the URL for the connection
            URL url = new URL(URL + "truncate.php");
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel = (HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            //channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel = channel.getOutputStream();
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Update data to MySQL";

        /*******************************************************************************/
    }

    public void StudentFunction(final String name, final String phone, final String email) {
        class StudentRegisterClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("name", params[0]);
                hashMap.put("phone", params[1]);
                hashMap.put("email", params[2]);
                finalResult = httpParse.postRequest(hashMap, URLAdd);
                return finalResult;
            }
        }
        StudentRegisterClass userRegisterFunctionClass = new StudentRegisterClass();
        userRegisterFunctionClass.execute(name, phone, email);
    }
}