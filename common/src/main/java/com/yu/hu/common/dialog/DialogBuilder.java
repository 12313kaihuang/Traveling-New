package com.yu.hu.common.dialog;


import android.content.Context;

/**
 * builder基类 属性都置为protected便于子类访问，getter方法由子类实现
 */
@SuppressWarnings("unused")
public abstract class DialogBuilder {

    protected Context context;

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

    public DialogBuilder(Context context) {
        this.context = context;
    }

    public abstract <T extends BaseDialog> T build();

    public void show() {
        build().show();
    }
}
