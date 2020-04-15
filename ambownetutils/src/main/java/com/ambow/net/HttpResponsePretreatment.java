package com.ambow.net;

import com.ambow.Bean.HttpResponse;


/**
 *
 */

public class HttpResponsePretreatment {

    public static boolean pretreatNoToast(HttpResponse response) {
        if (response == null) {
            return false;
        }
        return response.isSuccess();
    }


//    public static boolean pretreatToast(HttpResponse response) {
//        //用户未登录
//        if(response!=null&&response.getCode()!=null&&response.getCode()==-1){
//            MyApplication.getInstance().setLogined(false,null);
//            PreferenceUtil.removeString("token");
//            PreferenceUtil.removeString("phone");
//            PreferenceUtil.removeString("headerUrl");
//            PreferenceUtil.removeString("name");
//            PreferenceUtil.removeString("grade");
//            PreferenceUtil.removeString("gender");
//            PreferenceUtil.removeString("birthday");
//            PreferenceUtil.removeString("wechatId");
//            Intent intent_login = new Intent(MyApplication.getInstance().getApplicationContext(), LoginActivity.class);
//            intent_login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApplication.getInstance().startActivity(intent_login);
//            return false;
//        }
//        if (response == null || !response.isSuccess() ) {
//            CustomToast.showShortTimgText(response.getMessage());
//            return false;
//        }
//        return response.isSuccess();
//    }
}
