package com.example.myshixun.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myshixun.R;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;

import java.util.List;

public class Video_adapter extends BaseQuickAdapter<AppInformation, BaseViewHolder> {
    Context context;
    Onclick_nextvideo listOnclick;

    public Video_adapter(Context context, List<AppInformation> data,Onclick_nextvideo onclick_nextvideo) {
        super(R.layout.video_item, data);
        this.listOnclick=onclick_nextvideo;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AppInformation item) {
        baseViewHolder.setText(R.id.video_item_title,item.getVideotitle());
        baseViewHolder.setText(R.id.video_item_name,"up:"+item.getUsername());
        baseViewHolder.setText(R.id.video_item_time,item.getVideotime());
        baseViewHolder.setText(R.id.video_item_uploadtime,item.getUploadtime());
        ImageView imageView=baseViewHolder.getView(R.id.video_item_iamge);
        Glide.with(context).load(Apiservice.base_url+item.getVideoimage()).into(imageView);
    }

    public interface Onclick_nextvideo{
        public void click(int p);
    }
}
