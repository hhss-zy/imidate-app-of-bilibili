package com.example.myshixun.tab_viewpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myshixun.R;
import com.example.myshixun.adapter.firstpage_dy_adapter_fromlistfragment;
import com.example.myshixun.mainbody.VideoplayActivity;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.NetUtil;
import com.example.myshixun.util.basemodle1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listfragment extends Fragment {
    private View view;
    private TextView textView;
    RecyclerView recyclerView;
//    int[] iconimage={R.drawable.dls,R.drawable.meiko,R.drawable.pm,R.drawable.b_main,};
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=getLayoutInflater().inflate(R.layout.listfragment,null,false);
        }

        getvideo();
        return view;
    }
    public void getvideo(){
        Apiservice apiservice= NetUtil.getapiservice();
        Call<BaseModle<AppInformation>> call=apiservice.videodata();
        call.enqueue(new Callback<BaseModle<AppInformation>>() {
            @Override
            public void onResponse(Call<BaseModle<AppInformation>> call, Response<BaseModle<AppInformation>> response) {
//                Log.e("video", "suceess: "+response.body().getDatas() );
                List<AppInformation> datas=response.body().getDatas();

                recyclerView=view.findViewById(R.id.fristpage_recyclerview_dy);
                //瀑布布局
                StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);//确定布局
                recyclerView.setAdapter(new firstpage_dy_adapter_fromlistfragment(datas, getContext(), new firstpage_dy_adapter_fromlistfragment.Onclickimage() {
                    @Override
                    public void turnvideo(int p) {
                        Intent intent=new Intent(getContext(),VideoplayActivity.class);
                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<BaseModle<AppInformation>> call, Throwable t) {
                Log.e("video", "onFailure: "+t.getMessage() );
            }
        });
    }
}
