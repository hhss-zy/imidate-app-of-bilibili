package com.example.myshixun.mainbody.mepage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myshixun.R;
import com.example.myshixun.adapter.releasepage_getimage_adapter;
import com.example.myshixun.release.Eventstring;
import com.example.myshixun.util.Modifyimage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Modifyimage_adapter extends RecyclerView.Adapter<Modifyimage_adapter.Viewholder_modifyimage>{
    List<String> path;
    Context context;
    ModifyimageListner listner;

    public Modifyimage_adapter(List<String> path, Context context, ModifyimageListner listner) {
        this.path = path;
        this.context = context;
        this.listner = listner;
    }

    @Override
    public Viewholder_modifyimage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.modifyimage_adapter, null);
        return new Viewholder_modifyimage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Modifyimage_adapter.Viewholder_modifyimage holder, int position) {
        Glide.with(context).load(path.get(position))
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imagepath=path.get(position);
                Eventstring headimagepath=new Eventstring(imagepath,1);
                EventBus.getDefault().post(headimagepath);
                listner.chioceimage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return path.size();
    }

    public class Viewholder_modifyimage extends RecyclerView.ViewHolder{
        ImageView imageView;
        public Viewholder_modifyimage( View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.modifyimage_adapter_itemimage);
        }
    }
    public interface ModifyimageListner{
        void chioceimage(int position);
    }
}
