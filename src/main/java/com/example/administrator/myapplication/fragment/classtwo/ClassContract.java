package com.example.administrator.myapplication.fragment.classtwo;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface ClassContract {
    interface View extends BaseContract.BaseView{
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);
        //void getProductCatagorySuccess(List<String> titlelist, List<List<ProductCatagoryBean.DataBean.ListBean>> childlist);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getProductCatagory(String cid);

        void getCatagory();
    }
}
