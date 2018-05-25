package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.DetailsBean;
import com.example.administrator.myapplication.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> data;
    private final LayoutInflater inflater;
    private OnItemListeren onItemListeren;
    public SearchAdapter(Context context, List<SearchBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    public interface OnItemListeren{
        void OnClickItem(SearchBean.DataBean dataBean);
    }
    public void setOnItemClickListeren(OnItemListeren onItemListeren){
        this.onItemListeren=onItemListeren;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sousuoxiangqing, parent, false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final SearchBean.DataBean dataBean = data.get(position);
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.tvPrice.setText(dataBean.getPrice()+"");
        searchViewHolder.tvTitle.setText(dataBean.getTitle()+"");
        // Glide.with(context).load(dataBean.getImages().split("\\|")[0]).into(detailsViewHolder.img);
        searchViewHolder.img.setImageURI(dataBean.getImages().split("\\|")[0]);
       searchViewHolder.linear.setOnClickListener(new View.OnClickListener() {
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

    class SearchViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView tvTitle;
        private final TextView tvPrice;
        private final LinearLayout linear;

        public SearchViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            linear = itemView.findViewById(R.id.linear);
        }
    }

}
