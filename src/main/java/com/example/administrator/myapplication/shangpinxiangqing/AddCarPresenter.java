package com.example.administrator.myapplication.shangpinxiangqing;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.net.LoginApi;
import com.example.administrator.myapplication.net.ProjectApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class AddCarPresenter extends BasePresenter<AddCarContract.View> implements AddCarContract.Presenter {

    ProjectApi projectApi;
    @Inject
    public AddCarPresenter(ProjectApi projectApi) {
        this.projectApi=projectApi;
    }

    @Override
    public void addCart(String uid, String pid, String token) {
        projectApi.addCart(uid,pid,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mView.getAddcarSuccess(baseBean);
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
