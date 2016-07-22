package com.example.lenovo.dlnademo;

import android.util.Log;
import android.util.Xml;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPClientSocket {
    SocketAddress mSSDPMulticastGroup;
    MulticastSocket mSSDPSocket;
    InetAddress broadcastAddress;

    public SSDPClientSocket() throws IOException {
        mSSDPSocket = new MulticastSocket(); //Bind some random port for receiving datagram
        mSSDPSocket.setLoopbackMode(false);
        broadcastAddress  = InetAddress.getByName(SSDPConstants.ADDRESS);
        mSSDPSocket.joinGroup(broadcastAddress);
    }

    /* Used to send SSDP packet */
    public void send(String data) throws IOException {
        Log.e("send data", data);
        DatagramPacket dp = new DatagramPacket(data.getBytes("utf-8"), data.length(),
                broadcastAddress,SSDPConstants.PORT);

        mSSDPSocket.send(dp);
    }

    public void close() {
        if (mSSDPSocket != null) {
            mSSDPSocket.close();
        }
    }
}
