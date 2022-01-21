package com.example.myshixun.mainbody;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myshixun.R;
import com.example.myshixun.mainbody.mepage.modify_informationActivity;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.release.Eventstring;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class me_fragment extends Fragment {
    private View view;

    @BindView(R.id.mepage_tomodify_information_)
    RelativeLayout relativeLayout_toinformation;

    @BindView(R.id.mepage_username_tv)
    TextView textView_username;
    CircleImageView circleImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.me_fragment,container,false);
        }
        ButterKnife.bind(this,view);
        circleImageView=view.findViewById(R.id.mepage_head_portrait);

        EventBus.getDefault().register(this);
        relativeLayout_toinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), modify_informationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("date",MODE_PRIVATE);
        String username=sharedPreferences.getString("f_username","");
        String userimage=sharedPreferences.getString("f_headimage","");
        textView_username.setText(username);
        Glide.with(getContext()).load(Apiservice.base_url+userimage).into(circleImageView);
        return view;
    }
    //修改名称
    @Subscribe(threadMode= ThreadMode.MAIN,sticky=true)
    public void getname(Eventstring eventstring){
        switch (eventstring.getCode()){
            case 1:
                break;
            case 2: textView_username.setText(eventstring.getShowimage());
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
