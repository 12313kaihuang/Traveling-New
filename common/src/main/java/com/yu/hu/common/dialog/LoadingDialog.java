package com.yu.hu.common.dialog;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yu.hu.common.databinding.DialogLoadingBinding;

/**
 * Created by Hy on 2019/11/22 12:51
 **/
public class LoadingDialog extends BaseDialog {

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    private LoadingDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogLoadingBinding binding =
                DialogLoadingBinding.inflate(mLayoutInflater, container, false);

        return binding.getRoot();
    }

    public LoadingDialog setContent(String content) {
        return this;
    }
}
