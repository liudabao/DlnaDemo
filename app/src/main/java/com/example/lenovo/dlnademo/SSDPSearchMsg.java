package com.example.lenovo.dlnademo;

import android.util.Log;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPSearchMsg {

    static final String HOST = "Host: " + SSDPConstants.ADDRESS + ":" + SSDPConstants.PORT;
    static final String MAN = "Man: \"ssdp:discover\"";
    static final String NEWLINE = "\r\n";

    int mMX = 3;    /* seconds to delay response */
    String mST;     /* Search target */

    public SSDPSearchMsg(String ST) {
        Log.e("msg","start");
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
        Log.e("msg0","tostring");
        StringBuilder content = new StringBuilder();
        content.append(SSDPConstants.SL_MSEARCH).append(NEWLINE);
        Log.e("msg1",content.toString());
        content.append(MAN).append(NEWLINE);
        Log.e("msg2",content.toString());
        content.append("Mx: " + mMX).append(NEWLINE);
        Log.e("msg3",content.toString());
        content.append(HOST).append(NEWLINE);
        Log.e("msg4",content.toString());
        content.append(mST).append(NEWLINE);
        Log.e("msg5",content.toString());
        content.append(NEWLINE);
        Log.e("msg6",content.toString());
        return content.toString();
    }
}
