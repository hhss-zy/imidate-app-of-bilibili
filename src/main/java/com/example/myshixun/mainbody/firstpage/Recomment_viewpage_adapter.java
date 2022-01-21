package com.example.myshixun.mainbody.firstpage;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myshixun.R;
import com.example.myshixun.adapter.firstpage_dy_adapter_fromlistfragment;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recomment_viewpage_adapter extends BaseQuickAdapter<AppInformation, BaseViewHolder>{
        Context context;
        Onclickturnvideo onclickturnvideo;
    public Recomment_viewpage_adapter(Context context, List<AppInformation> datas,Onclickturnvideo onclickturnvideo) {
        super(R.layout.recommend_item, datas);
        this.context=context;
        this.onclickturnvideo=onclickturnvideo;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AppInformation item) {
//        baseViewHolder.getAdapterPosition();
        ImageView imageView_video=baseViewHolder.getView(R.id.recommend_iamge_voide);
        CircleImageView imageView_head=baseViewHolder.getView(R.id.image_recommend_touxiang);
        Glide.with(context).load(Apiservice.base_url+item.getVideoimage()).into(imageView_video);
        imageView_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onclickturnvideo.turnvideo(baseViewHolder.getLayoutPosition());
            }
        });
       Glide.with(context).load(Apiservice.base_url+item.getHeadimage()).into(imageView_head);
        baseViewHolder.setText(R.id.recommend_name_tv,item.getUsername());
        baseViewHolder.setText(R.id.recommend_time_tv,item.getUploadtime());
        baseViewHolder.setText(R.id.recommend_title_tv,item.getVideotitle());
        baseViewHolder.setText(R.id.recommend_voidetime_text,item.getVideotime());

    }
    public interface Onclickturnvideo{
        public void turnvideo(int p);
    }
}
