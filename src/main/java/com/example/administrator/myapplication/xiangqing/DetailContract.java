package com.example.administrator.myapplication.xiangqing;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.DetailsBean;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public interface DetailContract {
    interface View extends BaseContract.BaseView{
        void getProductsSuccess(DetailsBean detailsBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getProducts(String pscid);
    }
}
