package com.example.myshixun.release;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshixun.R;
import com.example.myshixun.adapter.releasepage_getimage_adapter;
import com.example.myshixun.adapter.releasepage_showimage_adapter;
import com.example.myshixun.mainbody.Contentctivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class releaseActivity extends AppCompatActivity  {
    private TextView textView_release ;
    private ImageView imageView_upload,imageView_back;
    private RecyclerView recyclerView_getimage,recyclerView_showimage;
    static String showimage_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        EventBus.getDefault().register(this);
        textView_release=findViewById(R.id.releasepage_tv_release);
        imageView_upload=findViewById(R.id.releasepage_uploadimage);
        imageView_back=findViewById(R.id.releasepage_back);
        recyclerView_getimage=findViewById(R.id.imageget_release);
        recyclerView_showimage=findViewById(R.id.imageshow_release);
        textView_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(releaseActivity.this, Contentctivity.class);
//                startActivity(intent);
                finish();
            }
        });
        imageView_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read_image();
            }
        });

    }

    @Subscribe()
    public void getshowimage(EventList eventList){
        List<String> showimage=new ArrayList<>();
        showimage=eventList.getListdata();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_showimage.setLayoutManager(staggeredGridLayoutManager);//确定布局
        recyclerView_showimage.setAdapter(new releasepage_showimage_adapter(showimage,this));
        recyclerView_showimage.setItemViewCacheSize(showimage.size());
    }
    public void read_image(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int haspermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(haspermission == 0){
                Toast.makeText(this, "可以读取信息啦", Toast.LENGTH_SHORT).show();
            }

            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(uri,new String[]{MediaStore.Images.ImageColumns.DATA},null,null,null);

        int i=0;
        List<String> paths= new ArrayList<>();
        while(cursor.moveToNext()){

            paths.add(cursor.getString(0));
            //Toast.makeText(this, "得到信息"+paths, Toast.LENGTH_SHORT).show();  不注释会一次性累计提示在屏幕上
            i++;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_getimage.setLayoutManager(staggeredGridLayoutManager);//确定布局
        recyclerView_getimage.setAdapter(new releasepage_getimage_adapter(paths,releaseActivity.this));
        recyclerView_getimage.setItemViewCacheSize(paths.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}