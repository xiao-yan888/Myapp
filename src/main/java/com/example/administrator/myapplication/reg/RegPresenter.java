package com.example.administrator.myapplication.reg;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.RegisterBean;
import com.example.administrator.myapplication.net.LoginApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public class RegPresenter extends BasePresenter<RegContract.View>implements RegContract.Presenter{
    LoginApi loginApi;
    @Inject
    public RegPresenter(LoginApi loginApi) {
        this.loginApi=loginApi;
    }

    @Override
    public void reg(String mobile, String password) {
        loginApi.reg(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.regSuccess();
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
