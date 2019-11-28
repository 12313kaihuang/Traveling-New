package com.yu.hu.traveling;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.yu.hu.common.activity.BaseActivity;
import com.yu.hu.traveling.databinding.ActivitySplashBinding;

/**
 * create by hy on 2019/11/28 21:26
 * <p>
 * 闪屏页
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //冷启动优化 把预先显示的backGround去掉
        setWindowBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        ToastUtils.showShort("show");

        new Handler(Looper.getMainLooper()).postDelayed(() -> startActivity(new Intent(mContext, MainActivity.class)), 2000);
    }

    @SuppressWarnings("SameParameterValue")
    private void setWindowBackgroundDrawable(@Nullable Drawable drawable) {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(drawable);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
