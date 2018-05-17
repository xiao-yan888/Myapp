package com.example.administrator.myapplication.reg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.module.HttpModule;

public class RegActivity extends BaseActivity<RegPresenter> implements View.OnClickListener,RegContract.View {

    private EditText mEtMobile;
    private EditText mEtPass;
    /**
     * 确定注册
     */
    private Button mQureg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_reg;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }



    public void initView() {
        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mEtPass = (EditText) findViewById(R.id.et_pass);
        mQureg = (Button) findViewById(R.id.qureg);
        mQureg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.qureg:
                String mobile = mEtMobile.getText().toString();
                String password = mEtPass.getText().toString();
                mPresenter.reg(mobile, password);
                break;
        }
    }

    @Override
    public void regSuccess() {
        Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
