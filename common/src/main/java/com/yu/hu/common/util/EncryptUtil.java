package com.yu.hu.common.util;


import androidx.annotation.NonNull;

import com.blankj.utilcode.util.EncryptUtils;
import com.yu.hu.common.util.encrypt.IEncrypt;
import com.yu.hu.common.util.encrypt.RandomEncryptImpl;
import com.yu.hu.common.util.encrypt.SimpleEncryptImpl;


/**
 * create by hy on 2019/11/18 22:33
 * <p>
 * 加密解密相关
 *
 * @see SimpleEncryptImpl 基于Base64的简单加密/解密
 * @see RandomEncryptImpl 基于Base64的随机加密/解密
 * @see EncryptUtils  AndroidUtilCode
 */
@SuppressWarnings("unused")
public class EncryptUtil {

    /**
     * 默认的encrypt
     */
    private static IEncrypt sDEFAULT_ENCRYPT = getSimpleEncrypt();

    /**
     * 便于调用
     * 加密
     *
     * @see SimpleEncryptImpl
     */
    public static String encrypt(String value) {
        return sDEFAULT_ENCRYPT.encrypt(value);
    }

    /**
     * 便于调用
     * 解密
     *
     * @see SimpleEncryptImpl
     */
    @NonNull
    public static String decrypt(String value) {
        return sDEFAULT_ENCRYPT.decrypt(value);
    }


    /* ******************以下是单例类******************* */

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

}
