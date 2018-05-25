package com.example.administrator.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ElvShopcartAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.SellerBean;
import com.example.administrator.myapplication.bean.eventbus.MessageEvent;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.order.OrderActivity;
import com.example.administrator.myapplication.shopcar.ShopCartActivity;
import com.example.administrator.myapplication.shopcar.ShopCartContract;
import com.example.administrator.myapplication.shopcar.ShopCartPresenter;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class CarFragment extends BaseFragment<ShopCartPresenter>  implements ShopCartContract.View{
   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carfragment, container, false);
        return view;
    }
*/
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
    public int getContentLayout() {
        return R.layout.carfragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {

        mElv = (ExpandableListView)view.findViewById(R.id.elv);
        mCbAll = (CheckBox) view.findViewById(R.id.cbAll);
        mTvMoney = (TextView) view.findViewById(R.id.tvMoney);
        mTvTotal = (TextView) view.findViewById(R.id.tvTotal);
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
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                List<List<GetCartsBean.DataBean.ListBean>> clist = adapter.getchildList();
                List<SellerBean> gList = adapter.getGroupList();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setcList(clist);
                messageEvent.setgList(gList);
                EventBus.getDefault().postSticky(messageEvent);



            }
        });

        String token = (String) SharedPreferencesUtils.getParam(getContext(), "token", "");
        String uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
        mPresenter.getCarts(uid,token);
    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
////判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(getContext(), groupList, childList,mPresenter);
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
