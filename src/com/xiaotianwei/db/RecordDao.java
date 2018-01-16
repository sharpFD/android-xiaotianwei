package com.xiaotianwei.db;

import java.util.ArrayList;
import java.util.List;

import com.xiaotianwei.bean.Record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RecordDao {
    private SqliteHelper helper;
    private SQLiteDatabase database;
    public RecordDao(Context context) {
        helper=new SqliteHelper(context);
    }
    public void Minsert(Record record)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("type",record.getType());
        contentValues.put("content",record.getContent());
        contentValues.put("date",record.getDate());
        contentValues.put("money",record.getMoney());
        contentValues.put("accountname",record.getAccountname());
        contentValues.put("month",record.getMonth());
        database=helper.getWritableDatabase();
        database.insert("tbl_record",null,contentValues);
        Log.i("RecordDao","插入成功");
    }
    public void Mupdate(int id,Record newrecord)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("type",newrecord.getType());
        contentValues.put("content",newrecord.getContent());
        contentValues.put("date",newrecord.getDate());
        contentValues.put("money",newrecord.getMoney());
        contentValues.put("month",newrecord.getMonth());
        contentValues.put("accountname",newrecord.getAccountname());
        database=helper.getWritableDatabase();
        database.update("tbl_record",contentValues,"_id=?",new String[]{id+""});
        Log.i("RecordDao","更新成功");
    }
    public List<Record> queryall()
    {
        List<Record>list=new ArrayList<>();
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_record",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            String month=cursor.getString(cursor.getColumnIndex("month"));
            String content=cursor.getString(cursor.getColumnIndex("content"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String money=cursor.getString(cursor.getColumnIndex("money"));
            String accountname=cursor.getString(cursor.getColumnIndex("accountname"));
            Record record=new Record(type,content,date,money,id,accountname,month);
            list.add(record);
        }
        return list;
    }
    public void mDelete(int id)
    {
        database=helper.getWritableDatabase();
        database.delete("tbl_record","_id=?",new String[]{id+""});
    }
    public List<Record> queryallbymonth(String newmonth)
    {
        List<Record>list=new ArrayList<>();
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_record",null,"month=?",new String[]{newmonth},null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String type=cursor.getString(cursor.getColumnIndex("type"));
            String month=cursor.getString(cursor.getColumnIndex("month"));
            String content=cursor.getString(cursor.getColumnIndex("content"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String money=cursor.getString(cursor.getColumnIndex("money"));
            String accountname=cursor.getString(cursor.getColumnIndex("accountname"));
            Record record=new Record(type,content,date,money,id,accountname,month);
            list.add(record);
        }
        return list;
    }

}

