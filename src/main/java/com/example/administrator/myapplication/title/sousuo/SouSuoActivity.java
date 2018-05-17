package com.example.administrator.myapplication.title.sousuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.util.List;

public class SouSuoActivity extends AppCompatActivity {
    private MyView mv;
    private String mNames[] = {
            "笔记本","空气净化器","安卓手机",
            "超级大号圆珠笔","空气滤芯","超级大号钢笔",
            "气垫cc","防晒霜"
    };
    private MyViewGroup mvg;
    private Dao dao;
    private ListView lv;
    private Button del;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        mv = findViewById(R.id.mv);
        mvg = findViewById(R.id.mvg);
        lv = findViewById(R.id.lv);
        del = findViewById(R.id.del);
        dao = new Dao(SouSuoActivity.this);


        mv.setOnMyViewClickListener(new MyView.OnMyViewClickListener() {
            @Override
            public void onClickListener(View v) {
                String et_shop = mv.getEt_shop();
                Toast.makeText(SouSuoActivity.this,et_shop,Toast.LENGTH_LONG).show();
                dao.insert(et_shop);
                List<String> select = dao.select();
                ArrayAdapter arrayAdapter = new ArrayAdapter(SouSuoActivity.this,android.R.layout.simple_list_item_1,select);
                lv.setAdapter(arrayAdapter);


            }
        });


        initChildViews();
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deleteData();
                List<String> select = dao.select();
                ArrayAdapter arrayAdapter = new ArrayAdapter(SouSuoActivity.this,android.R.layout.simple_list_item_1,select);
                lv.setAdapter(arrayAdapter);
            }
        });
    }

    private void initChildViews() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextSize(15);
            view.setPadding(15,3,15,4);
            //view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            mvg.addView(view,lp);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mv.setEt_shop(mNames[finalI]);
                    dao.insert(mNames[finalI]);
                    List<String> select = dao.select();
                    ArrayAdapter arrayAdapter = new ArrayAdapter(SouSuoActivity.this,android.R.layout.simple_list_item_1,select);
                    lv.setAdapter(arrayAdapter);
                    Toast.makeText(SouSuoActivity.this,mNames[finalI],Toast.LENGTH_LONG).show();
                }
            });
        }





    }
}
