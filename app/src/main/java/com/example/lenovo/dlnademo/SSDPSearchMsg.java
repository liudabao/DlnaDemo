package com.example.lenovo.dlnademo;

import android.util.Log;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPSearchMsg {

    static final String HOST = "HOST: " + SSDPConstants.ADDRESS + ":" + SSDPConstants.PORT;
    static final String MAN = "MAN: \"ssdp:discover\"";
    static final String NEWLINE = "\r\n";

    int mMX = 5;    /* seconds to delay response */
    String mST;     /* Search target */

    public SSDPSearchMsg(String ST) {
        //Log.e("msg",ST);
        mST = ST;
    }

    public int getmMX() {
        return mMX;
    }

    public void setmMX(int mMX) {
        this.mMX = mMX;
    }

    public String getmST() {
        return mST;
    }

    public void setmST(String mST) {
        this.mST = mST;
    }

    @Override
    public String toString() {
       // Log.e("msg0","start string");
        StringBuilder content = new StringBuilder();
        content.append(SSDPConstants.SL_MSEARCH).append(NEWLINE);
        content.append(MAN).append(NEWLINE);
        content.append("MX: " + mMX).append(NEWLINE);
        content.append(HOST).append(NEWLINE);
        content.append(mST).append(NEWLINE);
        return content.toString();

    }
}
