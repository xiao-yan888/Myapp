package com.example.administrator.myapplication.xiangqing;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.DetailsBean;
import com.example.administrator.myapplication.net.LoginApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
 LoginApi loginApi;
   @Inject
    public DetailPresenter( LoginApi loginApi) {
       this.loginApi=loginApi;
    }

    @Override
    public void getProducts(String pscid) {
        loginApi.getProducts(pscid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        mView.getProductsSuccess(detailsBean);
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
