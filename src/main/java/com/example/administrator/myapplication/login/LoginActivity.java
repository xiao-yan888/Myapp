package com.example.administrator.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.UserBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.reg.RegActivity;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import dagger.internal.DaggerCollections;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

   /* public void inject(){
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }*/

    private void initView() {
        final EditText et_mobile = findViewById(R.id.et_mobile);
        final EditText et_pass = findViewById(R.id.et_pass);
        Button login = findViewById(R.id.login);
        Button reg = findViewById(R.id.reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = et_mobile.getText().toString();
                String pass = et_pass.getText().toString();
                mPresenter.login(mobile,pass);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void loginSuccess(UserBean userBean) {

        SharedPreferencesUtils.setParam(this,"uid",userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(this,"name",userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(this,"iconUrl",userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(this,"token",userBean.getData().getToken() + "");

        //保存用户信息到SharedPreferences
      /*  SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("uid", userBean.getData().getUid() + "");
        edit.putString("name", userBean.getData().getUsername() + "");
        edit.putString("iconUrl", userBean.getData().getIcon() + "");
        edit.putString("token", userBean.getData().getToken() + "");
        edit.commit();*/
        //关闭当前页面
        this.finish();


        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
