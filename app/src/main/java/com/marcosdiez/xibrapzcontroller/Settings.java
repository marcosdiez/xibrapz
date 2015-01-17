package com.marcosdiez.xibrapzcontroller;

/**
 * Created by Marcos on 17-Jan-15.
 */
public class Settings {
    public static String server_protocol = "http";
    public static String server = "xibrapz.webdanfe.com";
    public static int server_port = 80;
    public static String server_path = "get_data";

    public static int seconds_to_sleep_between_publish_attempt = 10;

    public static String server_header = server_protocol + "://" + server + ":" + server_port + "/" + server_path;
}
