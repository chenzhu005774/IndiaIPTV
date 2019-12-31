package com.amt.indiaiptv.utils.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.amt.indiaiptv.utils.LogUtils;
import com.amt.indiaiptv.utils.bean.CustomerEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by cz on 2017/12/25.
 */

public class CustomerInfoDB {
       Context context;

    public CustomerInfoDB(Context context){
        this.context = context;
    }
    /**
     *@author chenzhu
     *creat at 2017/12/25 14:17
     *保存一条记录
     * @param customerEntity 保存的对象已text形式保存
     */
    public void saveObject(CustomerEntity customerEntity) {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(customerEntity);
            objectOutputStream.flush();
            byte data[] = arrayOutputStream.toByteArray();
            objectOutputStream.close();
            arrayOutputStream.close();
            SQLiteHelper dbhelper = SQLiteHelper.getInstens(context);
            SQLiteDatabase database = dbhelper.getWritableDatabase();
            LogUtils.d("chenzhu--->SQL---saceobject:"+ customerEntity.getName());
            database.execSQL("insert into customertable (customerdata,name) values(?,?)", new Object[] { data , customerEntity.getName()});
            database.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

        }
    }
    /**
     *@author chenzhu
     *creat at 2017/12/25 14:19
     *有多少条记录
     *@return  CustomerEntity list
     **/
    public int countData( String  name) {
        int count = 0;
        SQLiteHelper dbhelper = SQLiteHelper.getInstens(context);
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from  customertable where name = ?", new  String[]{name});
        count = cursor.getCount();
        LogUtils.d("chenzhu--->SQL---countData:", cursor.getCount()+"");
        return count;
    }


    /**
     *@author chenzhu
     *creat at 2017/12/25 14:19
     *删除数据库中的对象，需要删除那些
     *@return  CustomerEntity list
     **/
    public int  DeletData(ArrayList<String> s) {
       ArrayList <String > arrayList =  new ArrayList<>();
        for (int a = 0 ;a < s.size() ;a++){
            arrayList.add("\""+s.get(a)+"\"");
        }
            LogUtils.d("chenzhu--->will  DeletData:" + s.toString().replace("[","").replace("]",""));
            SQLiteHelper dbhelper = SQLiteHelper.getInstens(context);
            SQLiteDatabase database = dbhelper.getReadableDatabase();
//           int a = database.delete("playrecordtable", "playrecordcode = ?", dele);//删除一条
            LogUtils.d("chenzhu--->SQL---DeletData--->SQL:" + "delete from customertable where name in ("+arrayList.toString().replace("[","").replace("]","")+")");
            database.execSQL("delete from customertable where name in ("+arrayList.toString().replace("[","").replace("]","")+")");
//            LogUtils.d("chenzhu--->SQL---DeletData:" + 0);
            return s.size();

    }

 /**
  *@author chenzhu
  *creat at 2017/12/25 14:17
  *@param name playrecorCode
  *@param customerEntity  存入moveContentEntity对象
  *@return sql是否有当前对象
  * "playrecordtable", "playrecordcode
  **/
 public void  upData(String name, CustomerEntity customerEntity){
       ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
       byte data[]= null;
       try {
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
           objectOutputStream.writeObject(customerEntity);
           objectOutputStream.flush();
            data= arrayOutputStream.toByteArray();
           objectOutputStream.close();
           arrayOutputStream.close();
                 } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       LogUtils.d("chenzhu--->will  upData:"+name);
       SQLiteHelper dbhelper = SQLiteHelper.getInstens(context);
       SQLiteDatabase database = dbhelper.getReadableDatabase();
       ContentValues values = new ContentValues();
       values.put("customerdata",data);
       values.put("name",name);
       int a = database.update("customertable", values, "name = ?", new String[] {name});
       LogUtils.d("chenzhu--->SQL---upData:"+a);
}

    /**
     *@author chenzhu
     *creat at 2017/12/25 16:30
     * 获取所有的列表
     *@return
     **/
    public ArrayList<CustomerEntity> getAllObject() {
        ArrayList<CustomerEntity> listmoveContentEntity = new ArrayList<CustomerEntity>();
        SQLiteHelper dbhelper = SQLiteHelper.getInstens(context);
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from  customertable", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                byte data[] = cursor.getBlob(cursor.getColumnIndex("customerdata"));
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                    CustomerEntity customerEntity = (CustomerEntity) inputStream.readObject();
                    listmoveContentEntity.add(customerEntity);
                    inputStream.close();
                    arrayInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        LogUtils.d("chenzhu--->SQL---getAllObject"+Integer.toString(listmoveContentEntity.size()));
        return listmoveContentEntity;
    }
}
