package com.tzs.antique.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberUtil {
    /**
     * @param phone 字符串类型的手机号
     *              传入手机号,判断后返回
     *              true为手机号,false相反
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
