package com.example.administrator.myapplication.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.shangpinxiangqing.DetailsActivity;
import com.example.administrator.myapplication.title.TitleActivity;
import com.example.administrator.myapplication.WebActivity;
import com.example.administrator.myapplication.adapter.RvClassAdapter;
import com.example.administrator.myapplication.adapter.RvRecommendAdapter;
import com.example.administrator.myapplication.adapter.RvSecKillAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bean.AdBean;
import com.example.administrator.myapplication.bean.CatagoryBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.inter.OnItemClickListener;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.title.sousuo.SouSuoActivity;
import com.example.administrator.myapplication.utils.GlideImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomContract.View {
    private View view;
    private Banner banner;
    private RecyclerView rv_class;

    private MarqueeView marqueeView;
    private RecyclerView rv_miaosha;
    private RecyclerView rv_tiujian;
    private TitleActivity ta;
    public static final  int ONE=1;
    /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);
        return view;
    }*/

    @Override
    public int getContentLayout() {
        return R.layout.homefragment;
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
        banner = view.findViewById(R.id.banner);
       // ta = view.findViewById(R.id.ta);

        //轮播图
       GlideImageLoader loader = new GlideImageLoader();
        banner.setImageLoader(loader);
        rv_class = view.findViewById(R.id.rv_class);
        marqueeView = view.findViewById(R.id.marqueeView);
        rv_miaosha = view.findViewById(R.id.rv_miaosha);
        rv_tiujian = view.findViewById(R.id.rv_tiujian);
        ta = view.findViewById(R.id.ta);
        //跑马灯
        initMarqueeView();
        //设置布局管理
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false);
        rv_class.setLayoutManager(gridLayoutManager);
        //设置布局管理
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),1,RecyclerView.HORIZONTAL,false);
        rv_miaosha.setLayoutManager(gridLayoutManager1);
        //设置布局管理
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        rv_tiujian.setLayoutManager(gridLayoutManager2);
        //请求数据
        initData();
        //点击标题
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
                //分享
                Toast.makeText(getContext(),"分享",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClicksou(View v) {
                //搜索

                Intent intent = new Intent(getContext(), SouSuoActivity.class);
                startActivity(intent);




                Toast.makeText(getContext(),"搜索",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initMarqueeView() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("你妈妈在旁边看着呢");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有时间睡觉了");
        marqueeView.startWithList(info);

    }
    //获取数据
    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }


    @Override
    public void getAdSuccess(final AdBean adBean) {
      final   List<AdBean.DataBean> data = adBean.getData();
        final List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = data.get(position).getUrl();
                if (!TextUtils.isEmpty(url)) {
                   Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("detailUrl", url);
                    startActivity(intent);
                }
            }
        });

        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rv_miaosha.setAdapter(rvSecKillAdapter);
        rvSecKillAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String detailUrl = adBean.getMiaosha().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("detailUrl", detailUrl);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getActivity(), adBean.getTuijian().getList());
        rv_tiujian.setAdapter(rvRecommendAdapter);

        rvRecommendAdapter.setOnItemClickListeren(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              //  String detailUrl = adBean.getTuijian().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                AdBean.TuijianBean.ListBean listBean = adBean.getTuijian().getList().get(position);
                intent.putExtra("bean", listBean);
                intent.putExtra("falg",ONE);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }
    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter adapter = new RvClassAdapter(getActivity(), data);
        rv_class.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               /* String icon = data.get(position).getIcon();
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("detailUrl", icon);
                startActivity(intent);*/
                Toast.makeText(getContext(), data.get(position).getName(), Toast.LENGTH_SHORT).show();
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
