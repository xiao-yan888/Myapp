package com.example.administrator.myapplication.fragment.my.myorder;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.OrdersBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public interface OrderListContract {

    interface View extends BaseContract.BaseView {
        void showOrders(List<OrdersBean.DataBean> list);

        void updateOrderSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getOrders(String uid, String page, String token);

        void getWaitOrders(String uid, String page, String token);

        void getAlreadyOrders(String uid, String page, String token);

        void getCancleOrders(String uid, String page, String token);

        void updateOrder(String uid, String status, String orderId, String token);
    }
}
