package com.example.lenovo.dlnademo;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;

public class MainActivity extends AppCompatActivity {

    Button send;
    Button receive;
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getPersimmions();
        init();
    }

    private void init(){
        WifiManager wm = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        Log.e("TAG", wm.getConnectionInfo()+"");
        WifiManager.MulticastLock multicastLock = wm.createMulticastLock("multicastLock");
        multicastLock.setReferenceCounted(true);
        multicastLock.acquire();
        send=(Button)findViewById(R.id.send);
        receive=(Button)findViewById(R.id.receive);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        send();
                    }
                }).start();
            }
        });
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        receive();
                    }
                }).start();
            }
        });
    }


    private void send(){
        SSDPSearchMsg searchContentDirectory = new SSDPSearchMsg(SSDPConstants.ST_ContentDirectory);
        SSDPSearchMsg searchAVTransport = new SSDPSearchMsg(SSDPConstants.ST_AVTransport);
        SSDPSearchMsg searchProduct = new SSDPSearchMsg(SSDPConstants.ST_Product);
        SSDPClientSocket sock;
        try {
            sock = new SSDPClientSocket();
            Log.e("send", "start");
            sock.send(searchContentDirectory.toString());
            sock.send(searchAVTransport.toString());
            sock.send(searchProduct.toString());
            Log.e("send", searchContentDirectory.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("M-SEARCH", e.getMessage());

        }
    }

    private void receive(){

        SSDPServerSocket sock;
        try {
            Log.e("Recieve", "start");
            sock = new SSDPServerSocket();

            while (true) {
                DatagramPacket dp = sock.receive(); //Here, I only receive the same packets I initially sent above
                String c = new String(dp.getData());
                Log.e("Recieve", c);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("M-SEARCH", e.getMessage());

        }
    }
}
