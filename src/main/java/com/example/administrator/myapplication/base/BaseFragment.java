package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.inter.IBase;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements IBase ,BaseContract.BaseView{
    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        mPresenter.attchView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), null);
        initView(view);
        return view;
    }

    @Override
    public void onDestroy() {
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
