package com.yu.hu.common.util;

import android.util.Log;

/**
 * create by hy on 2019/11/18 23:22
 * <p>
 * logUtil
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class LogUtil {

    private static final boolean debug = Common.DEBUG_LOG;

    private static final String TAG = LogUtil.class.getSimpleName();

    public static void i(String s) {
        i(TAG, s);
    }

    public static void d(String s) {
        d(TAG, s);
    }

    public static void w(String s) {
        w(TAG, s);
    }

    public static void e(String s) {
        e(TAG, s);
    }

    /**
     * 不受{@link #debug}控制
     */
    public static void a(String s) {
        a(TAG, s);
    }

    public static void i(final String tag, String s) {
        if (debug) {
            Log.i(tag, s);
        }
    }

    public static void d(final String tag, String s) {
        if (debug) {
            Log.d(tag, s);
        }
    }

    public static void w(final String tag, String s) {
        if (debug) {
            Log.w(tag, s);
        }
    }

    public static void e(final String tag, String s) {
        if (debug) {
            Log.e(tag, s);
        }
    }

    public static void a(final String tag, String s) {
        Log.w(tag, s);
    }

}
