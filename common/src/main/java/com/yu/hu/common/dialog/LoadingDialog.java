package com.yu.hu.common.dialog;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.yu.hu.common.R;
import com.yu.hu.common.databinding.DialogLoadingBinding;

/**
 * Created by Hy on 2019/11/22 12:51
 **/
@SuppressWarnings("unused")
public class LoadingDialog extends BaseDialog<LoadingDialog, DialogLoadingBinding> {

    /**
     * progressBar的颜色
     */
    @ColorRes
    private int progressBarColor;

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    private LoadingDialog() {
        //初始化值
        this.contentColor = R.color.colorPrimary;
        this.progressBarColor = R.color.colorPrimary;
    }

    @Override
    protected DialogLoadingBinding getDataBinding(@Nullable ViewGroup container) {
        return DialogLoadingBinding.inflate(mLayoutInflater, container, false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDataBinding.setLoadingText(content);
        mDataBinding.tvContent.setTextColor(getResources().getColor(contentColor));
        setProgressBarColor();
    }

    //api 21  Android 5.0 及以上才有
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setProgressBarColor() {
        //状态
        int[][] states = new int[2][];
        //按下
        states[0] = new int[]{android.R.attr.state_pressed};
        //默认
        states[1] = new int[]{};

        int barColor = getResources().getColor(progressBarColor);
        //状态对应颜色值（按下，默认）
        int[] colors = new int[]{barColor, barColor};
        ColorStateList colorList = new ColorStateList(states, colors);

        mDataBinding.progressBar.setIndeterminateTintList(colorList);
    }

    /* *******************对外暴露的方法******************** */

    /**
     * 设置提示内容
     *
     * @param content content
     */
    public LoadingDialog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 统一设置progressBar及文字的颜色
     *
     * @param displayColor color
     */
    public LoadingDialog setDisplayColor(@ColorRes int displayColor) {
        this.contentColor = displayColor;
        this.progressBarColor = displayColor;
        return this;
    }

    /**
     * content颜色
     *
     * @param contentColor color
     */
    public LoadingDialog setContentColor(@ColorRes int contentColor) {
        this.contentColor = contentColor;
        return this;
    }

    /**
     * progressBar 颜色
     *
     * @param progressBarColor color
     */
    public LoadingDialog setProgressBarColor(@ColorRes int progressBarColor) {
        this.progressBarColor = progressBarColor;
        return this;
    }

}
