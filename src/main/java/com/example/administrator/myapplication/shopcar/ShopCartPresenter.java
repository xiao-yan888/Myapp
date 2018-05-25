package com.example.administrator.myapplication.shopcar;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.SellerBean;
import com.example.administrator.myapplication.net.ProjectApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public class ShopCartPresenter extends BasePresenter<ShopCartContract.View> implements ShopCartContract.presenter{

   private ProjectApi projectApi;
@Inject
    public ShopCartPresenter(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public void getCarts(String uid, String token) {
        projectApi.getCarts(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCartsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCartsBean getCartsBean) {
                        List<SellerBean> groupList = new ArrayList<>();//用于封装一级列表
                        List<List<GetCartsBean.DataBean.ListBean>> childList = new ArrayList<>();//用于封装二级列表

                        List<GetCartsBean.DataBean> data = getCartsBean.getData();

                        for (int i = 0; i < data.size(); i++) {
                            GetCartsBean.DataBean dataBean = data.get(i);
                            SellerBean sellerBean = new SellerBean();
                            sellerBean.setSellerName(dataBean.getSellerName());
                            sellerBean.setSellerid(dataBean.getSellerid());
                            sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                            groupList.add(sellerBean);

                            List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                            childList.add(list);
                        }
                        if (mView != null) {
                            mView.showCartList(groupList, childList);
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

    @Override
    public void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token) {
        projectApi.updateCarts(uid, sellerid, pid, num, selected, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if (mView != null) {
                    mView.updateCartsSuccess(s);
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

    @Override
    public void deleteCart(String uid, String pid, String token) {
        projectApi.deleteCart(uid, pid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if (mView != null) {
                    mView.deleteCartSuccess(s);
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
    public boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean){
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i <list.size() ; i++) {
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if (listBean.getSelected()==0){
                return false;
            }
        }
        return true;

    }
}
