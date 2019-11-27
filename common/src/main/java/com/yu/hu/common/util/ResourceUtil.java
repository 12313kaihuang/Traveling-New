package com.yu.hu.common.util;


import com.blankj.utilcode.util.ResourceUtils;


/**
 * Created by Hy on 2019/11/18 11:52
 * <p>
 * 资源相关Util
 *
 * @see ResourceUtils
 **/
@SuppressWarnings("unused")
public class ResourceUtil {

    /**
     * 从asserts从读取模拟数据
     *
     * @see ResourceUtils#readAssets2List(String)
     */
    public static String readAssets2String(final String assetsFilePath) {
        return ResourceUtils.readAssets2String(assetsFilePath);
    }
}
