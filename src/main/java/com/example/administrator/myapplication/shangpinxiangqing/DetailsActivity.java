package com.example.administrator.myapplication.shangpinxiangqing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.AdBean;
import com.example.administrator.myapplication.bean.BaseBean;
import com.example.administrator.myapplication.bean.DetailsBean;
import com.example.administrator.myapplication.bean.SearchBean;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.login.LoginActivity;
import com.example.administrator.myapplication.module.HttpModule;
import com.example.administrator.myapplication.shopcar.ShopCartActivity;
import com.example.administrator.myapplication.title.sousuo.xiangqing.SearchActivity;
import com.example.administrator.myapplication.utils.GlideImageLoader;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;
import com.example.administrator.myapplication.xiangqing.ListActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsActivity extends BaseActivity<AddCarPresenter> implements AddCarContract.View {

    private ImageView mIvShare;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    /**
     * 购物车
     */
    private TextView mTvShopCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private DetailsBean.DataBean bean;
    private int falg;
    private AdBean.TuijianBean.ListBean lsitbean;
    private String images;
    private int salenum;
    private double price;
    private String title;
    private int pid;
    public static final int SHOPCAR=0;
    public static final int ADDSHOPCAR=1;
    private String uid;
    private SearchBean.DataBean searchbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        falg = intent.getIntExtra("falg",-1);
        if (falg==-1){
            return;
        }
        if (falg==ListActivity.TWO){
            bean = (DetailsBean.DataBean) intent.getSerializableExtra("bean");
            images = bean.getImages();
            title = bean.getTitle();
            salenum = bean.getSalenum();
            price = bean.getPrice();
            pid = bean.getPid();

        }else if(falg== SearchActivity.THREE){
            searchbean = (SearchBean.DataBean) intent.getSerializableExtra("bean");
            images = searchbean.getImages();
            title = searchbean.getTitle();
            salenum = searchbean.getSalenum();
            price = searchbean.getPrice();
            pid= searchbean.getPid();
        }else{
            lsitbean = (AdBean.TuijianBean.ListBean) intent.getSerializableExtra("bean");
            images = lsitbean.getImages();
            title = lsitbean.getTitle();
            salenum = lsitbean.getSalenum();
            price = lsitbean.getPrice();
            pid= lsitbean.getPid();
        }

        //if (falg== )

        initView();
        setData();
    }

    private void initView() {
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mTvShopCard = (TextView) findViewById(R.id.tvShopCard);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mTvAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        uid = (String) SharedPreferencesUtils.getParam(this, "uid", "-1");

        mTvShopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("-1".equals(uid)) {
                    //跳转到登录页面
                    Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                    intent.putExtra("shop",SHOPCAR);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(DetailsActivity.this, ShopCartActivity.class);
                    startActivity(intent);

                }


            }
        });

    }

    private void addCard() {
        //判断用户是否登录
        //判断的逻辑是，从SharedPreferences里取uid，如果能取到说明已经登录过，否则跳转到登录页面
      /*  SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");*/

       // String uid  = (String) SharedPreferencesUtils.getParam(this, "uid", "-1");

        if ("-1".equals(uid)) {
            //跳转到登录页面
            Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
            intent.putExtra("shop",ADDSHOPCAR);
            startActivity(intent);
        } else {
            //添加购物车
            String token  = (String) SharedPreferencesUtils.getParam(this, "token", "");

            //String token = sharedPreferences.getString("token", "");
           // Toast.makeText(DetailsActivity.this,"添加购物车",Toast.LENGTH_LONG).show();
          //  addCartPresenterImp.addCart(uid, bean.getPid() + "", token);
                mPresenter.addCart(uid,pid+"",token);

        }
    }

    private void setData() {

        mBanner.setImageLoader(new GlideImageLoader());
        String[] split = images.split("\\|");
        mBanner.setImages(Arrays.asList(split));
        mBanner.start();
        mTvTitle.setText(title);
        //给原价加横线
        SpannableString spannableString = new SpannableString("原价:" + salenum);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spannableString);
        mTvVipPrice.setText("现价："+price);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                Intent intent = new Intent(DetailsActivity.this,BigImageActivity.class);
                intent.putExtra("image",images);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getAddcarSuccess(BaseBean baseBean) {
        Toast.makeText(DetailsActivity.this,baseBean.getMsg(),Toast.LENGTH_LONG).show();


    }
}
