package com.example.myshixun.mainbody;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myshixun.R;
import com.example.myshixun.adapter.recommendpage_adapter;
import com.example.myshixun.mainbody.firstpage.Recomment_viewpage_adapter;
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

public class recommend_fragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.recomment_fragment,container,false);
        }
        recyclerView=view.findViewById(R.id.reconmentpage_recyclerview);

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
                StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);//确定布局
                recyclerView.setAdapter(new recommendpage_adapter(datas, getContext(), new recommendpage_adapter.Onclicckvideo() {
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
