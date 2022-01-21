package com.example.myshixun.mainbody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myshixun.R;
import com.example.myshixun.event.Event;
import com.example.myshixun.release.releaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Contentctivity extends AppCompatActivity {
    private LinearLayout linearLayout_firstpage, linearLayout_dynamics, linearLayout_message, linearLayout_me,linearLayout_release;
    static private ImageView imageView_first, imageView_dynamics, imageView_message, imageView_me,imageView_release;
    static private TextView textView_first, textView_dynamics, textView_message, textView_me;
    private first_page_fragment firstpage_fragment;
    private recommend_fragment recommend_fragment;
    private me_fragment me_fragment;
    private message_frament message_frament;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentctivity);
//        EventBus.getDefault().register(this);
        linearLayout_firstpage = findViewById(R.id.linearlayout_firstPage);
        linearLayout_dynamics = findViewById(R.id.linearlayout_recommentPage);
        linearLayout_me = findViewById(R.id.linearlayout_mePage);
        linearLayout_message = findViewById(R.id.linearlayout_messagePage);
        linearLayout_release = findViewById(R.id.linearlayout_releasePage);
        imageView_first = findViewById(R.id.linearlayout_first_image);
        imageView_dynamics = findViewById(R.id.linearlayout_recomment_image);
        imageView_me = findViewById(R.id.linearlayout_mePage_image);
        imageView_message = findViewById(R.id.linearlayout_messagePage_image);
        textView_first = findViewById(R.id.linearlayout_first_textview);
        textView_dynamics = findViewById(R.id.linearlayout_recomment_textview);
        textView_me = findViewById(R.id.linearlayout_mePage_textview);
        textView_message = findViewById(R.id.linearlayout_messagePage_textview);
        if (firstpage_fragment != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, firstpage_fragment).commitAllowingStateLoss();
        } else {
            firstpage_fragment = new first_page_fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, firstpage_fragment).commitAllowingStateLoss();
        }
        setlistenrer();
        changeview(1);
    }

//    @Subscribe()
//    public void get(Event event){
//        String s=event.getUser().getName();
//        Log.d("TAG", "t1: "+s);
//    }
    private void setlistenrer() {
        onclick onclick = new onclick();
        linearLayout_firstpage.setOnClickListener(onclick);
        linearLayout_dynamics.setOnClickListener(onclick);
        linearLayout_message.setOnClickListener(onclick);
        linearLayout_me.setOnClickListener(onclick);
        linearLayout_release.setOnClickListener(onclick);
    }

    public void changeview(int i) {
        imageView_first.setSelected(false);
        textView_first.setSelected(false);

        imageView_dynamics.setSelected(false);
        textView_dynamics.setSelected(false);

        imageView_me.setSelected(false);
        textView_me.setSelected(false);

        imageView_message.setSelected(false);
        textView_message.setSelected(false);

        switch (i) {
            case 1:
                imageView_first.setSelected(true);
                textView_first.setSelected(true);
                break;
            case 2:
                imageView_dynamics.setSelected(true);
                textView_dynamics.setSelected(true);
                break;
            case 3:
                imageView_message.setSelected(true);
                textView_message.setSelected(true);
                break;
            case 4:
                imageView_me.setSelected(true);
                textView_me.setSelected(true);
                break;
        }
    }
    private class onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearlayout_firstPage:
                    if (firstpage_fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, firstpage_fragment).commitAllowingStateLoss();
                    } else {
                        firstpage_fragment = new first_page_fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, firstpage_fragment).commitAllowingStateLoss();
                    }
                    changeview(1);
                    break;
                case R.id.linearlayout_recommentPage:
                    if (recommend_fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, recommend_fragment).commitAllowingStateLoss();
                    } else {
                        recommend_fragment = new recommend_fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, recommend_fragment).commitAllowingStateLoss();
                    }
                    changeview(2);
                    break;
                case R.id.linearlayout_mePage:
                    if (me_fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, me_fragment).commitAllowingStateLoss();
                    } else {
                        me_fragment = new me_fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, me_fragment).commitAllowingStateLoss();
                    }
                    changeview(4);
                    break;
                case R.id.linearlayout_messagePage:
                    if (message_frament != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, message_frament).commitAllowingStateLoss();
                    } else {
                        message_frament = new message_frament();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, message_frament).commitAllowingStateLoss();
                    }
                    changeview(3);
                    break;
                case R.id.linearlayout_releasePage:
                    Intent intent=new Intent(Contentctivity.this, releaseActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
