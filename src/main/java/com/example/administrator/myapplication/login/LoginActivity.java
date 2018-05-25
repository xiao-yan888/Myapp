package com.example.administrator.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.UserBean;

import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.fragment.home.HomeFragment;
import com.example.administrator.myapplication.fragment.my.UserInfoActivity;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.reg.RegActivity;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
import com.example.administrator.myapplication.shopcar.ShopCartActivity;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    private int shop;
    private ImageView mMqq;
    private Intent intent;
    private EditText et_mobile;
    private EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        shop = intent.getIntExtra("shop", -1);
        initView();
    }

   /* public void inject(){
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }*/

    private void initView() {
        et_mobile = findViewById(R.id.et_mobile);
        et_pass = findViewById(R.id.et_pass);
        Button login = findViewById(R.id.login);
        Button reg = findViewById(R.id.reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = et_mobile.getText().toString();
                String pass = et_pass.getText().toString();
                mPresenter.login(mobile, pass);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);

            }
        });
        mMqq = (ImageView) findViewById(R.id.mqq);
         mMqq.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(LoginActivity.this,"第三方登录",Toast.LENGTH_LONG).show();
                 UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);

             }
         });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //分享或者登录的结果交给友盟处理
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    public String getName() {
        return et_mobile.getText().toString();
    }

    @Override
    public String getPass() {
        return et_pass.getText().toString();
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        UserBean.DataBean data = userBean.getData();
        String mobile = data.getMobile();
        SharedPreferencesUtils.setParam(this, "uid", userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(this, "name", userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(this, "iconUrl", userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(this, "token", userBean.getData().getToken() + "");

        //保存用户信息到SharedPreferences
      /*  SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("uid", userBean.getData().getUid() + "");
        edit.putString("name", userBean.getData().getUsername() + "");
        edit.putString("iconUrl", userBean.getData().getIcon() + "");
        edit.putString("token", userBean.getData().getToken() + "");
        edit.commit();*/


        if (shop == DetailsActivity.ADDSHOPCAR) {
            //关闭当前页面
            this.finish();
        } else if(shop==DetailsActivity.SHOPCAR){
            this.finish();
            Intent intent = new Intent(LoginActivity.this, ShopCartActivity.class);
            startActivity(intent);
        }else if(shop== UserInfoActivity.USER){
            this.finish();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
           /* Bundle bundle = new Bundle();
            bundle.putString("name", mobile);

            intent.putExtras(bundle);
            setResult(2, intent);//返回值调用函数，其中2为resultCode，返回值的标志*/
            this.finish();//传值结束
        }


        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
    }


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key + ":");
                sb.append(value + "\n");
            }
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("name", sb.toString());

            intent.putExtras(bundle);
            setResult(2, intent);//返回值调用函数，其中2为resultCode，返回值的标志
            finish();//传值结束


            //  mTv.setText(sb.toString());
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };



}
