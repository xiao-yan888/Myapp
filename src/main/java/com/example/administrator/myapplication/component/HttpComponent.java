package com.example.administrator.myapplication.component;

import com.example.administrator.myapplication.fragment.CarFragment;
import com.example.administrator.myapplication.fragment.classtwo.ClassFragment;
import com.example.administrator.myapplication.fragment.home.HomeFragment;
import com.example.administrator.myapplication.fragment.my.MainFragment;
import com.example.administrator.myapplication.fragment.my.UserInfoActivity;
import com.example.administrator.myapplication.fragment.my.myorder.fragment.OrderAllFragment;
import com.example.administrator.myapplication.login.LoginActivity;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.order.OrderActivity;
import com.example.administrator.myapplication.reg.RegActivity;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
import com.example.administrator.myapplication.shopcar.ShopCartActivity;
import com.example.administrator.myapplication.title.sousuo.xiangqing.SearchActivity;
import com.example.administrator.myapplication.xiangqing.ListActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/8 0008.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);
    void inject(RegActivity regActivity);
    void inject(HomeFragment homeFragment);
    void inject(ClassFragment classFragment);
    void inject(ListActivity listActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(ShopCartActivity shopCartActivity);
    void inject(OrderActivity orderActivity);
    void inject(UserInfoActivity userInfoActivity);
    void inject(CarFragment carFragment);
    void inject(SearchActivity searchActivity);
    void inject(OrderAllFragment orderAllFragment);



}
