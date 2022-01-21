package com.example.myshixun.mainbody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myshixun.R;
import com.example.myshixun.mainbody.messagepage.messagepage_adapter;
import com.example.myshixun.mainbody.messagepage.messagepageutil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class message_frament extends Fragment {
    private View view;
    @BindView(R.id.messagepage_list_recycleview)
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.message_fragment,container,false);
        }
        ButterKnife.bind(this,view);
        List<messagepageutil> data=new ArrayList<>();
        data.add(new messagepageutil(R.drawable.b_main,"哔哩哔哩创作中心","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.bilibili,"哔哩哔哩大会员","非常感谢你的关注！点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.b_login,"哔哩哔哩","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.dls,"英语兔","非常感谢你的关注！如果你希望改善语音，点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-26"));
        data.add(new messagepageutil(R.drawable.b_main,"哔哩哔哩创作中心","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.bilibili,"哔哩哔哩大会员","非常感谢你的关注！点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.b_login,"哔哩哔哩","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.dls,"英语兔","非常感谢你的关注！如果你希望改善语音，点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-26"));
        data.add(new messagepageutil(R.drawable.b_main,"哔哩哔哩创作中心","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.bilibili,"哔哩哔哩大会员","非常感谢你的关注！点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.b_login,"哔哩哔哩","点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-27"));
        data.add(new messagepageutil(R.drawable.dls,"英语兔","非常感谢你的关注！如果你希望改善语音，点击领取快乐，3.8折开30天大会员，我们希望了解你最近在B站的观看体验","11-26"));
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(new messagepage_adapter(getContext(),data));
        return view;
    }
}
