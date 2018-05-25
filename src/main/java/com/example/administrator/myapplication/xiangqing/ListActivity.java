package com.example.administrator.myapplication.xiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.RvDetailsAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.DetailsBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity<DetailPresenter> implements DetailContract.View {
    private int pscid;
    private XRecyclerView mXrv;
    private boolean isRefresh = true;
    private RvDetailsAdapter adapter;
    public static final  int TWO=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        setContentView(R.layout.activity_list);
        initView();
        mPresenter.getProducts(pscid+"");
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder().httpModule(new HttpModule()).build().inject(this);
    }

    @Override
    public void getProductsSuccess(DetailsBean detailsBean) {
        List<DetailsBean.DataBean> data = detailsBean.getData();
        List<DetailsBean.DataBean> dataall=new ArrayList<>();
        dataall.addAll(data);
        if (isRefresh){
            adapter = new RvDetailsAdapter(this, data);
            mXrv.setAdapter(adapter);
            adapter.refresh(dataall);
            mXrv.refreshComplete();
        }else {
            if (adapter !=null){
                adapter.loadMore(dataall);
                mXrv.loadMoreComplete();
            }
        }
     /*   RvDetailsAdapter adapter = new RvDetailsAdapter(ListActivity.this, data);
        mXrv.setAdapter(adapter);*/
        adapter.setOnItemClickListeren(new RvDetailsAdapter.OnItemListeren() {
            @Override
            public void OnClickItem( DetailsBean.DataBean dataBean) {
                Intent intent = new Intent(ListActivity.this,DetailsActivity.class);
                intent.putExtra("falg",TWO);
                intent.putExtra("bean",dataBean);
                //  intent.putExtra("bean",dataBean);
                startActivity(intent);

            }
        });


    }

    private void initView() {
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        mXrv.setLayoutManager(new LinearLayoutManager(this));
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                mPresenter.getProducts(pscid+"");
            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                mPresenter.getProducts(pscid+"");
            }
        });
    }
}
