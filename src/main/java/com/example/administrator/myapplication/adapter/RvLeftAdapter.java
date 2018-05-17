package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.inter.OnItemClickListener;


import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class RvLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CatagoryBean.DataBean> data;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListeren;
    public RvLeftAdapter(Context context, List<CatagoryBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvleft, parent, false);
        RvLeftViewHolder rvLeftViewHolder = new RvLeftViewHolder(view);
        return rvLeftViewHolder;
    }
    public void setOnItemClickListeren(OnItemClickListener onItemClickListeren){
        this.onItemClickListeren=onItemClickListeren;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CatagoryBean.DataBean dataBean = data.get(position);

        RvLeftViewHolder rvLeftViewHolder= (RvLeftViewHolder) holder;
        rvLeftViewHolder.tv.setText(dataBean.getName());

        //改变选中文字的颜色
        if(dataBean.isCheck()){
            rvLeftViewHolder.tv.setTextColor(Color.RED);
            rvLeftViewHolder.tv.setBackgroundColor(Color.GRAY);
        }else{
            rvLeftViewHolder.tv.setTextColor(Color.BLACK);
            rvLeftViewHolder.tv.setBackgroundColor(Color.WHITE);
        }

        rvLeftViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListeren!=null) {
                    onItemClickListeren.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RvLeftViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;

        public RvLeftViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public void setchange(int position,boolean bool){
        for (int i=0;i<data.size();i++){
            data.get(i).setCheck(false);
        }

        CatagoryBean.DataBean dataBean = data.get(position);
        dataBean.setCheck(bool);
        notifyDataSetChanged();
    }
}
