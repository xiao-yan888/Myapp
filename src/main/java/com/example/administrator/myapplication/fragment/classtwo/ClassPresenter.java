package com.example.administrator.myapplication.fragment.classtwo;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;
import com.example.administrator.myapplication.net.LoginApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ClassPresenter extends BasePresenter<ClassContract.View> implements ClassContract.Presenter{
    LoginApi loginApi;
    @Inject
    public ClassPresenter(LoginApi loginApi) {
        this.loginApi=loginApi;
    }
    @Override
    public void getProductCatagory(String cid) {
        loginApi.getProductCatagory(cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductCatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductCatagoryBean productCatagoryBean) {
                        mView.getProductCatagorySuccess(productCatagoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCatagory() {
            loginApi.getCatagory()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<CatagoryBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CatagoryBean catagoryBean) {
                            mView.getCatagorySuccess(catagoryBean);
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
