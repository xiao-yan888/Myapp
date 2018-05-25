package com.example.administrator.myapplication.net;

import com.example.administrator.myapplication.bean.AddrsBean;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.OrdersBean;
import com.example.administrator.myapplication.bean.SearchBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public interface ProjectApiService {
    @FormUrlEncoded
    @POST("product/addCart")
    Observable<BaseBean> addCart(@Field("uid")String uid, @Field("pid")String pid, @Field("token")String token);

    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<GetCartsBean> getCarts(@Field("Uid") String uid,
                                      @Field("Token") String token);

    @FormUrlEncoded
    @POST("product/updateCarts")
    Observable<BaseBean> updateCarts(@Field("uid") String uid,
                                     @Field("sellerid") String sellerid,
                                     @Field("pid") String pid,
                                     @Field("num") String num,
                                     @Field("selected") String selected,
                                     @Field("token") String token);

    @FormUrlEncoded
    @POST("product/deleteCart")
    Observable<BaseBean> deleteCart(@Field("uid") String uid,
                                    @Field("pid") String pid,
                                    @Field("token") String token);

    @FormUrlEncoded
    @POST("user/getAddrs")
    Observable<AddrsBean> getAddrs(@Field("uid") String uid,
                                   @Field("token") String token);


    @Multipart
    @POST("file/upload")
    Observable<BaseBean> updateHeader(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);

    //根据关键词所搜商品
    @FormUrlEncoded
    @POST("product/searchProducts")
    Observable<SearchBean> searchProducts(@Field("keywords") String keywords);

    @FormUrlEncoded
    @POST("product/getOrders")
    Observable<OrdersBean> getOrders(@Field("Uid") String uid,
                                     @Field("Page") String page,
                                     @Field("Token") String token);

    @FormUrlEncoded
    @POST("product/updateOrder")
    Observable<BaseBean> updateOrder(@Field("uid") String uid,
                                     @Field("status") String status,
                                     @Field("orderId") String orderId,
                                     @Field("token") String token);

}
