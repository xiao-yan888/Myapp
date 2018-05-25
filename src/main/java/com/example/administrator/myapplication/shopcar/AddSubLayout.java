package com.example.administrator.myapplication.shopcar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;


/**
 * Created by Administrator on 2018/4/29 0029.
 */

public class AddSubLayout extends LinearLayout {

    private TextView add;
    private TextView sub;
    private TextView num;

    public AddSubLayout(Context context) {
        this(context,null);
    }

    public AddSubLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddSubLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.add_sub_item, this);
        add = view.findViewById(R.id.add);
        sub = view.findViewById(R.id.sub);
        num = view.findViewById(R.id.num);
    }
//设置数量
    public void setNum(String str){
        num.setText(str);
    }
  //获取数量
    public String getNum(){
        return num.getText().toString();
    }
    // 给加号设置点击事件
    public void setAddClickListeren(OnClickListener onClickListener){
        add.setOnClickListener(onClickListener);
    }
    // 给减号设置点击事件
    public void setSubClickListeren(OnClickListener onClickListener){
        sub.setOnClickListener(onClickListener);
    }
}
