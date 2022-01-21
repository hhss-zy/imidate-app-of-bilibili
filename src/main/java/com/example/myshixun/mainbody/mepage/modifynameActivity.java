package com.example.myshixun.mainbody.mepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshixun.R;
import com.example.myshixun.release.Eventstring;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class modifynameActivity extends AppCompatActivity {
    @BindView(R.id.modifyname_edit)
    EditText editText;
    @BindView(R.id.modifyname_preservation)
    TextView textView_preservation;

    ImageView imageView_back;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_name);
        ButterKnife.bind(this);

        sharedPreferences=getSharedPreferences("date",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        imageView_back=findViewById(R.id.modifyname_back);
        textView_preservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eventstring eventstring=new Eventstring(editText.getText().toString(),2);
                EventBus.getDefault().post(eventstring);
                finish();
            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}