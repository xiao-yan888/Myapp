package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.ProductCatagoryBean;
import com.example.administrator.myapplication.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class ElvRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductCatagoryBean.DataBean.ListBean> listBeans;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListeren;
    public ElvRvAdapter(Context context, List<ProductCatagoryBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
        inflater=LayoutInflater.from(context);
    }
public void setOnItemClickListeren(OnItemClickListener onItemClickListeren){
        this.onItemClickListeren=onItemClickListeren;

}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.childview, parent, false);
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = new ElvRvAdapterViewHolder(view);
        return elvRvAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = (ElvRvAdapterViewHolder) holder;
        ProductCatagoryBean.DataBean.ListBean listBean = listBeans.get(position);
        //Glide.with(context).load(listBean.getIcon()).into(elvRvAdapterViewHolder.iv);
        elvRvAdapterViewHolder.iv.setImageURI(listBean.getIcon());
        elvRvAdapterViewHolder.tv.setText(listBean.getName());
        elvRvAdapterViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListeren!=null){
                    onItemClickListeren.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class ElvRvAdapterViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv;
        private final LinearLayout ll;

        public ElvRvAdapterViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.child_img);
            tv = itemView.findViewById(R.id.child_title);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
