package com.freeter.common.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author yangxin-ryan
 */
public class Base64Util {

    // 字符串编码
    private static final String UTF_8 = "UTF-8";
    /**
     * 解密字符串
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    /**
     * 加密
     * @param inputData
     * @return
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println("加密"+Base64Util.encodeData("|00000asDF|ruanweijing|0000qwER|0000|0000df|2440000003"));
//        String enStr = Base64Util.encodeData("|00000asDF|ruanweijing|0000qwER|0000|0000df|2440000003");
//        System.out.println("解密"+Base64Util.decodeData(enStr));
//    }

}
