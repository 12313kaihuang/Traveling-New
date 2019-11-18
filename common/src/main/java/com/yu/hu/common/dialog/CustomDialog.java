package com.yu.hu.common.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * create by hy on 2019/11/18 23:30
 */
@SuppressWarnings("unchecked")
public class CustomDialog extends BaseDialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder extends DialogBuilder {

        public Builder(Context context) {
            super(context);
        }

        @Override
        public <T extends BaseDialog> T build() {
            return null;
        }
    }

}
