package com.example.myshixun.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshixun.R;
import com.example.myshixun.release.EventList;
import com.example.myshixun.release.Eventstring;
import com.example.myshixun.release.releaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class releasepage_getimage_adapter extends RecyclerView.Adapter<releasepage_getimage_adapter.Viewholder> {
    List<String> path;
    Context context;
    static List<String> putimagepath=new ArrayList<>();
    public releasepage_getimage_adapter(List<String> path, Context context) {
        this.path = path;
        this.context = context;
    }

    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.releasepage_getimageitem, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder( releasepage_getimage_adapter.Viewholder holder, int position) {
        Glide.with(context).load(path.get(position))
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.imageView_select.setSelected(!holder.imageView_select.isSelected());
                String showpath=path.get(position);
                if (holder.imageView_select.isSelected()){
                    putimagepath.add(showpath);
                }else {
                    putimagepath.remove(showpath);
                }
                EventList<String> eventList=new EventList<>(putimagepath);
                EventBus.getDefault().post(eventList);
//                    Log.i("showpath", "onClick: "+showpath);
            }
        });
    }

    @Override
    public int getItemCount() {
        return path.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView,imageView_select;
        public Viewholder( View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.releasepage_getimage);
        imageView_select=itemView.findViewById(R.id.select_image);
        }
    }
}
