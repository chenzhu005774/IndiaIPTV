package com.amt.indiaiptv.utils.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cz on 2017/12/25.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteHelper dbhelper = null;
    public static SQLiteHelper getInstens(Context context) {
        if (dbhelper == null) {
            dbhelper = new SQLiteHelper(context);
        }
        return dbhelper;
    }
    private SQLiteHelper(Context context) {
        super(context, "datebase.db", null, 1);
    }
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       //创建一张表 对象信息做为 数据 code做为值。
        String sql_class_table="create table if not exists customertable(_id integer primary key autoincrement,customerdata text ,name varchar(50))";
        sqLiteDatabase.execSQL(sql_class_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }





}
