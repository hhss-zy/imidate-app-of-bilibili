package com.example.myshixun.mainbody.mepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshixun.R;
import com.example.myshixun.adapter.releasepage_getimage_adapter;
import com.example.myshixun.loginmain.RegisterActivity;
import com.example.myshixun.loginmain.loginActivity;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.release.Eventstring;
import com.example.myshixun.release.releaseActivity;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.Login;
import com.example.myshixun.util.NetUtil;
import com.example.myshixun.util.UserInformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class modify_informationActivity extends AppCompatActivity {

    @BindView(R.id.information_name)
    RelativeLayout relativeLayout_name;

    @BindView(R.id.information_image_re)
    RelativeLayout relativeLayout_image;

    @BindView(R.id.information_date)
    RelativeLayout relativeLayout_date;

    @BindView(R.id.information_sex)
    RelativeLayout relativeLayout_sex;

    @BindView(R.id.information_showname_text)
    TextView textView_name;

    @BindView(R.id.information_date_text)
    TextView textView_date;

    @BindView(R.id.information_sex_text)
    TextView textView_sex;

    @BindView(R.id.login_out)
    TextView textView_loginout;

    @BindView(R.id.information_back)
    ImageView imageView_back;

    @BindView(R.id.information_image_circle)
    CircleImageView circleImageView_headimage;

    int Year=2000;
    int Month,Day;
    private String[] sexarry=new String[]{"保密","男","女"};

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_information);
        //eventbus
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        sharedPreferences=getSharedPreferences("date",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String username=sharedPreferences.getString("f_username","");
        String userimage=sharedPreferences.getString("f_headimage","");
        String sex=sharedPreferences.getString("f_usersex","");
        String brithday=sharedPreferences.getString("f_brithday","");

        textView_name.setText(username);
        textView_sex.setText(sex);
        textView_date.setText(brithday);
        Glide.with(this).load(Apiservice.base_url+userimage).into(circleImageView_headimage);
        //name
        relativeLayout_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(modify_informationActivity.this, modifynameActivity.class);
                startActivity(intent);
            }
        });
     //日期
        relativeLayout_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(modify_informationActivity.this,dateSetListener,Year,Month,Day).show();
            }
        });

        //sex
        relativeLayout_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(modify_informationActivity.this);
                builder.setSingleChoiceItems(sexarry, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView_sex.setText(sexarry[which]);
                        editor.putString("sex",sexarry[which]);
                        editor.apply();
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        //image
        relativeLayout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new headimagedialog(modify_informationActivity.this, "头像选择", "取消", new headimagedialog.DialogListener() {
                    @Override
                    public void gototakeipone() {

                    }

                    @Override
                    public void gotoimagebase() {
                        Intent intent=new Intent(modify_informationActivity.this,modifyheadimageActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void gotoimagerondom() {

                    }

                    @Override
                    public void cannel() {

                    }
                }).show();
            }
        });
        //return
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaduser();
                uploadfile();
                finish();
            }
        });
        //登出
        textView_loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(modify_informationActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });

    }
    //修改名称
    @Subscribe(threadMode=ThreadMode.MAIN,sticky=true)
    public void getname(Eventstring eventstring){
        switch (eventstring.getCode()){
            case 1: Glide.with(this).load(eventstring.getShowimage()).into(circleImageView_headimage);
            editor.putString("userimage",eventstring.getShowimage());
            editor.apply();
            break;
            case 2: textView_name.setText(eventstring.getShowimage());
            editor.putString("username",eventstring.getShowimage());
            editor.apply();
            break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

//得到时间
    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Year=year;
            Month=month;
            Day=dayOfMonth;
            textView_date.setText(new StringBuffer().append(Year).append("-").append(Month+1).append("-").append(Day).append("").toString());
            editor.putString("brithday",new StringBuffer().append(Year).append("-").append(Month+1).append("-").append(Day).append("").toString());
            editor.apply();
        }
    };
    //上传数据
    public void uploaduser(){
        Apiservice apiservice= NetUtil.getapiservice();
        Integer phone= sharedPreferences.getInt("phone",0);
        String username=sharedPreferences.getString("username","");
        String sex=sharedPreferences.getString("sex","");
        String brithday=sharedPreferences.getString("brithday","");
        UserInformation userInformation=new UserInformation(phone,username,sex,brithday);
        Call<BaseModle<UserInformation>> call=apiservice.userdata(userInformation);
        call.enqueue(new Callback<BaseModle<UserInformation>>() {
            @Override
            public void onResponse(Call<BaseModle<UserInformation>> call, Response<BaseModle<UserInformation>> response) {
                if (response.code()==200){

                }
            }

            @Override
            public void onFailure(Call<BaseModle<UserInformation>> call, Throwable t) {
                Log.e("uploaduser", "onFailure: "+t.getMessage() );
//                Toast.makeText(modify_informationActivity.this,"失败，无该手机号",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //文件上传
    public void uploadfile(){
        String path=sharedPreferences.getString("userimage","");
        Integer phone= sharedPreferences.getInt("phone",0);
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody idbody=RequestBody.create(null,phone+"");
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Apiservice mapiservice = NetUtil.getapiservice();
        Call<BaseModle<String>> call = mapiservice.uploaduserfile(part,idbody);
        call.enqueue(new Callback<BaseModle<String>>() {
            @Override
            public void onResponse(Call<BaseModle<String>> call, Response<BaseModle<String>> response) {
                ResponseBody errorbody = response.errorBody();
                if (errorbody != null) {
                    try {
                        String errer = errorbody.string();
                        Log.e("errorbody", errer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.code()== 200) {
//                        Toast.makeText(modify_informationActivity.this, response.body().getMes(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseModle<String>> call, Throwable t) {
                Log.e("errorbody", t.getMessage());
            }
        });
    }
}