package com.xiaotianwei.db;

import com.xiaotianwei.bean.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private SqliteHelper helper;
    private SQLiteDatabase database;
    public UserDao(Context context) {
        helper=new SqliteHelper(context);
    }
    public int addUser(User user)
    {
        int add=0;
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("password",user.getPassword());
        boolean flag=uniqueUser(user);
        if(flag==true)  //如果用户名可以注册
        {database=helper.getWritableDatabase();
        database.insert("tbl_user",null,contentValues);
        add=1;          //如果用户名可以注册
        }
        else
        {
            add=0;
        }
       return add;
    }

    public int resetUser(String oldname,User newuser)//重置密码，返回0表示用户不存在，返回1表示用户存在
    {
        ContentValues contentValues=new ContentValues();
        int id=queryUserbybname(oldname);
        if(id==-1){ return 0; }
        else{
            contentValues.put("username",newuser.getUsername());
            contentValues.put("password",newuser.getPassword());
            database=helper.getWritableDatabase();
            database.update("tbl_user",contentValues,"_id=?",new String[]{id+""});
            return 1;
        }
    }
    public int queryUserbybname(String username)    //返回id，id为-1表示用户不存在
    {
        int getid=-1;
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_user",new String[]{"_id"},"username=?",new String[]{username},null,null,null);
        while(cursor.moveToNext()) {
            getid = cursor.getInt(cursor.getColumnIndex("_id"));
        }
        return getid;
    }
    public boolean veryfyUser(User user)
    {
        boolean flag=false;  //为真代表验证成功，为假代表验证失败
        int id=queryUserbybname(user.getUsername());
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_user",new String[]{"username","password"},"_id=?",new String[]{id+""},null,null,null);
        if(cursor==null) flag=false;
        else {
            while (cursor.moveToNext()) {
                String getusername = cursor.getString(cursor.getColumnIndex("username"));
                String getuserpassword = cursor.getString(cursor.getColumnIndex("password"));
                if (getusername.equals(user.getUsername()) && getuserpassword.equals(user.getPassword()))
                    flag = true;
                else
                    flag = false;
            }
        }
        return flag;
    }

    public boolean uniqueUser(User user)          //判断用户是否唯一
    {
        boolean flag=true;     //为真代表用于不存在，为假代表用户存在
        database=helper.getReadableDatabase();
        Cursor cursor=database.query("tbl_user",new String[]{"username"},null,null,null,null,null);
        String receivename=user.getUsername();
        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex("username")).equals(receivename))
            {
                flag=false;
            }
        }
        return  flag;
    }
}

