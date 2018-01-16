package com.xiaotianwei.db;

import com.briup.xiaotianwei.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper{

	public SqliteHelper(Context context) {
		super(context, "user.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql1="create table tbl_user(_id integer primary key autoincrement,username unique,password)";
        String sql2="create table tbl_account(_id integer primary key autoincrement,accountname,price,pic)";
        //name:账户类型（支付宝，储蓄卡）,price(多少钱)
        String sql3="create table tbl_record(_id integer primary key autoincrement,type,content,date,money,accountname,month)";
        //type(收入/支出)，content（做了什么事情），money（多少钱）
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL("insert into tbl_account(accountname,price,pic) values(?,?,?)",new String[]{"现金","0",R.drawable.ft_cash1+""});
        db.execSQL("insert into tbl_account(accountname,price,pic) values(?,?,?)",new String[]{"储蓄卡","0",R.drawable.ft_chuxuka1+""});
        db.execSQL("insert into tbl_account(accountname,price,pic) values(?,?,?)",new String[]{"信用卡","0",R.drawable.ft_creditcard1+""});
        db.execSQL("insert into tbl_account(accountname,price,pic) values(?,?,?)",new String[]{"支付宝","0",R.drawable.ft_wangluochongzhi1+""});
        Log.i("SqliteHelper","建表成功");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
