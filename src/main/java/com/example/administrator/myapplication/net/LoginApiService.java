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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public interface LoginApiService {
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserBean> login(@Field("mobile") String mobile,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("user/reg")
    Observable<RegisterBean> reg(@Field("mobile") String mobile,
                                   @Field("password") String password);
    @GET("ad/getAd")
    Observable<AdBean> getAd();

    @GET("product/getCatagory")
    Observable<CatagoryBean> getCatagory();

    @FormUrlEncoded
    @POST("product/getProductCatagory")
    Observable<ProductCatagoryBean> getProductCatagory(@Field("cid") String cid);

    @FormUrlEncoded
    @POST("product/getProducts")
    Observable<DetailsBean> getProducts(@Field("pscid") String pscid);

    @FormUrlEncoded
    @POST("product/addCart")
    Observable<BaseBean> addCart(@Field("uid")String uid,@Field("pid")String pid,@Field("token")String token);
}
