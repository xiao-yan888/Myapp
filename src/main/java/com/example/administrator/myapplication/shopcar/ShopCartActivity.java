package com.example.administrator.myapplication.shopcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ElvShopcartAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.SellerBean;
import com.example.administrator.myapplication.bean.eventbus.MessageEvent;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.order.OrderActivity;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ShopCartActivity extends BaseActivity<ShopCartPresenter> implements ShopCartContract.View {

    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 合计：
     */
    private TextView mTvMoney;
    /**
     * 去结算：
     */
    private TextView mTvTotal;
    private ElvShopcartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        String token = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "token", "");
        String uid = (String) SharedPreferencesUtils.getParam(ShopCartActivity.this, "uid", "");
        mPresenter.getCarts(uid,token);
    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cbAll);
        mTvMoney = (TextView) findViewById(R.id.tvMoney);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter !=null){
                    adapter.changeAllState(mCbAll.isChecked());
                }
            }
        });

        mTvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopCartActivity.this, OrderActivity.class);
                startActivity(intent);
                List<List<GetCartsBean.DataBean.ListBean>> clist = adapter.getchildList();
                List<SellerBean> gList = adapter.getGroupList();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setcList(clist);
                messageEvent.setgList(gList);
                EventBus.getDefault().postSticky(messageEvent);



            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
////判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(this, groupList, childList,mPresenter);
        mElv.setAdapter(adapter);
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoney.setText("总计："+strings[0]+"元");
        mTvTotal.setText("去结算（"+strings[1]+"个）");
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Override
    public void updateCartsSuccess(String msg) {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void deleteCartSuccess(String msg) {
        //调用适配器里的delSuccess()方法
        if (adapter!=null){
            adapter.delSuccess();
        }
    }

    //* 判断所有商家是否全部选中
    public boolean isSellerAddSelected(List<SellerBean> groupList){
        for (int i = 0; i <groupList.size() ; i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()){
                return false;
            }
        }
        return true;
    }

}
