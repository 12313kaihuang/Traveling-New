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
        super.onCreate(savedInstanceState);
        ToastUtils.showShort("show");

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(mContext, MainActivity.class));
            //finish();
        }, 2000);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
