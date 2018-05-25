package com.example.administrator.myapplication.fragment.my.myorder.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.OrderListAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.OrdersBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.fragment.my.myorder.OrderListContract;
import com.example.administrator.myapplication.fragment.my.myorder.OrderListPresenter;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class OrderAllFragment extends BaseFragment<OrderListPresenter> implements OrderListContract.View {
    private XRecyclerView xRecyclerView;
    private RelativeLayout relative_empty_order;
    private OrderListAdapter adapter;
    private boolean isRefresh = true;//刷新状态
    private int page = 1;
    private  String uid;
    private  String token;
    @Override
    public int getContentLayout() {
        return R.layout.order_all_item;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder().build().inject(this);
    }

    @Override
    public void initView(View view) {
        xRecyclerView = view.findViewById(R.id.recycler_order);
        relative_empty_order = view.findViewById(R.id.relative_empty_order);

        //初始化Recycler
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        xRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //设置适配器
        adapter = new OrderListAdapter(getContext(),mPresenter);
        xRecyclerView.setAdapter(adapter);

        uid = (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
        token = (String) SharedPreferencesUtils.getParam(getContext(), "token", "");
        mPresenter.getOrders(uid, page + "", token);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isRefresh = true;
                mPresenter.getOrders(uid, page + "", token);
            }

            @Override
            public void onLoadMore() {
                page++;
                isRefresh = false;
                mPresenter.getOrders(uid, page + "", token);
            }
        });
    }

    @Override
    public void showOrders(List<OrdersBean.DataBean> list) {
        if (isRefresh) {
            if (adapter != null) {
                adapter.refresh(list);
                xRecyclerView.refreshComplete();

            }
        } else {
            if (adapter != null) {
                adapter.loadMore(list);
                xRecyclerView.loadMoreComplete();

            }
            //判断当前列表的数据，是否大于等于总条目
            //   smartRefreshLayout.setLoadmoreFinished(true);
        }
    }

    @Override
    public void updateOrderSuccess(BaseBean baseBean) {

    }
}
