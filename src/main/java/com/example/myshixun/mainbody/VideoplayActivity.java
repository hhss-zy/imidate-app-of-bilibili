package com.example.myshixun.mainbody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myshixun.R;
import com.example.myshixun.adapter.Video_adapter;
import com.example.myshixun.adapter.recommendpage_adapter;
import com.example.myshixun.netapi.Apiservice;
import com.example.myshixun.util.AppInformation;
import com.example.myshixun.util.BaseModle;
import com.example.myshixun.util.NetUtil;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoplayActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;
    private StyledPlayerView styledPlayerView;
    public  String Url="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";

    ImageView imageView_back;
    @BindView(R.id.video_title)
    TextView textView_title;
    @BindView(R.id.video_imagehead)
    CircleImageView imageView_head;
    @BindView(R.id.video_username)
    TextView textView_name;

    RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        sharedPreferences=getSharedPreferences("date",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String videopath= sharedPreferences.getString("videopath","");
        recyclerView=findViewById(R.id.video_recyclerView);
        getvideo();
        //谷歌视频播放器
        styledPlayerView=findViewById(R.id.Videoplay_vd);
        exoPlayer=new ExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(exoPlayer);
        MediaItem mediaItem=MediaItem.fromUri(Apiservice.base_url+videopath);
        exoPlayer.setMediaItem(mediaItem);

        imageView_back=findViewById(R.id.video_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void getvideo(){
        Apiservice apiservice= NetUtil.getapiservice();
        Call<BaseModle<AppInformation>> call=apiservice.videodata();
        call.enqueue(new Callback<BaseModle<AppInformation>>() {
            @Override
            public void onResponse(Call<BaseModle<AppInformation>> call, Response<BaseModle<AppInformation>> response) {
//                Log.e("video", "suceess: "+response.body().getDatas() );
                List<AppInformation> datas=response.body().getDatas();
                editor.putString("videopath",datas.get(0).getVideoparh());
                editor.apply();
                StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                recyclerView.setAdapter(new Video_adapter(VideoplayActivity.this, datas, new Video_adapter.Onclick_nextvideo() {
                    @Override
                    public void click(int p) {

                    }
                }));
            }

            @Override
            public void onFailure(Call<BaseModle<AppInformation>> call, Throwable t) {
                Log.e("video", "onFailure: "+t.getMessage() );
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        exoPlayer.prepare();
        exoPlayer.play();
    }

    @Override
    protected void onStop() {
        super.onStop();
        exoPlayer.stop();
        exoPlayer.release();
    }
}