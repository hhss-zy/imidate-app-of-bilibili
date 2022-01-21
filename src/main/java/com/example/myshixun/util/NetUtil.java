package com.example.myshixun.util;

import com.example.myshixun.netapi.Apiservice;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetUtil {
    public static Apiservice getapiservice() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Apiservice.base_url)
                .build();
        return retrofit.create(Apiservice.class);
    }
}
