package com.example.administrator.myapplication.fragment.my;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.net.ProjectApi;

import java.io.File;


import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/22 0022.
 */

public class UpdatePresenter extends BasePresenter<UpdateHeaderContract.View> implements UpdateHeaderContract.Presenter {

    private ProjectApi projectApi;
    @Inject
    public UpdatePresenter(ProjectApi projectApi) {
        this.projectApi = projectApi;
    }

    @Override
    public void updateHeader(String uid, String filePath) {

        int i = filePath.lastIndexOf("/");
        String fileName = filePath.substring(i+1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath));

        MediaType textType = MediaType.parse("text/plain");
        RequestBody u = RequestBody.create(textType, uid);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", fileName, file);

        projectApi.updateHeader(u,f)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getCode();
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mView.updateSuccess(s);
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
