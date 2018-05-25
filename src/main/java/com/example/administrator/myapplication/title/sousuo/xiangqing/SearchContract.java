package com.example.administrator.myapplication.title.sousuo.xiangqing;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.SearchBean;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public interface SearchContract {
    interface View extends BaseContract.BaseView{
        void searchProductsSuccess(SearchBean searchBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void searchProducts(String keywords);
    }
}
