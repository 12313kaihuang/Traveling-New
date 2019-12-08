package com.yu.hu.traveling;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

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

    //剩余时间的监听
    private Observer<Long> resetTimeObserver = resetTime -> {
        if (resetTime == 0) {
            toMainActivity();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel(SplashViewModel.class);
        mDataBinding.setViewModel(mViewModel);

        //bing每日一图：https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
        Glide.with(this)
                .load("https://www.bing.com/th?id=OHR.MarrakechMarket_ZH-CN5880133555_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp")
                .into(mDataBinding.ivBg);

        mDataBinding.tvSkip.setOnClickListener(v -> {
            //移除监听 否则剩余1s时点击跳过会出现两次跳转至MainActivity情况
            mViewModel.getRestTime().removeObserver(resetTimeObserver);
            toMainActivity();
        });

        mViewModel.getRestTime().observe(this, resetTimeObserver);
    }

    /**
     * 跳转至MainActivity
     */
    private void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        //singTask
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    @Override
    public void onBackPressed() {
        //屏蔽back键
    }
}
