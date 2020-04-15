package com.ambow.mydemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenToHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

                return chain.proceed(chain.request() // originalRequest
                        .newBuilder()
                        .addHeader("Authorization","6606ee6512964391a38bcfd4ff2bf057")
//                        .addHeader(NetApp.getAppContext().getLoginUser()!=null?"Authorization":"123",NetApp.getAppContext().getLoginUser()!=null?MyApplication.getInstance().getLoginUser().getToken():"")
//                        .addHeader(MyApplication.getInstance().getLoginUser()!=null?"Authorization":"123",MyApplication.getInstance().getLoginUser()!=null?"a8c8042b128e41078107e970c5951c8a":"")
                        .addHeader("splitKey","2") //安博爱学传2
                        .build());

        };

}
