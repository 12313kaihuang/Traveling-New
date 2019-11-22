package com.yu.hu.common.dialog;


import android.text.TextUtils;

import androidx.fragment.app.FragmentManager;

/**
 * builder基类 属性都置为protected便于子类访问，getter方法由子类实现
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class DialogBuilder<T extends BaseDialog> {

    protected String showTag;

    protected FragmentManager fragmentManager;

    /**
     * 标题
     */
    protected String title;

    /**
     * 内容
     */
    protected String content;

    // positive  右边的按钮
    protected String positiveBtnText;

    //negative  左边的按钮
    protected String negativeBtnText;

    /**
     * 点击按钮后是否自动dismiss
     */
    protected boolean autoDismiss;

    public DialogBuilder(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public DialogBuilder(FragmentManager fragmentManager, String showTag) {
        this.showTag = showTag;
        this.fragmentManager = fragmentManager;
    }

    public abstract T build();

    public void show() {
        if (TextUtils.isEmpty(showTag)) {

        }

    }

}
