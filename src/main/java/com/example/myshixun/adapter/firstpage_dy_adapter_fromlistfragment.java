package com.example.myshixun.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myshixun.R;
import com.example.myshixun.mainbody.VideoplayActivity;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.basemodle1;

import java.util.List;

public class firstpage_dy_adapter_fromlistfragment extends BaseQuickAdapter<AppInformation, BaseViewHolder> {
    Context context;
    Onclickimage onclickimage;

    public firstpage_dy_adapter_fromlistfragment(List<AppInformation> datas,Context context,Onclickimage onclickimage) {
        super(R.layout.firstpage_dyitem, datas);
        this.context=context;
        this.onclickimage=onclickimage;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AppInformation appInformation) {
        baseViewHolder.setText(R.id.dytime_time,appInformation.getVideotime());
        baseViewHolder.setText(R.id.dyitem_title,appInformation.getVideotitle());
        baseViewHolder.setText(R.id.dyitem_name,appInformation.getUsername());
        ImageView imageView=baseViewHolder.getView(R.id.dyitem_image);
        Glide.with(context).load(Apiservice.base_url+appInformation.getVideoimage()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickimage.turnvideo(baseViewHolder.getLayoutPosition());
            }
        });
    }


    //    int[] icon = {R.drawable.b_login,R.drawable.dls,R.drawable.b_login,R.drawable.dls,R.drawable.b_login,R.drawable.dls};
    public interface Onclickimage{
        void turnvideo(int p);
    }
}
