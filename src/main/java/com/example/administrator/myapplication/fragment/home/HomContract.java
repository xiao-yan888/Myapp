package com.example.administrator.myapplication.fragment.home;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.AdBean;
import com.example.administrator.myapplication.bean.CatagoryBean;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public interface HomContract {
    interface View extends BaseContract.BaseView{
        void getAdSuccess(AdBean adBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAd();

        void getCatagory();
    }
}
