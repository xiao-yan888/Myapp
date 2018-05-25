package com.example.administrator.myapplication.fragment.my;


import com.example.administrator.myapplication.base.BaseContract;

public interface UpdateHeaderContract {

    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath);
    }
}
