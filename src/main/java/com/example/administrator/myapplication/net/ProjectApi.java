package com.example.administrator.myapplication.net;

import com.example.administrator.myapplication.bean.AddrsBean;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.OrdersBean;
import com.example.administrator.myapplication.bean.SearchBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public class ProjectApi {
    private static ProjectApi projectApi;
    private ProjectApiService projectApiService;
    private ProjectApi(ProjectApiService projectApiService) {
        this.projectApiService=projectApiService;
    }

    public static ProjectApi getProjectApi(ProjectApiService projectApiService){
        if (projectApi==null){
            projectApi=new ProjectApi(projectApiService);
        }
        return projectApi;
    }

    public Observable<BaseBean> addCart(String uid, String pid, String token){
        return projectApiService.addCart(uid,pid,token);
    }

    public Observable<GetCartsBean> getCarts(String uid, String token) {
        return projectApiService.getCarts(uid,token);
    }

    public Observable<BaseBean> updateCarts(String uid, String sellerid, String pid, String num, String selected,
                                            String token) {
        return projectApiService.updateCarts(uid, sellerid, pid, num, selected, token);
    }

    public Observable<BaseBean> deleteCart(String uid, String pid,
                                           String token) {
        return projectApiService.deleteCart(uid, pid, token);
    }
    //查询常用收获地址
    public Observable<AddrsBean> getAddrs(String uid, String token) {
        return projectApiService.getAddrs(uid, token);
    }

    public Observable<BaseBean> updateHeader(RequestBody uid, MultipartBody.Part file){
        return projectApiService.updateHeader(uid,file);
    }
    //所搜接口
    public Observable<SearchBean> searchProducts(String keywords) {
        return projectApiService.searchProducts(keywords);
    }
    //查询订单
    public Observable<OrdersBean> getOrders(String uid, String page, String token) {
        return projectApiService.getOrders(uid, page, token);
    }
    //改变订单状态
    public Observable<BaseBean> updateOrder(String uid, String status, String orderId, String token) {
        return projectApiService.updateOrder(uid, status, orderId, token);
    }


}
