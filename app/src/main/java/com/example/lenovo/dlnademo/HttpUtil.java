package com.example.lenovo.dlnademo;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lenovo on 2016/7/19.
 */
public class HttpUtil {
    private static final  OkHttpClient okHttpClient=new OkHttpClient();

    public static String get(String url){

        String result=null;
        Request request=new Request.Builder().url(url).build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                result=response.body().string();
            }
            else {
                Log.e("HTTP","fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getHttpRequest(String request){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            URL url=new URL(request);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            if(connection.getResponseCode()==200){
                Log.e("connection", "connect");
                inputStream=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuffer buffer=new StringBuffer();
                while((line=reader.readLine())!=null){
                    buffer.append(line);

                }
                Log.e("Data", buffer.toString());
            }
            else {
                Log.e("connection", "disconnect");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return request;
    }
}
