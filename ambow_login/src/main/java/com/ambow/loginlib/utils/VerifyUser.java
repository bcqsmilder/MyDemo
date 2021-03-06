package com.ambow.loginlib.utils;

public abstract class VerifyUser {
    boolean isShowToast = false;
    protected abstract boolean verifyPhoneOrEmail(String phoneOrEmail);//验证手机号
    protected abstract boolean verifyPassword(String password);//验证密码
    protected abstract boolean verifyCaptcha(String captcha);//验证验证码
    public abstract void setIsShowToast(boolean isShowToast);

    public boolean checkUser(LoginLoader.SubmitType inputType, String... strings) {
        if (strings == null) return false;
        switch (inputType) {
            case LOGIN:
                return verifyPhoneOrEmail(strings[0])
                        && verifyPassword(strings[1]);
            case REGISTER:
                return verifyPhoneOrEmail(strings[0])
                        && verifyCaptcha(strings[1])
                        && verifyPassword(strings[2]);
            case LOGIN_FAST:

            case PASSWORD_FORGET:
                return verifyPhoneOrEmail(strings[0])
                        && verifyCaptcha(strings[1]);
            case NORMAL:
            default:
                break;
        }
        return false;
    }

}

