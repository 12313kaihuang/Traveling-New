package com.yu.hu.common.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/**
 * Created by Hy on 2019/11/18 19:51
 * <p>
 * dialog基类
 **/
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        this(context, getThemeResId());
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * @return theme资源id
     */
    @StyleRes
    protected static int getThemeResId() {
        return 0;
    }


}
