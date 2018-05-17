package com.example.administrator.myapplication.title.sousuo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table shops(id integer primary key autoincrement,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
