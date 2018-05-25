package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.OrdersBean;
import com.example.administrator.myapplication.fragment.my.myorder.OrderListPresenter;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrdersBean.DataBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private AlertDialog dialog;
    private OrderListPresenter orderListPresenter;
    private final String uid;
    private final String token;

    public OrderListAdapter(Context context, OrderListPresenter orderListPresenter) {
        this.context = context;
        this.orderListPresenter = orderListPresenter;
        inflater = LayoutInflater.from(context);
        uid = (String) SharedPreferencesUtils.getParam(context, "uid", "");
        token = (String) SharedPreferencesUtils.getParam(context, "token", "");
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        final OrdersBean.DataBean dataBean = list.get(position);
        orderViewHolder.text_title.setText(dataBean.getTitle());
        orderViewHolder.text_price.setText("价格:" + dataBean.getPrice());

        //0 待支付1 已支付2 已取消
        int status = dataBean.getStatus();
        if (status == 0) {
            orderViewHolder.text_flag.setText("待支付");
            orderViewHolder.order_button.setText("取消订单");
        } else if (status == 1) {
            orderViewHolder.text_flag.setText("已支付");
            orderViewHolder.order_button.setText("查看订单");
        } else if (status == 2) {
            orderViewHolder.text_flag.setText("已取消");
            orderViewHolder.order_button.setText("查看订单");
        }

        orderViewHolder.text_time.setText("创建时间:" + dataBean.getCreatetime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView text_title;
        public TextView text_price;
        public TextView text_flag;
        public TextView text_time;
        public Button order_button;

        public OrderViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            text_price = itemView.findViewById(R.id.text_price);
            text_flag = itemView.findViewById(R.id.text_flag);
            text_time = itemView.findViewById(R.id.text_time);
            order_button = itemView.findViewById(R.id.order_button);
        }
    }
    public void refresh(List<OrdersBean.DataBean> data) {
        if (list != null) {
            list.clear();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<OrdersBean.DataBean> data) {
        if (list != null) {
            list.addAll(data);
            notifyDataSetChanged();
        }
    }
}
