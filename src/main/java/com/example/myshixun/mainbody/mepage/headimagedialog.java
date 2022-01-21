package com.example.myshixun.mainbody.mepage;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myshixun.R;
import com.google.android.material.dialog.InsetDialogOnTouchListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class headimagedialog  extends Dialog {
    public String dialogtitle;
    public String dialogcancel_text;
    public DialogListener listener;

    TextView textView_title;
    TextView textView_cancel;
    LinearLayout linearLayout_takeipone,linearLayout_imagebase,linearLayout_rondom;
    public headimagedialog(@NonNull Context context) {
        super(context);
    }
    public  headimagedialog(Context context,String dialogtitle,String dialogcancel_text,DialogListener listener ){
        super(context);
        this.dialogtitle=dialogtitle;
        this.dialogcancel_text=dialogcancel_text;
        this.listener=listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContext().setTheme(R.style.imagedialog);
        setContentView(R.layout.imagedialog);
        initview();
        linearLayout_takeipone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.gototakeipone();
                headimagedialog.this.dismiss();
            }
        });
        linearLayout_imagebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.gotoimagebase();
                headimagedialog.this.dismiss();
            }
        });
        linearLayout_rondom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.gotoimagerondom();
                headimagedialog.this.dismiss();
            }
        });
        textView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cannel();
                headimagedialog.this.dismiss();
            }
        });

    }

    private void initview(){
        textView_title=findViewById(R.id.imagedialog_title);
        textView_cancel=findViewById(R.id.imagedialog_cancel);
        linearLayout_takeipone=findViewById(R.id.imagedialog_takeipone);
        linearLayout_imagebase=findViewById(R.id.imagedialog_imagebase);
        linearLayout_rondom=findViewById(R.id.imagedialog_rondomimage);
        if (!TextUtils.isEmpty(dialogtitle)){
            textView_title.setText(dialogtitle);
        }
        if (!TextUtils.isEmpty(dialogcancel_text)){
            textView_cancel.setText(dialogcancel_text);
        }
    }
public interface DialogListener{
    void gototakeipone();
    void gotoimagebase();
    void gotoimagerondom();
    void cannel();
}

}
