package com.yu.hu.common.util;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.appcompat.view.StandaloneActionMode;

import com.blankj.utilcode.util.EncryptUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * create by hy on 2019/11/18 22:33
 * <p>
 * 加密解密相关
 *
 * @see EncryptUtils
 */
@SuppressWarnings("unused")
public class EncryptUtil {

    /**
     * 使用单例模式
     * <p>
     * 基于Base64加密
     *
     * @see #getSimpleEncrypt()
     * @see SimpleEncryptImpl
     */
    private static SimpleEncryptImpl sINSTANCE_SIMPLE;

    /**
     * 使用单例模式
     * <p>
     * 基于Base64加密
     *
     * @see #getRandomEncrypt()
     * @see RandomEncryptImpl
     */
    private static RandomEncryptImpl sINSTANCE_RANDOM;

    /**
     * 获取加密解密单例
     *
     * @see SimpleEncryptImpl
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
     * 获取加密解密单例
     *
     * @see RandomEncryptImpl
     */
    public static IEncrypt getRandomEncrypt() {
        if (sINSTANCE_RANDOM == null) {
            synchronized (EncryptUtil.class) {
                if (sINSTANCE_RANDOM == null) {
                    sINSTANCE_RANDOM = new RandomEncryptImpl();
                }
            }
        }
        return sINSTANCE_RANDOM;
    }

    /**
     * base64加密
     * 加密解密的一个简单实现：固定前缀
     *
     * @see IEncrypt
     */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    private static class SimpleEncryptImpl implements IEncrypt {

        private SimpleEncryptImpl() {

        }

        @Override
        public String encrypt(String value) {
            StringBuilder builder = new StringBuilder(Common.SIMPLE_ENCRYPT_PREFIX);
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

        @NonNull
        @Override
        public String decrypt(String value) {
            byte[] decryptBytes;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PREFIX.length()).getBytes(StandardCharsets.UTF_8);
            } else {
                try {
                    decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PREFIX.length()).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    decryptBytes = value.substring(Common.SIMPLE_ENCRYPT_PREFIX.length()).getBytes();
                }
            }

            try {
                return new String(Base64.decode(decryptBytes, Base64.DEFAULT));
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.warn(e.getMessage());
                return "";
            }
        }
    }


    /**
     * Base64加密方式
     * 随机一个前缀与字符
     */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    private static class RandomEncryptImpl implements IEncrypt {

        private static int PREFIX = 'A';
        private static int PREFIX_MAX = 'z' - PREFIX;

        private Random mRandom;

        RandomEncryptImpl() {
            mRandom = new Random(System.currentTimeMillis());
        }

        @Override
        public String encrypt(String value) {
            String result;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                result = Base64.encodeToString(value.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            } else {
                try {
                    result = Base64.encodeToString(value.getBytes("UTF-8"), Base64.DEFAULT);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    result = Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
                }
            }
            return random(result);
        }

        @NonNull
        @Override
        public String decrypt(String value) {
            byte[] decryptBytes;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decryptBytes = restore(value).getBytes(StandardCharsets.UTF_8);
            } else {
                try {
                    decryptBytes = restore(value).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    decryptBytes = restore(value).getBytes();
                }
            }

            try {
                return new String(Base64.decode(decryptBytes, Base64.DEFAULT));
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.warn(e.getMessage());
                return "";
            }
        }

        /**
         * @param encodeString 加密后的串
         * @return 二次加密后的串
         */
        private String random(String encodeString) {
            LogUtil.d("加密串：" + encodeString);
            int prefix = mRandom.nextInt(encodeString.length());
            prefix = prefix > PREFIX_MAX ? PREFIX_MAX : prefix;
            char pChar = (char) (PREFIX + prefix);
            LogUtil.d("prefix = " + prefix);
            StringBuilder sBuilder = new StringBuilder().append(pChar);
            for (int i = 0; i < encodeString.length(); i++) {
                sBuilder.append(encodeString.charAt(i));
                if (prefix == i) {
                    sBuilder.append(prefix % 10);
                }
            }
            return sBuilder.toString();
        }

        /**
         * @param decryptString 被二次加密的串
         * @return 待解密串
         */
        private String restore(String decryptString) {
            int prefix = decryptString.charAt(0) - PREFIX;
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < decryptString.length(); i++) {
                if (i == prefix - 1) continue;
                builder.append(decryptString.charAt(i));
            }
            LogUtil.d("str = " + builder.toString());
            return builder.toString();
        }
    }
}
