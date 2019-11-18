package com.yu.hu.common.util;

import android.os.Build;
import android.util.Base64;

import com.blankj.utilcode.util.EncryptUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * create by hy on 2019/11/18 22:33
 * <p>
 * 加密解密相关
 *
 * @see EncryptUtils
 */
public class EncryptUtil {

    /**
     * 使用单例模式
     *
     * @see SimpleEncryptImpl
     */
    private static SimpleEncryptImpl sINSTANCE_SIMPLE;

    /**
     * 获取加密解密单例
     */
    public static IEncrypt getSimpleEncrypt() {
        if (sINSTANCE_SIMPLE == null) {
            synchronized (EncryptUtil.class) {
                if (sINSTANCE_SIMPLE == null) {
                    sINSTANCE_SIMPLE = new SimpleEncryptImpl();
                }
            }
        }
        return sINSTANCE_SIMPLE;
    }

    /**
     * 加密解密的一个简单实现类
     * base64加密
     *
     * @see IEncrypt
     */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    private static class SimpleEncryptImpl implements IEncrypt {

        private SimpleEncryptImpl() {

        }

        @Override
        public String encrypt(String value) {
            StringBuilder builder = new StringBuilder(Common.SIMPLE_ENCRYPT_PRE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                builder.append(Base64.encodeToString(value.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
            } else {
                try {
                    builder.append(Base64.encodeToString(value.getBytes("UTF-8"), Base64.DEFAULT));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    builder.append(Base64.encodeToString(value.getBytes(), Base64.DEFAULT));
                }
            }
            return builder.toString();
        }

        @Override
        public String decrypt(String value) {
            byte[] decryptBytes;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PRE.length()).getBytes(StandardCharsets.UTF_8);
            } else {
                try {
                    decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PRE.length()).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PRE.length()).getBytes();
                }
            }
            return Base64.encodeToString(decryptBytes, Base64.DEFAULT);
        }
    }
}
