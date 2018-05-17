package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.myapplication.inter.IBase;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView{
   @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        inject();
        mPresenter.attchView(this);
    }
    @Override
    public void initView(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
