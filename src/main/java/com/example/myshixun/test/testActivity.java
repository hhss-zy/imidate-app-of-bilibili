package com.example.myshixun.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myshixun.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class testActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;
    private StyledPlayerView styledPlayerView;
    public  String Url="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        styledPlayerView=findViewById(R.id.Videoplay_vd);
        exoPlayer=new ExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(exoPlayer);
        MediaItem mediaItem=MediaItem.fromUri(Url);
        exoPlayer.setMediaItem(mediaItem);
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