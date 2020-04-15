package com.ambow.base;

import android.content.Context;

public class NetApp {
    public static String BaseUrl ;
    private static Context sAppContext;
    /**
     * 子模块和主模块需要共享全局上下文，故需要在app module初始化时传入
     */
    public static void init(Context appContext) {
        if(sAppContext == null) {
            sAppContext = appContext.getApplicationContext();
        }
    }
    public static Context getAppContext() {
        return sAppContext;
    }


    /**
     * 设置项目的根目录
     * @param baseUrl
     */
    public static void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    /**
     * 获取项目的根目录
     * @return
     */
    public static String getBaseUrl() {
        return BaseUrl;
    }

}
