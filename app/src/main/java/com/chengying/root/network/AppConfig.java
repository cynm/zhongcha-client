package com.chengying.root.network;

import android.os.Environment;

import java.util.Map;


public class AppConfig {
    public static Map<String, String> userMap;
    public static Map<String, String> regeMap;
    public static String loginUrl = "LoginProcesser";
    public static String regiUrl = "RegesterProcesser";

    public static AppConfig app = new AppConfig();
    public static int TIMEOUT = 6000;
    public static String CashPath = Environment.getExternalStorageDirectory().getPath() + "/zhongcha/";
    public String ip = "192.168.1.168";
    public String httpAddress;
    private String httpPort;
    private String socketPort = "6000";

    public static AppConfig getInstance() {
        return app;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public String getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(String port) {
        this.socketPort = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHttpAddress() {
        return httpAddress;
    }

    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress;
    }


}
