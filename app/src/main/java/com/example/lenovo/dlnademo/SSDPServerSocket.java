package com.example.lenovo.dlnademo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPServerSocket {
    SocketAddress mSSDPMulticastGroup;
    MulticastSocket mSSDPSocket;
    InetAddress broadcastAddress;

    public SSDPServerSocket() throws IOException {
        mSSDPSocket = new MulticastSocket(SSDPConstants.PORT); //Bind some random port for receiving datagram
        broadcastAddress  = InetAddress.getByName(SSDPConstants.ADDRESS);
        mSSDPSocket.joinGroup(broadcastAddress);
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
