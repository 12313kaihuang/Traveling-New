package com.yu.hu.traveling;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yu.hu.common.activity.BaseActivity;
import com.yu.hu.traveling.databinding.ActivitySplashBinding;
import com.yu.hu.traveling.vm.SplashViewModel;


/**
 * create by hy on 2019/11/28 21:26
 * <p>
 * 闪屏页 & 启动页
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel(SplashViewModel.class);
        mDataBinding.setViewModel(mViewModel);

        Glide.with(this)
                .load("https://www.bing.com//th?id=OHR.AspenHiking_ZH-CN5769117414_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp")
                .into(mDataBinding.ivBg);

        mDataBinding.tvSkip.setOnClickListener(v -> toMainActivity());

        mViewModel.getRestTime().observe(this, resetTime -> {
            if (resetTime == 0) {
                toMainActivity();
            }
        });
    }

    /**
     * 跳转至MainActivity
     */
    private void toMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        //singTask
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.startInterval();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
