package com.example.administrator.myapplication.app;

import android.app.Application;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyApp extends Application {
    {
        //  PlatformConfig.setWeixin("wx396ea2b17e2f8938", "a33aae6c6649257cbb48de80ddb0bf90");
        PlatformConfig.setQQZone("1106856414", "dHByyWjizkOJihqy");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
        //初始化Zxing库
        ZXingLibrary.initDisplayOpinion(this);
        UMConfigure.init(this, "5ada9fbbb27b0a700b000182", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

    }
}
