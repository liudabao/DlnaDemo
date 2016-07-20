package com.example.lenovo.dlnademo;

import android.util.Log;

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
        broadcastAddress  = InetAddress.getByName(SSDPConstants.ADDRESS);
        mSSDPSocket.joinGroup(broadcastAddress);
    }

    /* Used to send SSDP packet */
    public void send(String data) throws IOException {
        DatagramPacket dp = new DatagramPacket(data.getBytes(), data.length(),
                broadcastAddress,SSDPConstants.PORT);

        mSSDPSocket.send(dp);
    }

    /* Used to receive SSDP packet */
    public DatagramPacket receive() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        mSSDPSocket.receive(dp);
        return dp;
    }

    public void close() {
        if (mSSDPSocket != null) {
            mSSDPSocket.close();
        }
    }
}
