package com.yu.hu.common.dialog;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.yu.hu.common.databinding.DialogLoadingBinding;

/**
 * Created by Hy on 2019/11/22 12:51
 **/
public class LoadingDialog extends BaseDialog<LoadingDialog, DialogLoadingBinding> {

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    private LoadingDialog() {

    }

    @Override
    protected DialogLoadingBinding getDataBinding(@Nullable ViewGroup container) {
        return DialogLoadingBinding.inflate(mLayoutInflater, container, false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDataBinding.setLoadingText(content);
    }

    public LoadingDialog setContent(String content) {
        this.content = content;
        return this;
    }
}
