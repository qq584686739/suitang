//package com.suitang.utils;
//
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class MD5Util {
//	public static String md5(String string) {
//        byte[] hash = null;
//        try {
//            hash = MessageDigest.getInstance("MD5").digest(
//                    string.getBytes("UTF-8"));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        StringBuilder hex = new StringBuilder(hash.length * 2);
//        for (byte b : hash) {
//            if ((b & 0xFF) < 0x10)
//                hex.append("0");
//            hex.append(Integer.toHexString(b & 0xFF));
//        }
//        return hex.toString();
//    }
//}