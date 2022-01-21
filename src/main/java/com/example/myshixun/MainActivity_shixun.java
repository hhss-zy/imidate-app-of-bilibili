package com.example.myshixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myshixun.loginmain.loginActivity;
import com.example.myshixun.loginmain.RegisterActivity;


public class MainActivity_shixun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shixun);
    }

    public void onclick_shixun_login(View view) {
        Intent intent=new Intent(MainActivity_shixun.this, loginActivity.class);
        startActivity(intent);
   }

    public void onclick_shixun_zhuche(View view) {
        Intent intent=new Intent(MainActivity_shixun.this, RegisterActivity.class);
        startActivity(intent);
    }
}