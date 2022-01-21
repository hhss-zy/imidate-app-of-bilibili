package com.example.myshixun.mainbody.firstpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recommend_viewpage_fregment extends Fragment {

    @BindView(R.id.recomment_viewpage_recycleview)
    RecyclerView recyclerView;
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        if (view==null){
            view=getLayoutInflater().inflate(R.layout.recomment_viewpage_fragment,null,false);
        }
        ButterKnife.bind(this,view);
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
                recyclerView.setAdapter(new Recomment_viewpage_adapter(getContext(), datas, new Recomment_viewpage_adapter.Onclickturnvideo() {
                    @Override
                    public void turnvideo(int p) {
                        Intent intent=new Intent(getContext(), VideoplayActivity.class);
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
