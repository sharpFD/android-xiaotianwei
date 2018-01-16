package com.xiaotianwei.activity;

import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.bean.Record;
import com.xiaotianwei.db.AccountDao;
import com.xiaotianwei.db.RecordDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Item_detail extends Activity {
    private TextView mTv_Text1,mTv_Text2,mTv_Text3,mTv_Text4,title,type;
    private ImageView typepic,pic1,pic2,pic3,back;
    private Button save;
    private int position;
    private List<Record>list;
    private RecordDao dao;
    private AccountDao accountDao;
    private SharedPreferences sharedPreferences;
    private String oldaccountname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null){
            getActionBar().hide();
        }
        setContentView(R.layout.activity_item_detail);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });             //返回按钮监听事件

        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //设置item

                View view=getLayoutInflater().inflate(R.layout.dialog_tianjia_type,null);       //自定义dialog，显示账户类型
                LinearLayout xianjin= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xianjin);  //找到自定义dialog里面的不同控件
                LinearLayout chuxuka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_chuxuka);
                LinearLayout xinyongka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xinyongka);
                LinearLayout zhifubao= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_zhifubao);
                final AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);
                builder.setCancelable(true);   //点击外部不可取消
                builder.setTitle("选择账户类型");
                builder.setIcon(R.drawable.ic_launcher);                //设置图标
                sharedPreferences=getSharedPreferences("detail",MODE_APPEND);   //得到选择的账户，并且按完确定后传递到activity里
                final SharedPreferences.Editor editor=sharedPreferences.edit();
                xianjin.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","现金");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_cash1);
                        mTv_Text1.setText("现金");


                    }
                });

                chuxuka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择储蓄卡
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","储蓄卡");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_chuxuka1);
                        mTv_Text1.setText("储蓄卡");
                    }
                });

                xinyongka.setOnClickListener(new View.OnClickListener() {                 //账户类型选择信用卡
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","信用卡");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_creditcard1);
                        mTv_Text1.setText("信用卡");
                    }
                });

                zhifubao.setOnClickListener(new View.OnClickListener() {                 //账户类型选择现金
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","支付宝");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_wangluochongzhi1);
                        mTv_Text1.setText("支付宝");
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

            pic2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);       //设置修改费用dialog
                    final EditText editText = new EditText(Item_detail.this);
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setHint("请输入修改后的费用");
                    builder.setTitle("费用");
                    builder.setIcon(R.drawable.ic_launcher);
                    builder.setView(editText);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(editText.getText().toString().equals("")){
                                Toast.makeText(Item_detail.this, "请输入修改后的费用", Toast.LENGTH_SHORT).show();
                            }
                            else
                            mTv_Text2.setText(editText.getText().toString());
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                }
            });

        pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view=getLayoutInflater().inflate(R.layout.dialog_jizhang_datapicker,null); //设置时间选择器dialog
                final DatePicker datepicker= (DatePicker) view.findViewById(R.id.item_datapicker);
                AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);
                builder.setCancelable(true);   //点击外部不可取消
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       int day=datepicker.getDayOfMonth();
                        mTv_Text3.setText(day+"");
                        mTv_Text4.setText(day+"");

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //点击保存按钮点击事件

                Record oldrecord=new Record();
                oldrecord=list.get(position);
                int id=oldrecord.getId();
                String type=oldrecord.getType();
                String content=oldrecord.getContent();
                float oldmoney=Float.parseFloat(oldrecord.getMoney());
                String month=oldrecord.getMonth();
                String money=mTv_Text2.getText().toString();
                String date=mTv_Text3.getText().toString();
                String accountname=mTv_Text1.getText().toString();
                //account表！！！！！！！！！！！！！！！！！！！！！！！
                Record newrecord=new Record(type,content,date,money,accountname,month);
                dao.Mupdate(id,newrecord);
                accountDao=new AccountDao(Item_detail.this);
                if(oldaccountname.equals(accountname)){  //如果不修改账户类型，只修改金额
                    switch (accountname)
                    {
                        case "现金": {                      //如果是现金
                            if (type.equals("shouru")){                 //如果是收入类型，注意：支出类型与之相反
                                if(oldmoney>Float.parseFloat(money))       //如果修改前的money大于修改后的money，就把原先账户类型里的前减去（修改前money-修改后money（相当于差价））
                                {
                                    accountDao.mUpdatebyzhichu("现金",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("现金",oldmoney-Float.parseFloat(money));
                                }
                                }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))        //如果修改前的money小于修改后的money，就加差价，与上类似
                                {
                                    accountDao.mUpdatebyzhichu("现金",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("现金",oldmoney-Float.parseFloat(money));
                                }
                               }
                            break;
                        }

                        case "储蓄卡": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("储蓄卡",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("储蓄卡",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("储蓄卡",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("储蓄卡",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;

                        }

                        case "信用卡": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("信用卡",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("信用卡",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("信用卡",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("信用卡",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;
                        }

                        case "支付宝": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("支付宝",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("支付宝",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("支付宝",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("支付宝",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;
                        }

                    }

                    }


                else {
                    switch (accountname) {      //如果修改了账户类型
                        case "现金": {
                            if (type.equals("shouru")){         //如果修改的是收入类型
                                accountDao.mUpdatebyshouru("现金",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney);
                                //修改前账户类型里的money要减去原先item里的全部money，修改后账户类型里的money要加上edittext里面传进来的money！！！！！！！
                            }
                            else
                            {
                                accountDao.mUpdatebyzhichu("现金",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);
                                //如果是支出类型，那么操作就与收入一致。
                            }
                                break;
                        }

                        case "储蓄卡": {
                            if (type.equals("shouru")){
                                accountDao.mUpdatebyshouru("储蓄卡",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {
                                accountDao.mUpdatebyzhichu("储蓄卡",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;

                        }

                        case "信用卡": {
                            if (type.equals("shouru")){ accountDao.mUpdatebyshouru("信用卡",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {accountDao.mUpdatebyzhichu("信用卡",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;
                        }

                        case "支付宝": {
                            if (type.equals("shouru")){ accountDao.mUpdatebyshouru("支付宝",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {accountDao.mUpdatebyzhichu("支付宝",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;
                        }

                    }
                }

                Toast.makeText(Item_detail.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initData() {
        Intent i=getIntent();
        dao=new RecordDao(this);
        position=i.getIntExtra("position",0);
        list=dao.queryall();
        Record record=new Record();
        record=list.get(position);          //得到当前点击item上的信息，并赋值给record对象
        int id=record.getId();
        String type=record.getType();
        String money=record.getMoney();
        String date=record.getDate();           ///得到储蓄卡。。。。。。。。。。。。。。。。。。
        String content=record.getContent();
        String accountname=record.getAccountname();
        oldaccountname=accountname;
        if(this.type.equals("shouru"))this.type.setText("因事收入");  //如果是收入类型
        else this.type.setText("因事支出");                             //如果是支出类型
        mTv_Text2.setText(money);
        mTv_Text3.setText(date);
        mTv_Text4.setText(date);
        switch (accountname)
        {
            case "现金":
            {
                typepic.setImageResource(R.drawable.ft_cash1);
                mTv_Text1.setText("现金");
                break;
            }

            case "储蓄卡":
            {
                typepic.setImageResource(R.drawable.ft_chuxuka1);
                mTv_Text1.setText("储蓄卡");
                break;
            }

            case "信用卡":
            {
                typepic.setImageResource(R.drawable.ft_creditcard1);
                mTv_Text1.setText("信用卡");
                break;
            }
            case "支付宝":
            {
                typepic.setImageResource(R.drawable.ft_wangluochongzhi1);
                mTv_Text1.setText("支付宝");
                break;
            }

        }

        String name=findnamebypinyin(content);
        title.setText(name);
    }

    private String findnamebypinyin(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initView() {
        mTv_Text1= (TextView) findViewById(R.id.Item_Iv_typetext1);
        mTv_Text2= (TextView) findViewById(R.id.Item_Iv_typetext2);
        mTv_Text3= (TextView) findViewById(R.id.Item_Iv_typetext3);
        mTv_Text4= (TextView) findViewById(R.id.Itemdetail_rili);
        typepic= (ImageView) findViewById(R.id.Item_Iv_typepic1);
        pic1= (ImageView) findViewById(R.id.Item_Iv_1);
        pic2= (ImageView) findViewById(R.id.Item_Iv_2);
        pic3= (ImageView) findViewById(R.id.Item_Iv_3);
        save= (Button) findViewById(R.id.Item_Btn_save);
        title= (TextView) findViewById(R.id.Itemdetail_title);
        back= (ImageView) findViewById(R.id.Itemdeyail_Iv_back);
        type= (TextView) findViewById(R.id.Item_Tv_type);

    }
}
