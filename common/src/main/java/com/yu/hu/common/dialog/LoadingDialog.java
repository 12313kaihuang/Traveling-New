package com.yu.hu.common.dialog;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.yu.hu.common.databinding.DialogLoadingBinding;

/**
 * Created by Hy on 2019/11/22 12:51
 **/
public class LoadingDialog extends BaseDialog {


    public static LoadingDialog newInstance(Builder builder) {
        LoadingDialog dialog = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BUILDER, builder);
        dialog.setArguments(bundle);
        return dialog;
    }

    private LoadingDialog() {

    }

    public void show(FragmentManager fragmentManager){
        show(fragmentManager,"22");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogLoadingBinding binding =
                DialogLoadingBinding.inflate(mLayoutInflater, container, false);
        Builder builder = getArguments().getParcelable(KEY_BUILDER);
        if (builder != null) {
            binding.setBuilder(builder);
        }
        return binding.getRoot();
    }

    public static class Builder implements Parcelable {

        private String content = "加载中...";

        public Builder() {

        }

        protected Builder(Parcel in) {
            content = in.readString();
        }

        public static final Creator<Builder> CREATOR = new Creator<Builder>() {
            @Override
            public Builder createFromParcel(Parcel in) {
                return new Builder(in);
            }

            @Override
            public Builder[] newArray(int size) {
                return new Builder[size];
            }
        };

        /**
         * 设置提示内容
         */
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public String getContent() {
            return content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(content);
        }

        public LoadingDialog build() {
            return LoadingDialog.newInstance(this);
        }


    }


}
