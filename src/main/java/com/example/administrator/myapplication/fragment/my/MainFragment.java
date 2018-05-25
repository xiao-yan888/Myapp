package com.example.administrator.myapplication.fragment.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.fragment.my.myorder.OrderListActivity;
import com.example.administrator.myapplication.login.LoginActivity;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class MainFragment extends BaseFragment {
    private TextView tv_login_zhuce;
    public static final int MAIN=2;
    private String name;
    private LinearLayout ll;
    private ImageView touxiang;
    private LinearLayout my_order_dfk;
    private LinearLayout my_order_dpj;
    private LinearLayout my_order_dsh;
    private LinearLayout my_order_th;
    private LinearLayout my_order_all;
    /*  @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(, container, false);
        initView(view);
        return view;
    }*/


    @Override
    public int getContentLayout() {
        return R.layout.mainfragment;
    }

    @Override
    public void inject() {

    }

    public void initView(View view) {
        tv_login_zhuce = view.findViewById(R.id.tv_login_zhuce);
        touxiang = view.findViewById(R.id.touxiang);
        ll = view.findViewById(R.id.ll);
        my_order_dfk = view.findViewById(R.id.my_order_dfk);
        my_order_dpj = view.findViewById(R.id.my_order_dpj);
        my_order_dsh = view.findViewById(R.id.my_order_dsh);
        my_order_th = view.findViewById(R.id.my_order_th);
        my_order_all = view.findViewById(R.id.my_order_all);
        my_order_dfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });
        tv_login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否登录过
                String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
                if (TextUtils.isEmpty(uid)) {
                    //未登录
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("shop",MAIN);
                    startActivity(intent);
                } else {
                    //已登录
                    Intent intent = new Intent(getContext(), UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        String name = (String) SharedPreferencesUtils.getParam(getContext(), "name", "");
        String iconUrl = (String) SharedPreferencesUtils.getParam(getContext(), "iconUrl", "");
        String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
        if (!TextUtils.isEmpty(uid)) {
            //登录过
            ll.setBackgroundResource(R.drawable.normal_regbg);
        } else {
            //未登录
            ll.setBackgroundResource(R.drawable.reg_bg);
        }
        if (!TextUtils.isEmpty(iconUrl)) {
            Glide.with(getContext()).load(iconUrl).into(touxiang);
        }
        if (!TextUtils.isEmpty(name)) {
            tv_login_zhuce.setText(name);
        }


    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
            Bundle b = data.getExtras();
            name = b.getString("name");
            tv_login_zhuce.setText(name);
           *//* tv_login_zhuce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //tv_login_zhuce.setText("登录/注册");
                }
            });*//*

        }
    }*/

}
