package com.example.administrator.myapplication.inter;

import android.view.View;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public interface IBase {
    int getContentLayout();
    void inject();
    void initView(View view);
}
