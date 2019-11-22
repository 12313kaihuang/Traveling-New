package com.yu.hu.common.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.yu.hu.common.util.LogUtil;

/**
 * Created by Hy on 2019/11/18 19:51
 * <p>
 * dialog基类
 **/
@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public abstract class BaseDialog extends DialogFragment {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 一些初始化操作
     */
    @CallSuper
    protected void init() {
        mContext = getContext();
        mLayoutInflater = LayoutInflater.from(mContext);

        if (getArguments() != null) {
            initArguments(getArguments());
        }
    }

    /**
     * arguments的一些初始化操作
     */
    @CallSuper
    protected void initArguments(@NonNull Bundle arguments) {

    }

    {
        //https://developer.android.com/reference/android/app/DialogFragment#AlertDialog  官方文档
        //https://blog.csdn.net/xiaohui2015/article/details/79374394  为什么要用bundle传递数据
    }


}
