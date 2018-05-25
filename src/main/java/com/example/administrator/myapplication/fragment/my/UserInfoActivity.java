package com.example.administrator.myapplication.fragment.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.component.DaggerHttpComponent;
import com.example.administrator.myapplication.login.LoginActivity;
import com.example.administrator.myapplication.utils.SharedPreferencesUtils;

import java.io.File;

public class UserInfoActivity extends BaseActivity<UpdatePresenter> implements View.OnClickListener,UpdateHeaderContract.View {
    private String name;
    private ImageView mIv;
    private TextView mTv;
    /**
     * 退出登录
     */
    private Button mBt;
    public static final int USER=3;
    private PopupWindow window;
    private String path = Environment.getExternalStorageDirectory()+"/tou.png";
    private Bitmap bitmap;
    private Bitmap bitmap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = (String) SharedPreferencesUtils.getParam(this, "name", "");
        String iconUrl = (String) SharedPreferencesUtils.getParam(this, "iconUrl", "");
        initView();
        mTv.setText(name);
        Glide.with(this).load(iconUrl).into(mIv);



    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mIv.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv:
                View inflate =  LayoutInflater.from(UserInfoActivity.this).inflate( R.layout.popup_item, null);
                // PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //  popupWindow.setContentView(inflate);

                initpopup(inflate);
                window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                // 设置PopupWindow的背景
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // 设置PopupWindow是否能响应外部点击事件
                window.setOutsideTouchable(true);
                // 设置PopupWindow是否能响应点击事件
                window.setTouchable(true);
                window.showAtLocation(inflate, Gravity.CENTER,0,0);



                break;
            case R.id.bt:
                //清空SharedPreferences
                SharedPreferencesUtils.clear(UserInfoActivity.this);
                //回到登录页面
                Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("shop",USER);
                startActivity(intent);
                UserInfoActivity.this.finish();


                break;
        }
    }

    private void initpopup(View inflate) {
        TextView xiangji = inflate.findViewById(R.id.xiangji);
        TextView xiangce = inflate.findViewById(R.id.xiangce);
        TextView quxiao = inflate.findViewById(R.id.quxiao);

        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调取系统的相册  Intent.ACTION_PICK相册
                Intent it = new Intent(Intent.ACTION_PICK);
                //设置格式
                it.setType("image/*");
                startActivityForResult(it, 3000);
                window.dismiss();
            }
        });


        xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //在Sdcard 中创建文件 存入图片
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                //1.意图   2.requestCode 请求码
                startActivityForResult(it, 1000);
                window.dismiss();
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1000 && resultCode ==RESULT_OK){
            //调取裁剪功能  om.android.camera.action.CROP 裁剪的Action
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到图片设置类型
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //是否支持裁剪 设置 true 支持  false 不支持
            it.putExtra("CROP", true);
            //设置比例大小  1:1
            it.putExtra("aspectX", 1);
            it.putExtra("aspectY", 1);
            //输出的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将裁剪好的图片进行返回到Intent中
            it.putExtra("return-data", true);
            startActivityForResult(it, 2000);
        }
        //点击完裁剪的完成以后会执行的方法
        if(requestCode == 2000 && resultCode == RESULT_OK){

            bitmap = data.getParcelableExtra("data");
            String uid = (String) SharedPreferencesUtils.getParam(this, "uid", "");
            mPresenter.updateHeader(uid,path);
           // mIv.setImageBitmap(bitmap);



        }

//得到相册里的图片进行裁剪
        if(requestCode == 3000 && resultCode == RESULT_OK){
            //得到相册图片
            Uri uri = data.getData();
            //裁剪
            Intent it = new Intent("com.android.camera.action.CROP");
            //设置图片 以及格式
            it.setDataAndType(uri, "image/*");
            //是否支持裁剪
            it.putExtra("crop", true);
            //设置比例
            it.putExtra("aspectX", 1);
            it.putExtra("aspectY", 1);
            //设置输出的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //是否支持人脸识别
//    			it.putExtra("onFaceDetection", true);
            //返回
            it.putExtra("return-data", true);
            startActivityForResult(it, 4000);
        }

        //2.点击裁剪完成
        if(requestCode == 4000 && resultCode == RESULT_OK){
            bitmap1 = data.getParcelableExtra("data");
            String uid = (String) SharedPreferencesUtils.getParam(this, "uid", "");
            mPresenter.updateHeader(uid,path);
            //mIv.setImageBitmap(bitmap);

        }


    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder().build().inject(this);
    }

    @Override
    public void updateSuccess(String code) {
        Log.e("zzzzz",code);
        if ("0".equals(code) && bitmap != null) {
            //toast("上传成功");
            //去设置头像
            mIv.setImageBitmap(bitmap);


        }
        if ("0".equals(code) && bitmap1!=null){
            mIv.setImageBitmap(bitmap1);
        }
    }
}
