package com.example.myshixun.mainbody.messagepage;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myshixun.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class messagepage_adapter extends BaseQuickAdapter<messagepageutil, BaseViewHolder> {
    Context context;
    public messagepage_adapter(Context context, List<messagepageutil> data) {
        super(R.layout.messagepage_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, messagepageutil item) {
        baseViewHolder.setText(R.id.messagepage_item_name,item.getName());
        baseViewHolder.setText(R.id.messagepage_item_message,item.getMessage());
        baseViewHolder.setText(R.id.messagepage_item_time,item.getTime());
        CircleImageView circleImageView=baseViewHolder.getView(R.id.messagepage_item_circle);
        Glide.with(context).load(item.getImage()).into(circleImageView);
    }
}
