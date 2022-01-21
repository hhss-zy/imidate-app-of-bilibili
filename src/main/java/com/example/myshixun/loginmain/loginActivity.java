package com.example.myshixun.loginmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshixun.R;
import com.example.myshixun.mainbody.Contentctivity;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.Login;
import com.example.myshixun.util.NetUtil;
import com.example.myshixun.util.Stringutil;
import com.example.myshixun.util.UserInformation;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {
    EditText editText_user,editText_password;
    TextView textView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);
        editText_user=findViewById(R.id.shixun_ed_user_dl);
        editText_password=findViewById(R.id.shixun_ed_password_dl);
        sharedPreferences=getSharedPreferences("date",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        textView=findViewById(R.id.tv_quzhuce);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onclick_shixun_login_main(View view) {

        String account=editText_user.getText().toString();
        String password1=editText_password.getText().toString();

        if(Stringutil.isempty(account)) {
            Toast.makeText(loginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
        }else if(Stringutil.isempty(password1)) {
            Toast.makeText(loginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }
        if (!Stringutil.isempty(account)&&!Stringutil.isempty(password1)){
            int phone=Integer.parseInt(account);
            Getlogin(phone,password1);

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void Getlogin(int phone,String password){
        Apiservice apiservice= NetUtil.getapiservice();
        Call<BaseModle<Login>> call=apiservice.login(phone,password);
        call.enqueue(new Callback<BaseModle<Login>>() {
            @Override
            public void onResponse(Call<BaseModle<Login>> call, Response<BaseModle<Login>> response) {
                Login data=response.body().getData();
                editor.putInt("phone",data.getPhone());
                editor.apply();
            if(response.code()==200) {
                Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();//提示框
                Intent intent=new Intent(loginActivity.this, Contentctivity.class);
                getuserdata();
                startActivity(intent);
            }


            }

            @Override
            public void onFailure(Call<BaseModle<Login>> call, Throwable t) {
                Log.e("login", "onFailure: "+t.getMessage() );
                Toast.makeText(loginActivity.this, "请使用注册账号密码", Toast.LENGTH_SHORT).show();//提示框
            }
        });
    }
    //获取用户数据
    public void getuserdata(){
        Apiservice apiservice= NetUtil.getapiservice();
        int p=sharedPreferences.getInt("phone",0);
        Call<BaseModle<UserInformation>> call=apiservice.getuserdata(p);
        call.enqueue(new Callback<BaseModle<UserInformation>>() {
            @Override
            public void onResponse(Call<BaseModle<UserInformation>> call, Response<BaseModle<UserInformation>> response) {

                Log.e("getuserdata", "onsucess: "+ response.body().getData());

                if(response.code()==200) {
                    UserInformation userInformation=response.body().getData();
                    editor.putString("f_username",userInformation.getUsername());
//                    Log.e("getuserdata", "onsucess: "+ userInformation.getUsername());
                    editor.putString("f_headimage",userInformation.getUserimage());
                    editor.putString("f_usersex",userInformation.getSex());
                    editor.putString("f_brithday",userInformation.getBrithday());
                    editor.apply();
                }


            }

            @Override
            public void onFailure(Call<BaseModle<UserInformation>> call, Throwable t) {
                Log.e("getuserdata", "onFailure: "+t.getMessage() );

            }
        });
    }
}