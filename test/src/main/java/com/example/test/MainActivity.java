package com.example.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yu.hu.common.util.EncryptUtil;
import com.yu.hu.common.util.LogUtil;
import com.yu.hu.traveling.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView textView = findViewById(R.id.et_input);
        Button button = findViewById(R.id.encryptBtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(textView.getText())) {
                    String encrypt = EncryptUtil.getRandomEncrypt().encrypt(textView.getText().toString());
                    //String encrypt = Base64.encodeToString(textView.getText().toString().getBytes(), Base64.DEFAULT);
                    LogUtil.d("加密后 = " + encrypt);
                    String decrypt = EncryptUtil.getRandomEncrypt().decrypt(encrypt);
                    //String decrypt = new String(Base64.decode(encrypt.getBytes(), Base64.DEFAULT));
                    LogUtil.d("解密 = " + decrypt);
                    Toast.makeText(MainActivity.this, "encrypt = " + decrypt, Toast.LENGTH_SHORT).show();
                }
            }
        });


        boolean equals = TextUtils.equals(null, "");

    }
}
