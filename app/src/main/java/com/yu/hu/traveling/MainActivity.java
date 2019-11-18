package com.yu.hu.traveling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean equals = TextUtils.equals(null, "");
        Toast.makeText(this, "equals = " + equals, Toast.LENGTH_SHORT).show();
    }
}
