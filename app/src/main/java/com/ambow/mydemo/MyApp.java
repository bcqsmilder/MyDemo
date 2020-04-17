package com.ambow.mydemo;

import android.app.Application;

import com.ambow.base.NetApp;
import com.ambow.live.appvideo.PolyvUserClient;
import com.ambow.net.OKHttpSSLClient;
import com.easefun.polyv.businesssdk.vodplayer.PolyvVodSDKClient;
import com.easefun.polyv.cloudclass.config.PolyvLiveSDKClient;
import com.easefun.polyv.foundationsdk.log.PolyvCommonLog;
import com.easefun.polyvsdk.PolyvDownloaderManager;
import com.easefun.polyvsdk.PolyvSDKClient;
import com.easefun.polyvsdk.screencast.PolyvScreencastHelper;

public class MyApp extends Application {
    private static MyApp instance;
    /** 加密秘钥 */
    private String aeskey = "VXtlHmwfS2oYm0CZ";
    /** 加密向量 */
    private String iv = "2u9gDPKdX6GyQJKU";
    /** SDK加密串，可以在点播后台获取 */
    private String config = "";
    @Override
    public void onCreate() {
        super.onCreate();
        NetApp.init(this);
        NetApp.setBaseUrl("https://dev-open-api.ambow.com/");
        OKHttpSSLClient.initOKHttpSSLClient();
        OKHttpSSLClient.setTokenToHeaderInterceptor(new TokenToHeaderInterceptor());


        initPolyvCilent();//点播的配置
        initScreencast();//点播的配置
            // Normal app init code...
        PolyvCommonLog.setDebug(true);
        PolyvLiveSDKClient liveSDKClient = PolyvLiveSDKClient.getInstance();
        liveSDKClient.initContext(this);
        liveSDKClient.enableHttpDns(false);

        PolyvVodSDKClient client = PolyvVodSDKClient.getInstance();
        //使用SDK加密串来配置
        client.setConfig(config, aeskey, iv);



    }

    public static MyApp getInstance() {
        if(instance!=null)
        return instance;
        else  return  new MyApp();
    }
    private void initScreencast() {
        //TODO appId和appSecret需与包名绑定，获取方式请咨询Polyv技术支持
        PolyvScreencastHelper.init("fknhher6d3", "d7d4ad9add8d47448b53026224fe40ff");
        //初始化单例
        PolyvScreencastHelper.getInstance(this);
    }

    public void initPolyvCilent() {
        //网络方式取得SDK加密串，（推荐）
        //网络获取到的SDK加密串可以保存在本地SharedPreference中，下次先从本地获取
//		new LoadConfigTask().execute();
        PolyvSDKClient client = PolyvSDKClient.getInstance();
        // 打开多用户开关，设置用户id，根据学员账号进行设置
//		openMultiAccount();

        //使用SDK加密串来配置
        client.settingsWithConfigString(config, aeskey, iv);
        //初始化SDK设置
        client.initSetting(getApplicationContext());
        //启动Bugly
        client.initCrashReport(getApplicationContext());
        //启动Bugly后，在学员登录时设置学员id
//		client.crashReportSetUserId(userId);
        initDownloadDir();
        // 设置下载队列总数，多少个视频能同时下载。(默认是1，设置负数和0是没有限制)
        PolyvDownloaderManager.setDownloadQueueCount(1);
    }


    private void initDownloadDir() {
        if(PolyvSDKClient.getInstance().isMultiDownloadAccount()){
            // TODO: 2019/4/16 accountid 填入登录用户的id
            PolyvUserClient.getInstance().login("0c6cb553d6",this);
        }else{
            PolyvUserClient.getInstance().initDownloadDir(this);
        }
    }
}
