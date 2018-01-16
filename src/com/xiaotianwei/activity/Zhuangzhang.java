package com.xiaotianwei.activity;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.db.AccountDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Zhuangzhang extends Activity {
    private ImageView zhuangzhang_Iv_back,zhuangrupic,zhuangchupic;
    private TextView zhuangru_Tv_text,zhuangchu_Tv_text;
    private EditText zhuangzhangjine;
    private Button zhuangzhang_save;
    private RelativeLayout zhuangru,zhuangchu;
    private SharedPreferences sharedPreferences;
    private String zhuangruzhanghuming,zhuangchuzhanghuming;
    private AccountDao accountDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null){
            getActionBar().hide();
        }
        setContentView(R.layout.fragment_zhuanzhang);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        zhuangchuzhanghuming="现金";
        zhuangruzhanghuming="储蓄卡";

        sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("accountname","现金");
        editor.commit();
        sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
        SharedPreferences.Editor editor2=sharedPreferences.edit();
        editor2.putString("accountname","储蓄卡");
        editor2.commit();
    }

    private void initListener() {
        zhuangzhang_Iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        zhuangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=getLayoutInflater().inflate(R.layout.dialog_tianjia_type,null);
                LinearLayout xianjin= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xianjin);
                LinearLayout chuxuka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_chuxuka);
                LinearLayout xinyongka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xinyongka);
                LinearLayout zhifubao= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_zhifubao);
                sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
                final String getupname=sharedPreferences.getString("accountname",null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(Zhuangzhang.this);
                builder.setCancelable(true);   //点击外部不可取消
                builder.setTitle("选择账户类型");
                builder.setIcon(R.drawable.ic_launcher);
                xianjin.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("现金".trim())){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("accountname","现金");
                        editor.commit();
                            zhuangchuzhanghuming="现金";
                        zhuangchupic.setImageResource(R.drawable.ft_cash1);
                        zhuangchu_Tv_text.setText("现金");}

                    }
                });

                chuxuka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择储蓄卡
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("储蓄卡".trim())){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("accountname","储蓄卡");
                        editor.commit();
                            zhuangchuzhanghuming="储蓄卡";
                        zhuangchupic.setImageResource(R.drawable.ft_chuxuka1);
                        zhuangchu_Tv_text.setText("储蓄卡");}
                    }
                });

                xinyongka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择信用卡
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("信用卡".trim())){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("accountname","信用卡");
                        editor.commit();
                            zhuangchuzhanghuming="信用卡";
                        zhuangchupic.setImageResource(R.drawable.ft_creditcard1);
                        zhuangchu_Tv_text.setText("信用卡");}
                    }
                });

                zhifubao.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("支付宝".trim())){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("accountname","支付宝");
                            zhuangchuzhanghuming="支付宝";
                        editor.commit();
                        zhuangchupic.setImageResource(R.drawable.ft_wangluochongzhi1);
                        zhuangchu_Tv_text.setText("支付宝");}
                    }
                });

                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();


            }
        });

        zhuangru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=getLayoutInflater().inflate(R.layout.dialog_tianjia_type,null);
                LinearLayout xianjin= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xianjin);
                LinearLayout chuxuka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_chuxuka);
                LinearLayout xinyongka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xinyongka);
                LinearLayout zhifubao= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_zhifubao);
                sharedPreferences=getSharedPreferences("record1",MODE_APPEND);
                final String getupname=sharedPreferences.getString("accountname",null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(Zhuangzhang.this);
                builder.setCancelable(true);   //点击外部不可取消
                builder.setTitle("选择账户类型");
                builder.setIcon(R.drawable.ic_launcher);
                xianjin.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("现金".trim())){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("accountname","现金");
                            editor.commit();
                            zhuangruzhanghuming="现金";
                            zhuangrupic.setImageResource(R.drawable.ft_cash1);
                            zhuangru_Tv_text.setText("现金");}

                    }
                });

                chuxuka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择储蓄卡
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("储蓄卡")){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("accountname","储蓄卡");
                            editor.commit();
                            zhuangruzhanghuming="储蓄卡";
                            zhuangrupic.setImageResource(R.drawable.ft_chuxuka1);
                            zhuangru_Tv_text.setText("储蓄卡");}
                    }
                });

                xinyongka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择信用卡
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("信用卡")){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("accountname","信用卡");
                            editor.commit();
                            zhuangruzhanghuming="信用卡";
                            zhuangrupic.setImageResource(R.drawable.ft_creditcard1);
                            zhuangru_Tv_text.setText("信用卡");}
                    }
                });

                zhifubao.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        if(getupname.equals("支付宝")){
                            Toast.makeText(Zhuangzhang.this, "不能选择相同的账户("+getupname+")", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            sharedPreferences=getSharedPreferences("record2",MODE_APPEND);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("accountname","支付宝");
                            editor.commit();
                            zhuangruzhanghuming="支付宝";
                            zhuangrupic.setImageResource(R.drawable.ft_wangluochongzhi1);
                            zhuangru_Tv_text.setText("支付宝");}
                    }
                });

                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });

        zhuangzhang_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(zhuangzhangjine.getText().toString().trim().equals(""))
                    {

                        Toast.makeText(Zhuangzhang.this, "请输入转账金额", Toast.LENGTH_SHORT).show();

                    }
                else
                    {
                        float price=Float.parseFloat(zhuangzhangjine.getText().toString());
                        accountDao=new AccountDao(Zhuangzhang.this);
                        accountDao.updatetwo(zhuangchuzhanghuming,zhuangruzhanghuming,price);
                        Toast.makeText(Zhuangzhang.this, "转账成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
            }
        });


    }

    private void initView() {
        zhuangzhang_Iv_back= (ImageView) findViewById(R.id.zhuangzhang_Iv_back);
        zhuangchupic= (ImageView) findViewById(R.id.zhangzhang_typepic1);
        zhuangrupic= (ImageView) findViewById(R.id.zhangzhang_typepic2);
        zhuangchu_Tv_text= (TextView) findViewById(R.id.zhuangzhang_typetext1);
        zhuangru_Tv_text= (TextView) findViewById(R.id.zhuangzhang_typetext2);
        zhuangzhangjine= (EditText) findViewById(R.id.zhuangzhang_Et_zhuangzhangjine);
        zhuangzhang_save= (Button) findViewById(R.id.zhuangzhang_save);
        zhuangru= (RelativeLayout) findViewById(R.id.zhuangzhang_zhuangru);
        zhuangchu= (RelativeLayout) findViewById(R.id.zhuangzhang_zhuangchu);
    }
}

