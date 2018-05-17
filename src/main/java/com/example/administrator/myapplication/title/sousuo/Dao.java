package com.example.administrator.myapplication.title.sousuo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context) {
        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();

    }
    //插入数据
    public void insert(String sname){
        ContentValues values = new ContentValues();
        values.put("name",sname);
        db.insert("shops",null,values);
    }

    //查询
    public List<String> select(){
        List<String> list = new ArrayList<>();

        Cursor cursor = db.query("shops", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

    public void deleteData() {

        db.delete("shops",null,null);
    }
}
