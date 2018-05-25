package com.example.administrator.myapplication.fragment.my.myorder;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.OrdersBean;
import com.example.administrator.myapplication.net.ProjectApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class OrderListPresenter extends BasePresenter<OrderListContract.View> implements OrderListContract.Presenter{
   private ProjectApi projectApi;
    @Inject
    public OrderListPresenter(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public void getOrders(String uid, String page, String token) {
        projectApi.getOrders(uid,page,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<OrdersBean, List<OrdersBean.DataBean>>() {
                    @Override
                    public List<OrdersBean.DataBean> apply(OrdersBean ordersBean) throws Exception {
                        return ordersBean.getData();
                    }
                }).subscribe(new Consumer<List<OrdersBean.DataBean>>() {
            @Override
            public void accept(List<OrdersBean.DataBean> dataBeans) throws Exception {
                if (mView != null) {
                    mView.showOrders(dataBeans);
                }
            }
        });
    }

    @Override
    public void getWaitOrders(String uid, String page, String token) {

    }

    @Override
    public void getAlreadyOrders(String uid, String page, String token) {

    }

    @Override
    public void getCancleOrders(String uid, String page, String token) {

    }

    @Override
    public void updateOrder(String uid, String status, String orderId, String token) {

    }
}
