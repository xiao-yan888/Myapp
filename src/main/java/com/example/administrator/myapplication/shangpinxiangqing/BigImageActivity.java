package com.example.administrator.myapplication.shangpinxiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class BigImageActivity extends AppCompatActivity {

    private PhotoView mImg;
    private String[] split;
    private List<String> list = new ArrayList<>();
    private MyViewPager vp;
    /**
     * 1
     */
    private TextView mTvCount;
    private int position;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        position = intent.getIntExtra("position", -1);
        split = image.split("\\|");
        for (int i = 0; i < split.length; i++) {

            list.add(split[i]);
        }

        count = list.size();
        initView();


    }

    private void initView() {
        // mImg = findViewById(R.id.img);
        vp = findViewById(R.id.mviewpager);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        //创建适配器
        ViewPagerAdapter myAdapter = new ViewPagerAdapter(list, BigImageActivity.this);
        vp.setAdapter(myAdapter);
        vp.setCurrentItem(position);

        mTvCount.setText((position + 1) + "/" + count);
      /*  ViewPagerAdapter adapter = new ViewPagerAdapter(list, BigImageActivity.this);
        vp.setAdapter(adapter);*/
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                BigImageActivity.this.position=p;
                mTvCount.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
