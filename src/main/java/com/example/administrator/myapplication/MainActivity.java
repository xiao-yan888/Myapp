package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.fragment.CarFragment;
import com.example.administrator.myapplication.fragment.classtwo.ClassFragment;
import com.example.administrator.myapplication.fragment.FindFragment;
import com.example.administrator.myapplication.fragment.home.HomeFragment;
import com.example.administrator.myapplication.fragment.my.MainFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mBtbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



    }


    private void initView() {
        mBtbar = (BottomTabBar) findViewById(R.id.btbar);

        mBtbar.init(getSupportFragmentManager())
                /*.setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)*/
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem(" ",R.drawable.ac0, HomeFragment.class)
                .addTabItem("  ",R.drawable.abw, ClassFragment.class)
                .addTabItem("   ",R.drawable.aby, FindFragment.class)
                .addTabItem("    ",R.drawable.abu, CarFragment.class)
                .addTabItem("     ",R.drawable.ac2, MainFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                    }
                });

    }
}
