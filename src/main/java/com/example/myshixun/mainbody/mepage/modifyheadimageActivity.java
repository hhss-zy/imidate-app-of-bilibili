package com.example.myshixun.mainbody.mepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myshixun.R;
import com.example.myshixun.adapter.releasepage_getimage_adapter;
import com.example.myshixun.release.releaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class modifyheadimageActivity extends AppCompatActivity {
    @BindView(R.id.modifyimage_back)
    ImageView imageView_back;
    @BindView(R.id.modifyimage_recycleview)
    RecyclerView recyclerView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifyheadimage_activity);
        ButterKnife.bind(this);
        sharedPreferences=getSharedPreferences("date",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        read_image();
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void read_image(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int haspermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(haspermission == 0){
                Toast.makeText(this, "可以读取信息了", Toast.LENGTH_SHORT).show();
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
            i++;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);//确定布局
        recyclerView.setAdapter(new Modifyimage_adapter(paths, this, new Modifyimage_adapter.ModifyimageListner() {
            @Override
            public void chioceimage(int position) {
                finish();
            }
        }));
        recyclerView.setItemViewCacheSize(paths.size());
    }
}