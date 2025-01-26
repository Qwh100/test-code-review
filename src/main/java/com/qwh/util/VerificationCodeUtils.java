package com.qwh.util;


import java.util.Random;

public class VerificationCodeUtils {

    private VerificationCodeUtils() {
    }

    /**
     * 获取4位验证码
     */
    public static String getVerification4Code() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 获取6位验证码
     */
    public static String getVerification6Code() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            Random r = new Random();
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }
}


