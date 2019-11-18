package com.example.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yu.hu.traveling.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean equals = TextUtils.equals(null, "");
        Toast.makeText(this, "equals = " + equals, Toast.LENGTH_SHORT).show();
    }
}
