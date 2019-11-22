package com.yu.hu.common.net;


import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.JsonObject;
import com.yu.hu.common.util.EncryptUtil;
import com.yu.hu.common.util.encrypt.IEncrypt;

/**
 * create by hy on 2019/11/18 21:21
 * <p>
 * 网络请求参数生成
 */
@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
public class ParamsGenerator implements IParamsGenerator {

    /**
     * 用于参数构建
     */
    protected JsonObject mJsonObject;

    /**
     * 用于参数加密
     */
    protected IEncrypt mIEncrypt;

    public ParamsGenerator() {
        mJsonObject = new JsonObject();
        mIEncrypt = EncryptUtil.getSimpleEncrypt();
    }

    @Override
    public ParamsGenerator add(String key, Object o) {
        mJsonObject.add(key, GsonUtils.getGson().toJsonTree(o));
        return this;
    }

    @Override
    public String generate() {
        return mJsonObject.toString();
    }

    @Override
    public String encrypt() {
        return mIEncrypt.encrypt(generate());
    }

}
