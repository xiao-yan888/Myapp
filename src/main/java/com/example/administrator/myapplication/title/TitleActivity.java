package com.example.administrator.myapplication.title;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class TitleActivity extends LinearLayout {

    private ImageView erweima;
    private LinearLayout llsuo;
    private ImageView fenxiang;

    public TitleActivity(Context context) {
        this(context,null);
    }

    public TitleActivity(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleActivity(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }


    //1定义一个接口
    public interface OnItemTitleClickListener{
        void onClickleft(View v);
        void onClickrigth(View v);
        void onClicksou(View v);
    }
private OnItemTitleClickListener onItemTitleClickListener;
    public void setOnItemTitleClickListener(OnItemTitleClickListener onItemTitleClickListener){
        this.onItemTitleClickListener=onItemTitleClickListener;

    }


    private void initview(final Context context) {
        View view = View.inflate(context, R.layout.titleview, this);
        erweima = view.findViewById(R.id.erweima);
        llsuo = view.findViewById(R.id.llsuo);
        fenxiang = view.findViewById(R.id.fenxiang);
        erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(context, CaptureActivity.class);
            onItemTitleClickListener.onClickleft(v);

            }
        });


        fenxiang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemTitleClickListener.onClickrigth(v);
            }
        });
        llsuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemTitleClickListener.onClicksou(v);
            }
        });

    }


}
