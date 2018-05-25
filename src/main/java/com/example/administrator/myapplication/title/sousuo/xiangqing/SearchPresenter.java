package com.example.administrator.myapplication.title.sousuo.xiangqing;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.SearchBean;
import com.example.administrator.myapplication.net.ProjectApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

   ProjectApi projectApi;
    @Inject
    public SearchPresenter(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public void searchProducts(String keywords) {
        projectApi.searchProducts(keywords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        mView.searchProductsSuccess(searchBean);
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
