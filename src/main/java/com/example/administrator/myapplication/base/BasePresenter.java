package com.example.administrator.myapplication.base;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
   protected T mView;
    @Override
    public void attchView(T view) {
        this.mView=view;
    }

    @Override
    public void detachView() {
        if (mView!=null){
            mView=null;
        }
    }
}
