package com.ambow.interceptor;


import com.ambow.utils.PreferenceUtil;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author: Wsir
 * *
 * @Date: 2018/5/4
 * *
 * Copyright (c) 2018 All Rights Reserved.
 */
public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            cookies.clear();
            for (String header : originalResponse.headers("Set-Cookie")) {
                if(header.contains("JSESSIONID")){
                    cookies.add(header);
                }

            }

            if(cookies.size() >= 1){
                PreferenceUtil.saveSetString("pref_cookies",cookies);
            }
        }

        return originalResponse;
    }



}
