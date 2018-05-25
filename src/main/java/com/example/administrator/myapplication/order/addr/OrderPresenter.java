package com.example.administrator.myapplication.order.addr;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.AddrsBean;
import com.example.administrator.myapplication.net.ProjectApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter {

    private ProjectApi projectApi;
    @Inject
    public OrderPresenter(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public void getAddrs(String uid, String token) {
        projectApi.getAddrs(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<AddrsBean, List<AddrsBean.DataBean>>() {
                    @Override
                    public List<AddrsBean.DataBean> apply(AddrsBean addrsBean) throws Exception {
                        return addrsBean.getData();
                    }
                }).subscribe(new Observer<List<AddrsBean.DataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<AddrsBean.DataBean> dataBeans) {
                if (mView != null) {
                    mView.addrsSuccess(dataBeans);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
