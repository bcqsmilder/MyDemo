package com.ambow.loginlib.utils;

public class LoginLoader {

    /**
     * 点击验证码的监听
     */
    public interface CaptchaListener {
        void onPre();//点击时
        void onComplete(String phoneOrEmail);//60秒完成后
    }
    /**
     * 登录提交监听     */
    public interface SubmitListener {
        void onComplete(String... strings);
    }


    /**
     * 页面类型
     */
    public enum SubmitType {
        LOGIN, REGISTER, LOGIN_FAST, PASSWORD_FORGET, NORMAL
    }

}
