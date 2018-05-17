package com.example.administrator.myapplication.fragment.classtwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ElvAdapter;
import com.example.administrator.myapplication.adapter.RvLeftAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.inter.OnItemClickListener;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.title.TitleActivity;
import com.example.administrator.myapplication.title.sousuo.SouSuoActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class ClassFragment extends BaseFragment<ClassPresenter> implements ClassContract.View {

    private RecyclerView rvleft;
    private ImageView img;
    private ExpandableListView elv;

    private RvLeftAdapter rvLeftAdapter;
    private TitleActivity ta;

/*    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classfragment, container, false);
        return view;
    }*/

    @Override
    public int getContentLayout() {
        return R.layout.classfragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        rvleft = view.findViewById(R.id.rvleft);
        img = view.findViewById(R.id.img);
        elv = view.findViewById(R.id.elv);
        ta = view.findViewById(R.id.ta);
        img.setImageResource(R.drawable.timg);

        ta.setOnItemTitleClickListener(new TitleActivity.OnItemTitleClickListener() {
            @Override
            public void onClickleft(View v) {
                //二维码
                Toast.makeText(getContext(),"二维码",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 1);
            }

            @Override
            public void onClickrigth(View v) {

            }

            @Override
            public void onClicksou(View v) {
                //搜索

                Intent intent = new Intent(getContext(), SouSuoActivity.class);
                startActivity(intent);

            }
        });

        mPresenter.getCatagory();
    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {

        List<String> titlelist = new ArrayList<>();
        List<List<ProductCatagoryBean.DataBean.ListBean>> childlist=new ArrayList<>();
       // ProductCatagoryBean childBean = new Gson().fromJson(result, ProductCatagoryBean.class);
       // List<ProductCatagoryBean.DataBean> data = childBean.getData();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i = 0; i < data.size(); i++){
            String name = data.get(i).getName();
            titlelist.add(name);
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childlist.add(list);
        }

        ElvAdapter adapter = new ElvAdapter(getContext(),titlelist,childlist);
        elv.setAdapter(adapter);
        //默认展开列表
        for (int i = 0; i < titlelist.size(); i++) {
            elv.expandGroup(i);
        }
        Log.e("zzzz",productCatagoryBean.getMsg());
    }

    @Override
    public void getCatagorySuccess(final CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        final int cid = catagoryBean.getData().get(0).getCid();
        rvleft.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        rvleft.addItemDecoration(decoration);
        rvLeftAdapter = new RvLeftAdapter(getActivity(),data);
        rvleft.setAdapter(rvLeftAdapter);
        mPresenter.getProductCatagory(cid+"");
        rvLeftAdapter.setchange(0,true);
        rvLeftAdapter.setOnItemClickListeren(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                rvLeftAdapter.setchange(position,true);
                mPresenter.getProductCatagory(catagoryBean.getData().get(position).getCid()+"");
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String string = bundle.getString(CodeUtils.RESULT_STRING);
                //拿到最终结果
                //Intent intent = new Intent(getContext(),WebViewActivity.class);

            }
        }
    }

}
