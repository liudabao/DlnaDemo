package com.example.lenovo.dlnademo;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPConstants {
    public static final String NEWLINE = "\r\n";

    public static final String ADDRESS = "239.255.255.250";
    public static final int PORT = 1900;

    /* Definitions of start line */
    public static final String SL_NOTIFY = "NOTIFY * HTTP/1.1";
    public static final String SL_MSEARCH = "M-SEARCH * HTTP/1.1";
    public static final String SL_OK = "HTTP/1.1 200 OK";

    /* Definitions of search targets */
    public static final String ST_RootDevice = "St: rootdevice";
    public static final String ST_ContentDirectory = "St: urn:schemas-upnp-org:service:ContentDirectory:1";
    public static final String ST_AVTransport = "St: urn:schemas-upnp-org:service:AVTransport:1";
    public static final String ST_Product = "St: urn:av-openhome-org:service:Product:1";

    /* Definitions of notification sub type */
    public static final String NTS_ALIVE = "NTS:ssdp:alive";
    public static final String NTS_BYE = "NTS:ssdp:byebye";
    public static final String NTS_UPDATE = "NTS:ssdp:update";
}
