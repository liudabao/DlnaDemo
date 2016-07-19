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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        WifiManager wm = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiManager.MulticastLock multicastLock = wm.createMulticastLock("multicastLock");
        multicastLock.setReferenceCounted(true);
        multicastLock.acquire();
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SendMSearchMessage();
                    }
                }).start();
            }
        });
    }

    private void SendMSearchMessage() {

        SSDPSearchMsg searchContentDirectory = new SSDPSearchMsg(SSDPConstants.ST_ContentDirectory);
        SSDPSearchMsg searchAVTransport = new SSDPSearchMsg(SSDPConstants.ST_AVTransport);
        SSDPSearchMsg searchProduct = new SSDPSearchMsg(SSDPConstants.ST_Product);

        SSDPSocket sock;
        try {
            sock = new SSDPSocket();
            for (int i = 0; i < 2; i++) {
                sock.send(searchContentDirectory.toString());
                sock.send(searchAVTransport.toString());
                sock.send(searchProduct.toString());
            }

            while (true) {
                DatagramPacket dp = sock.receive(); //Here, I only receive the same packets I initially sent above
                String c = new String(dp.getData());
                System.out.println(c);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("M-SEARCH", e.getMessage());

        }
    }
}
