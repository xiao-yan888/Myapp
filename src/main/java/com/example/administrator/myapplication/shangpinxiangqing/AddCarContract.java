package com.example.administrator.myapplication.shangpinxiangqing;

import com.example.administrator.myapplication.base.BaseContract;
import com.example.administrator.myapplication.bean.BaseBean;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public interface AddCarContract {
    interface View extends BaseContract.BaseView{
        void getAddcarSuccess(BaseBean baseBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void addCart(String uid, String pid, String token);
    }
}
