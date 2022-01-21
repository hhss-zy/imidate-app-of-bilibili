package com.example.myshixun.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myshixun.R;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.Login;
import com.example.myshixun.util.NetUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button button;
    EditText editText_account,editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2);
        editText_account=findViewById(R.id.shixun_ed_user);
        editText_password=findViewById(R.id.shixun_ed_password);
    }

    public void btn_zhuce(View view) {
        rigister();

    }
    public void rigister(){
        Apiservice apiservice= NetUtil.getapiservice();
        String s =editText_account.getText().toString().trim();
        Integer phone=Integer.parseInt(s);
        Login login=new Login(phone,editText_password.getText().toString().trim());
        Call<BaseModle<Login>> call=apiservice.register(login);
        call.enqueue(new Callback<BaseModle<Login>>() {
            @Override
            public void onResponse(Call<BaseModle<Login>> call, Response<BaseModle<Login>> response) {
                if (response.code()==200){
                    Intent intent=new Intent(RegisterActivity.this, loginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseModle<Login>> call, Throwable t) {
                Log.e("register", "onFailure: "+t.getMessage() );
                Toast.makeText(RegisterActivity.this,"注册失败，已拥有该手机号",Toast.LENGTH_SHORT).show();
            }
        });
    }
}