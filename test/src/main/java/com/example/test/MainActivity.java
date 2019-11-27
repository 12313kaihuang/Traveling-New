package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.yu.hu.common.activity.BaseActivity;
import com.yu.hu.common.dialog.LoadingDialog;
import com.yu.hu.common.entity.RequestResult;
import com.yu.hu.common.util.CrashHandler;
import com.yu.hu.common.util.EncryptUtil;
import com.yu.hu.common.util.LogUtil;
import com.yu.hu.common.util.ResourceUtil;
import com.yu.hu.common.util.ThreadPool;
import com.yu.hu.traveling.R;
import com.yu.hu.traveling.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashHandler.init();

        final TextView textView = findViewById(R.id.et_input);
        Button button = findViewById(R.id.encryptBtn);


        button.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(textView.getText())) {
                String encrypt = EncryptUtil.getRandomEncrypt().encrypt(textView.getText().toString());
                //String encrypt = Base64.encodeToString(textView.getText().toString().getBytes(), Base64.DEFAULT);
                LogUtil.d("加密后 = " + encrypt);
                String decrypt = EncryptUtil.getRandomEncrypt().decrypt(encrypt);
                //String decrypt = new String(Base64.decode(encrypt.getBytes(), Base64.DEFAULT));
                LogUtil.d("解密 = " + decrypt);
                Toast.makeText(MainActivity.this, "encrypt = " + decrypt, Toast.LENGTH_SHORT).show();
            }
        });

        mDataBinding.loadingBtn.setOnClickListener(v -> LoadingDialog.newInstance()
                .setContent(R.string.app_name)
                .setContent(mDataBinding.etInput.getText().toString())
                .setContentColorResource(R.color.black)
                .setProgressBarColorResource(R.color.colorAccent)
                .show(getSupportFragmentManager()));

        mDataBinding.test.setOnClickListener((v) -> {
            String result = ResourceUtil.readAssets2String("test.json");
            RequestResult<Student> requestResult = GsonUtils.fromJson(result,new TypeToken<RequestResult<Student>>(){}.getType());
            ToastUtils.showShort(requestResult.response.name);
        });


        mDataBinding.crashTestBtn.setOnClickListener(v -> {
            Context context = null;
            context.setTheme(0);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private static class Student {
        public long id;
        public String name;
        public int age;
    }
}
