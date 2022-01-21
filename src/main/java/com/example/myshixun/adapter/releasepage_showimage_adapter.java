package com.example.myshixun.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshixun.R;

import java.util.List;

public class releasepage_showimage_adapter extends RecyclerView.Adapter<releasepage_showimage_adapter.Viewholder_show> {

    List<String> showimagepath;
    Context context;

    public releasepage_showimage_adapter(List<String> showimagepath, Context context) {
        this.showimagepath = showimagepath;
        this.context = context;
    }

    @Override
    public Viewholder_show onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.releasepage_showimageitem, null);
        return  new Viewholder_show(view);
    }

    @Override
    public void onBindViewHolder( releasepage_showimage_adapter.Viewholder_show holder, int position) {
        Glide.with(context).load(showimagepath.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return showimagepath.size();
    }

    public class Viewholder_show extends RecyclerView.ViewHolder{
        ImageView imageView;
        public Viewholder_show( View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.releasepage_showimage);
        }
    }
}
