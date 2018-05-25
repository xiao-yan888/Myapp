package com.example.administrator.myapplication.title.sousuo.xiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.SearchAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.SearchBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
import com.example.administrator.myapplication.xiangqing.ListActivity;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    private RecyclerView mRv;
    public static final  int THREE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Intent intent = getIntent();
        String keywords = intent.getStringExtra("keywords");
        mPresenter.searchProducts(keywords);

    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder().build().inject(this);
    }

    @Override
    public void searchProductsSuccess(SearchBean searchBean) {
        List<SearchBean.DataBean> data = searchBean.getData();
        SearchAdapter adapter = new SearchAdapter(SearchActivity.this,data);
        mRv.setAdapter(adapter);
        adapter.setOnItemClickListeren(new SearchAdapter.OnItemListeren() {
            @Override
            public void OnClickItem(SearchBean.DataBean dataBean) {
                Intent intent = new Intent(SearchActivity.this,DetailsActivity.class);
                intent.putExtra("falg",THREE);
                intent.putExtra("bean",dataBean);
                //  intent.putExtra("bean",dataBean);
                startActivity(intent);
            }
        });
    }
}
