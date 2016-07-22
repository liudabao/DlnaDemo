package com.example.lenovo.dlnademo;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SSDPConstants {
    public static final String NEWLINE = "\n";

    public static final String ADDRESS = "239.255.255.250";
    public static final int PORT = 1900;

    /* Definitions of start line */
    public static final String SL_NOTIFY = "NOTIFY * HTTP/1.1";
    public static final String SL_MSEARCH = "M-SEARCH * HTTP/1.1";
    public static final String SL_OK = "HTTP/1.1 200 OK";

    /* Definitions of search targets */
    public static final String ST_ALL = "ST: ssdp:all";
    public static final String ST_RootDevice = "ST: upnp:rootdevice";
    public static final String ST_ContentDirectory = "ST: urn:schemas-upnp-org:service:ContentDirectory:1";
    public static final String ST_AVTransport = "ST: urn:schemas-upnp-org:service:AVTransport:1";
    public static final String ST_Product = "ST: urn:av-openhome-org:service:Product:1";
    public static final String ST_InternetDevice="ST: urn:schemas-upnp-org:device:InternetGatewayDevice:1";

    /* Definitions of notification sub type */
    public static final String NTS_ALIVE = "NTS:ssdp:alive";
    public static final String NTS_BYE = "NTS:ssdp:byebye";
    public static final String NTS_UPDATE = "NTS:ssdp:update";
}
