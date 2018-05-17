package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.xiangqing.ListActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;
import com.example.administrator.myapplication.inter.OnItemClickListener;


import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> titlelist;
    private  List<List<ProductCatagoryBean.DataBean.ListBean>> childlist;
    private final LayoutInflater inflater;

    public ElvAdapter(Context context, List<String> titlelist, List<List<ProductCatagoryBean.DataBean.ListBean>> childlist) {
        this.context = context;
        this.titlelist = titlelist;
        this.childlist = childlist;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getGroupCount() {
        return titlelist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return titlelist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childlist.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
       TitleViewHolder titleViewHolder;
        if (view == null) {
            titleViewHolder = new TitleViewHolder();
            view = inflater.inflate(R.layout.titview, null);
            titleViewHolder.tv_tit = view.findViewById(R.id.tv_tit);
            view.setTag(titleViewHolder);
        } else {
            titleViewHolder = (TitleViewHolder) view.getTag();
        }
        //显示数据
        titleViewHolder.tv_tit.setText(titlelist.get(i));
        return view;

    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view == null) {
            childViewHolder = new ChildViewHolder();
            view = inflater.inflate(R.layout.elvrv, null);
            childViewHolder.rv = view.findViewById(R.id.rv);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        //显示数据
        final List<ProductCatagoryBean.DataBean.ListBean> listBeans = childlist.get(i);
        childViewHolder.rv.setLayoutManager(new GridLayoutManager(context,3));
        //设置适配器
        ElvRvAdapter adapter = new ElvRvAdapter(context,listBeans);
        childViewHolder.rv.setAdapter(adapter);
        adapter.setOnItemClickListeren(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("pscid",listBeans.get(position).getPscid());
                context.startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
        // titleViewHolder.tv_tit.setText(titlelist.get(i));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class TitleViewHolder{
        TextView tv_tit;
    }
    class ChildViewHolder{
        RecyclerView rv;
      /*  ImageView child_img;
        TextView child_title;*/
    }

}
