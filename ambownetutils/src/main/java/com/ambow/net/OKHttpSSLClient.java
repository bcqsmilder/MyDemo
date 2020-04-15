package com.ambow.net;

import android.util.Log;

import com.ambow.base.NetApp;
import com.ambow.interceptor.ReadCookiesInterceptor;
import com.ambow.interceptor.SaveCookiesInterceptor;
import com.ambow.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 */

public class OKHttpSSLClient {
    private static OkHttpClient.Builder okHttpClientBuilder = null;
    private static Interceptor tokenToHeaderInterceptor;

    public static OkHttpClient getInstance() {
        if (okHttpClientBuilder == null) {
            synchronized (OKHttpSSLClient.class) {
                if (okHttpClientBuilder == null) {
                    initOKHttpSSLClient();
                }
            }
        }
        return okHttpClientBuilder.build();
    }

    //OKHttp默认支持
    public static void initOKHttpSSLClient() {


            okHttpClientBuilder = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
//                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .addInterceptor(new ReadCookiesInterceptor())
                    .addInterceptor(new SaveCookiesInterceptor())
                    .addInterceptor(getLogInterceptor())
                    .addInterceptor(getNetStatusInterceptor())
                    .cache(provideCache());
                    if(tokenToHeaderInterceptor!=null){
                        okHttpClientBuilder.addInterceptor(tokenToHeaderInterceptor);
                    }

    }

    public static void setTokenToHeaderInterceptor(Interceptor tokenToHeaderInterceptor) {
        OKHttpSSLClient.tokenToHeaderInterceptor = tokenToHeaderInterceptor;
    }

    /**
     * @return HttpLoggingInterceptor http日志拦截器
     */
    private static HttpLoggingInterceptor getLogInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
//                if(Constant.Debug){//debug状态打印log
                    Log.d("HttpLog", message);
//                }
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (!NetworkUtils.isConnected(NetApp.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected(NetApp.getAppContext())) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
    }

    public static Interceptor getHeaderIntercepter() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request() // originalRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .build());
            }
        };
    }

    public static Interceptor getTokenToHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request() // originalRequest
                        .newBuilder()
                        .addHeader("Authorization","6606ee6512964391a38bcfd4ff2bf057")
//                        .addHeader(NetApp.getAppContext().getLoginUser()!=null?"Authorization":"123",NetApp.getAppContext().getLoginUser()!=null?MyApplication.getInstance().getLoginUser().getToken():"")
//                        .addHeader(MyApplication.getInstance().getLoginUser()!=null?"Authorization":"123",MyApplication.getInstance().getLoginUser()!=null?"a8c8042b128e41078107e970c5951c8a":"")
                        .addHeader("splitKey","2") //安博爱学传2
                        .build());
            }
        };
    }


    public static Interceptor getNetStatusInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if(NetworkUtils.isConnected(NetApp.getAppContext().getApplicationContext())){
                    return chain.proceed(chain.request());
                }else {
                    throw new NoNetException();
                }

            }
        };
    }
    //设置缓存目录和缓存空间大小
    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(NetApp.getAppContext().getCacheDir(),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Log.e("cache", "Could not create Cache!");
        }
        return cache;
    }

}