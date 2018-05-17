package com.example.administrator.myapplication.reg;

import com.example.administrator.myapplication.base.BaseContract;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public interface RegContract {
    interface View extends BaseContract.BaseView {
        void regSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void reg(String mobile, String password);
    }
}
