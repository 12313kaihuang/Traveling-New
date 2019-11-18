package com.yu.hu.common.util;

/**
 * create by hy on 2019/11/18 22:33
 * <p>
 * 解密解密接口化
 * 具体实现方式由子类决定
 */
public interface IEncrypt {

    /**
     * 加密
     *
     * @param value 需加密的字符串
     * @return 加密后的字符串
     */
    String encrypt(String value);

    /**
     * 解密
     *
     * @param value 待解密字符串
     * @return 解密后的字符串
     */
    String decrypt(String value);

}
