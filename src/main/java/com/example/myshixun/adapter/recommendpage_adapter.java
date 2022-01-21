package com.example.myshixun.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshixun.R;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.basemodle1;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class recommendpage_adapter extends RecyclerView.Adapter<recommendpage_adapter.Viewholder> {

    private List<AppInformation> datas;
    private Context context;
    Onclicckvideo onclicckvideo;
    public recommendpage_adapter(List<AppInformation> datas, Context context,Onclicckvideo onclicckvideo) {
        this.datas = datas;
        this.context = context;
        this.onclicckvideo=onclicckvideo;
    }

    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recommend_item, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder( recommendpage_adapter.Viewholder holder, int position) {
        Glide.with(context).load(Apiservice.base_url+datas.get(position).getHeadimage()).into(holder.imageView_icon);
        Glide.with(context).load(Apiservice.base_url+datas.get(position).getVideoimage()).into(holder.imageView_voide);
        holder.textView_name.setText(datas.get(position).getUsername());
        holder.textView_time.setText(datas.get(position).getUploadtime());
        holder.textView_title.setText(datas.get(position).getVideotitle());
        holder.textView_time.setText(datas.get(position).getVideotime());
        holder.imageView_voide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicckvideo.turnvideo(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView_voide;
        CircleImageView imageView_icon;
        TextView textView_name,textView_time,textView_title,textView_videotime;
        public Viewholder( View itemView) {
            super(itemView);
            imageView_icon=itemView.findViewById(R.id.image_recommend_touxiang);
            imageView_voide=itemView.findViewById(R.id.recommend_iamge_voide);
            textView_name=itemView.findViewById(R.id.recommend_name_tv);
            textView_time=itemView.findViewById(R.id.recommend_time_tv);
            textView_title=itemView.findViewById(R.id.recommend_title_tv);
            textView_videotime=itemView.findViewById(R.id.recommend_voidetime_text);
        }
    }
    public interface Onclicckvideo{
        public void turnvideo(int p);
    }
}
