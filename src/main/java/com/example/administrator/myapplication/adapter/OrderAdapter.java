package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.GetCartsBean;
import com.example.administrator.myapplication.bean.SellerBean;
import com.example.administrator.myapplication.order.addr.OrderPresenter;
import com.example.administrator.myapplication.shopcar.AddSubLayout;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class OrderAdapter extends BaseExpandableListAdapter {
    private final String token;
    private Context context;
    private List<SellerBean> groupList;
    private List<List<GetCartsBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;

    private OrderPresenter mPresenter;
    private final String uid;

    public OrderAdapter(Context context, List<SellerBean> groupList, List<List<GetCartsBean.DataBean
            .ListBean>> childList, OrderPresenter mPrsenter) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;

        this.mPresenter = mPrsenter;
        inflater = LayoutInflater.from(context);

        //获取UID和token值
        uid = (String) SharedPreferencesUtils.getParam(context, "uid", "");
        token = (String) SharedPreferencesUtils.getParam(context, "token", "");
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = inflater.inflate(R.layout.shopcart_seller_item, null);
            groupViewHolder.cbSeller = convertView.findViewById(R.id.cbSeller);
            groupViewHolder.tvSeller = convertView.findViewById(R.id.tvSeller);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        //设置值
        SellerBean sellerBean = groupList.get(groupPosition);
        groupViewHolder.tvSeller.setText(sellerBean.getSellerName());
        groupViewHolder.cbSeller.setChecked(sellerBean.isSelected());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.shopcart_seller_product_item, null);
            childViewHolder.cbProduct = convertView.findViewById(R.id.cbProduct);
            childViewHolder.iv = convertView.findViewById(R.id.iv);
            childViewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            childViewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
            childViewHolder.tvDel = convertView.findViewById(R.id.tvDel);
            childViewHolder.addSubLayout = convertView.findViewById(R.id.add_sub);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        final GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        //根据服务器返回的select值，给checkBox设置是否选中
        childViewHolder.cbProduct.setChecked(listBean.getSelected()==1?true:false);
        childViewHolder.tvTitle.setText(listBean.getTitle());
        childViewHolder.tvPrice.setText(listBean.getPrice() + "");
        childViewHolder.addSubLayout.setNum(listBean.getNum()+"");
        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(childViewHolder.iv);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class GroupViewHolder {
        CheckBox cbSeller;
        TextView tvSeller;
    }

    class ChildViewHolder {
        CheckBox cbProduct;
        ImageView iv;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDel;
        AddSubLayout addSubLayout;
    }
}
