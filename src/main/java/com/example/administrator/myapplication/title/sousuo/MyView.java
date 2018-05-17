package com.example.administrator.myapplication.title.sousuo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/5/15 0015.
 */

public class MyView extends LinearLayout {

    private EditText et_shop;
    private int color;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }
    public String getEt_shop() {
        return et_shop.getText().toString().trim();
    }

    public void setEt_shop(String et) {
        et_shop.setText(et);
        et_shop.setTextColor(color);
    }

    //自定义接口
    public interface OnMyViewClickListener{
        void onClickListener(View v);
    }
    private OnMyViewClickListener onMyViewClickListener;
    public void setOnMyViewClickListener(OnMyViewClickListener onMyViewClickListener){
        this.onMyViewClickListener=onMyViewClickListener;
    }
    private void initView(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        color = a.getColor(R.styleable.MyView_tv_color, Color.BLACK);


        View view = View.inflate(context, R.layout.myview, this);
        et_shop = view.findViewById(R.id.et_shop);
        TextView sou = view.findViewById(R.id.sou);
        sou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onMyViewClickListener!=null){
                    onMyViewClickListener.onClickListener(view);
                }
            }
        });

    }
}
