package com.example.administrator.myapplication.net;

import com.example.administrator.myapplication.bean.AdBean;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.bean.DetailsBean;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;
import com.example.administrator.myapplication.bean.RegisterBean;
import com.example.administrator.myapplication.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class LoginApi {
    private static LoginApi loginApi;
    private LoginApiService loginApiService;
    private LoginApi(LoginApiService loginApiService) {
        this.loginApiService=loginApiService;
    }

    public static LoginApi getLoginApi(LoginApiService loginApiService){
        if (loginApi==null){
           loginApi=new LoginApi(loginApiService);
        }
        return loginApi;
    }

    public Observable<UserBean> login( String mobile, String password){
       return loginApiService.login(mobile,password);
    }

    public Observable<RegisterBean> reg(String mobile, String password) {
        return loginApiService.reg(mobile, password);
    }

    public Observable<AdBean> getAd() {
        return loginApiService.getAd();
    }
    public Observable<CatagoryBean> getCatagory() {
        return loginApiService.getCatagory();
    }

    public Observable<ProductCatagoryBean> getProductCatagory(String cid) {
        return loginApiService.getProductCatagory(cid);
    }
    public Observable<DetailsBean> getProducts(String pscid) {
        return loginApiService.getProducts(pscid);
    }

   /* public Observable<BaseBean> addCart(String uid, String pid, String token){
        return loginApiService.addCart(uid,pid,token);
    }*/
}
