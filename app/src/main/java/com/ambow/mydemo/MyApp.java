package com.ambow.mydemo;

import android.app.Application;

import com.ambow.base.NetApp;
import com.ambow.net.OKHttpSSLClient;

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        NetApp.init(this);
        NetApp.setBaseUrl("https://dev-open-api.ambow.com/");
        OKHttpSSLClient.initOKHttpSSLClient();
        OKHttpSSLClient.setTokenToHeaderInterceptor(new TokenToHeaderInterceptor());
    }

    public static MyApp getInstance() {
        if(instance!=null)
        return instance;
        else  return  new MyApp();
    }
}
