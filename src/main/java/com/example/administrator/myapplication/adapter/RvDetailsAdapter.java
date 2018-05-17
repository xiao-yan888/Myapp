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
import com.example.administrator.myapplication.bean.DetailsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25 0025.
 */

public class RvDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<DetailsBean.DataBean> data;
    private final LayoutInflater inflater;
    private OnItemListeren onItemListeren;
    public RvDetailsAdapter(Context context, List<DetailsBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    public interface OnItemListeren{
        void OnClickItem(DetailsBean.DataBean dataBean);
    }
public void setOnItemClickListeren(OnItemListeren onItemListeren){
        this.onItemListeren=onItemListeren;
}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(view);
        return detailsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DetailsBean.DataBean dataBean = data.get(position);
        DetailsViewHolder detailsViewHolder = (DetailsViewHolder) holder;
        detailsViewHolder.tvPrice.setText(dataBean.getPrice()+"");
        detailsViewHolder.tvTitle.setText(dataBean.getTitle()+"");
       // Glide.with(context).load(dataBean.getImages().split("\\|")[0]).into(detailsViewHolder.img);
        detailsViewHolder.img.setImageURI(dataBean.getImages().split("\\|")[0]);
        detailsViewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListeren.OnClickItem(dataBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class DetailsViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView tvTitle;
        private final TextView tvPrice;
        private final LinearLayout linear;

        public DetailsViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            linear = itemView.findViewById(R.id.linear);
        }
    }

//刷新
    public void refresh(List<DetailsBean.DataBean> list){
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }
//加载
public void loadMore(List<DetailsBean.DataBean> list){
    this.data.addAll(list);
    notifyDataSetChanged();
}
}
