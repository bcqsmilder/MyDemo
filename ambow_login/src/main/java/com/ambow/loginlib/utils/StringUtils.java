package com.ambow.loginlib.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
//
//    /**
//     * 验证邮箱
//     * @param target
//     * @return
//     */
//    public final static boolean isValidEmail(CharSequence target) {
//        if (TextUtils.isEmpty(target)) {
//            return false;
//        } else {
//            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
//        }
//    }

//    /**
//     * 验证手机号
//     * @param target
//     * @return
//     */
//    public static final boolean isValidPhoneNumber(CharSequence target) {
//        if (TextUtils.isEmpty(target)) {
//            return false;
//        } else {
//            return android.util.Patterns.PHONE.matcher(target).matches();
//        }
//    }

    /**
     * 验证有效密码
     *
     * @param target
     * @return
     */
    public static final boolean isValidPassword(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return target.length() >= 6;
        }
    }

    /**
     * 是否是有效的验证码
     *
     * @param target
     * @return
     */
    public static final boolean isValidCaptcha(CharSequence target) {
        return isValidPassword(target);
    }


    /**
     * 验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhoneNumber(String phone) {

        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {

            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(phone);
            if (phone.substring(0, 1).equals("1") && phone.length() == 11 && isNum.matches()) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * 验证邮箱
     *
     * @param string
     * @return
     */
    public static boolean isValidEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }


    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Pattern pattern_1 = Pattern.compile("-?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        Matcher isNumint = pattern_1.matcher(str);
        if (!isNum.matches() && !isNumint.matches()) {
            return false;
        }
        return true;

    }


}
