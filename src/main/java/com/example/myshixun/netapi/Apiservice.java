package com.example.myshixun.netapi;

import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.Login;
import com.example.myshixun.util.UserInformation;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;


public interface Apiservice {

    public static final String base_url = "http://192.168.0.219:8082/shixun_czy/api/";
    public static final String api_baidu = "https://www.bilibili.com/";
    //登录
    @POST("shixun/login")
    @FormUrlEncoded
    Call<BaseModle<Login>> login(@Field("phone") int phone, @Field("password") String password);
    //访问外部网站
    @POST
    Call<BaseModle> outsideweb(@Url String str);
    //注册
    @Headers("Content-type: application/json")
    @POST("shixun/register")
    Call<BaseModle<Login>> register(@Body Login login);
    //得到视频data
    @GET("shixun/videodata")
    Call<BaseModle<AppInformation>> videodata();
    //上传用户data
    @Headers("Content-type: application/json")
    @POST("shixun/uploaduser")
    Call<BaseModle<UserInformation>> userdata(@Body UserInformation UserInformation);
    //上传用户图片文件
    @POST("shixun/uploaduserfile")
    @Multipart
    Call<BaseModle<String>> uploaduserfile(@Part MultipartBody.Part file, @Part("phone") RequestBody requestBody);
    //得到用户信息
    @POST("shixun/getuserdata")
    @FormUrlEncoded
    Call<BaseModle<UserInformation>> getuserdata(@Field("phone") int phone);
}