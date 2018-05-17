package com.example.administrator.myapplication.component;

import com.example.administrator.myapplication.fragment.classtwo.ClassFragment;
import com.example.administrator.myapplication.fragment.home.HomeFragment;
import com.example.administrator.myapplication.login.LoginActivity;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.reg.RegActivity;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
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
}
