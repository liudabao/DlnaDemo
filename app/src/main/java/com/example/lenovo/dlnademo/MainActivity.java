package com.example.lenovo.dlnademo;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button send;
    Button receive;
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo="";
    String str;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getPersimmions();
        handler=new Handler(){

            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        showToast(str);
                        Log.e("Recieve", str);
                        break;
                }

            }

        };
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

               // Timer timer=new Timer();
               // timer.schedule(new SendTask(), 3 * 1000, 5 * 1000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Device", "find");
                        find();
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

                        //receive();
                    }
                }).start();

            }
        });
    }

    class SendTask extends TimerTask{

        @Override
        public void run() {
            send();
        }
    }

    private void send(){
       // SSDPSearchMsg searchContentDirectory = new SSDPSearchMsg(SSDPConstants.ST_ContentDirectory);
       // SSDPSearchMsg searchAVTransport = new SSDPSearchMsg(SSDPConstants.ST_AVTransport);
       // SSDPSearchMsg searchProduct = new SSDPSearchMsg(SSDPConstants.ST_Product);
       // SSDPSearchMsg searchRoot=new SSDPSearchMsg(SSDPConstants.ST_RootDevice);
       // SSDPSearchMsg searchInternet=new SSDPSearchMsg(SSDPConstants.ST_InternetDevice);
        SSDPSearchMsg searchAll=new SSDPSearchMsg(SSDPConstants.ST_ALL);
        SSDPClientSocket sock;
        try {
            sock = new SSDPClientSocket();
            Log.e("send", "start");
            sock.send(searchAll.toString());
            //sock.send(searchRoot.toString());
           // sock.send(searchContentDirectory.toString());
           // sock.send(searchAVTransport.toString());
            //sock.send(searchProduct.toString());
            //Log.e("send", searchContentDirectory.toString());

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
                str = new String(dp.getData(),"utf-8");

                Message msg=new Message();
                msg.what=1;
                handler.sendMessage(msg);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("M-SEARCH", e.getMessage());

        }
    }

    private void showToast(String data){
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }


    private void find(){
        ControlPoint controlPoint=new ControlPoint();
        controlPoint.start();
        controlPoint.addSearchResponseListener(new SearchResponseListener() {
            @Override
            public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {
                String uuid = ssdpPacket.getUSN();
                String target = ssdpPacket.getST();
                String location = ssdpPacket.getLocation();
                Log.e("Device", uuid);
                Log.e("Device", target);
                Log.e("Device", location);
            }
        });
        controlPoint.search("ssdp:all");

    }
}
