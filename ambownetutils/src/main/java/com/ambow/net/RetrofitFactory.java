package com.ambow.net;


import com.ambow.base.NetApp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */

public class RetrofitFactory {
    private static final String Host = NetApp.getBaseUrl();

    private static Retrofit HostRetrofit = null;
    private static Retrofit TestRetrofit = null;

    public static Retrofit getHostRetrofit(){
        if(HostRetrofit == null){
            synchronized (RetrofitFactory.class){
                if(HostRetrofit == null){
                    HostRetrofit = creatRetrofit(Host);
                }
            }
        }
        return HostRetrofit;
    }


    private static Retrofit creatRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .client(OKHttpSSLClient.getInstance())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
