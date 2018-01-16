package com.xiaotianwei.db;

import java.util.ArrayList;
import java.util.List;

import com.xiaotianwei.bean.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountDao {
    private SqliteHelper helper;
    private SQLiteDatabase database;
    public AccountDao(Context context) {
        helper=new SqliteHelper(context);
    }

    public void  mInsert(Account account)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("accountname",account.getAccountname());
        contentValues.put("price",account.getPrice());
        contentValues.put("pic",account.getPic());
        database=helper.getWritableDatabase();
        database.insert("tbl_account",null,contentValues);
    }
    public List<Account> queryall()
    {
        List<Account> list=new ArrayList<>();
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_account",null,null,null,null,null,null);
       if(cursor!=null){
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String accountname=cursor.getString(cursor.getColumnIndex("accountname"));
            int price=cursor.getInt(cursor.getColumnIndex("price"));
            int pic=cursor.getInt(cursor.getColumnIndex("pic"));
            Account account=new Account(id,accountname,price,pic);
            Log.i("pic",pic+"");
            list.add(account);
        }}
        return list;
    }

    public void mUpdatebyid(int id,Account newaccount)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("accountname",newaccount.getAccountname());
        contentValues.put("price",newaccount.getPrice());
        contentValues.put("pic",newaccount.getPic());
        database=helper.getWritableDatabase();
        database.update("tbl_account",contentValues,"_id=?",new String[]{id+""});
        Log.i("RecordDao","更新成功");
    }
    public float queryprice(String name)            //查询某一个账户类型余额
    {
        float sum=0;
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_account",new String[]{"price"},"accountname=?",new String[]{name},null,null,null);
        while(cursor.moveToNext())
        {
            float temp=cursor.getInt(cursor.getColumnIndex("price"));
            sum=sum+temp;
        }
        return sum;
    }
    public void mUpdatebyshouru(String name,float money)
    {
        float old=queryprice(name);
        ContentValues contentValues=new ContentValues();
        contentValues.put("price",old+money);
        database=helper.getWritableDatabase();
        database.update("tbl_account",contentValues,"accountname=?",new String[]{name});
        Log.i("RecordDao","更新成功");
    }

    public void mUpdatejine(String name,float money)
    {
        //float old=queryprice(name);
        ContentValues contentValues=new ContentValues();
        contentValues.put("price",money);
        database=helper.getWritableDatabase();
        database.update("tbl_account",contentValues,"accountname=?",new String[]{name});
        Log.i("RecordDao","更新成功");
    }

    public void mUpdatebyzhichu(String name,float money)
    {
        float old=queryprice(name);
        ContentValues contentValues=new ContentValues();
        contentValues.put("price",old-money);
        database=helper.getWritableDatabase();
        database.update("tbl_account",contentValues,"accountname=?",new String[]{name});
        Log.i("RecordDao","更新成功");
    }
    public int  queryallprice()
    {
        int sum=0;
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_account",new String[]{"price"},null,null,null,null,null);
        while (cursor.moveToNext())
        {
            sum=sum+cursor.getInt(cursor.getColumnIndex("price"));
        }
        return sum;
    }
    public void updatetwo(String oldname,String newname,float price)
    {
        float zhuangchuprice=queryprice(oldname);
        float zhuangruprice=queryprice(newname);
        ContentValues contentValues1=new ContentValues();
        contentValues1.put("price",zhuangchuprice-price);

        ContentValues contentValues2=new ContentValues();
        contentValues2.put("price",zhuangruprice+price);
        database=helper.getReadableDatabase();
        database.update("tbl_account",contentValues1,"accountname=?",new String[]{oldname});
        database.update("tbl_account",contentValues2,"accountname=?",new String[]{newname});

    }
}
