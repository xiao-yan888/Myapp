package com.example.administrator.myapplication.shopcar;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.SellerBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public interface ShopCartContract {
    interface View extends BaseContract.BaseView{
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);
    }
    interface presenter extends BaseContract.BasePresenter<View>{
        void getCarts(String uid, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);
    }
}
