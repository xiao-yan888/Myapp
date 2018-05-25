package com.example.administrator.myapplication.order.addr;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.AddrsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public interface OrderContract {
    interface View extends BaseContract.BaseView {
        void addrsSuccess(List<AddrsBean.DataBean> list);

        //void createOrderSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddrs(String uid, String token);

        //void createOrder(String uid, String price, String token);
    }
}
