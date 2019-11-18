package com.yu.hu.common.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Hy on 2019/11/18 19:51
 * <p>
 * dialog基类
 **/
public abstract class BaseDialog extends Dialog {


    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * builder基类 属性都置为protected便于子类访问，getter方法由子类实现
     */
    protected static abstract class Builder {

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

        public abstract <T extends BaseDialog> T build();

        public void show() {
            build().show();
        }
    }
}
