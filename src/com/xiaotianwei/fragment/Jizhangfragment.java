package com.xiaotianwei.fragment;

import java.util.ArrayList;
import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.activity.Item_detail;
import com.xiaotianwei.activity.Tianjianyusuan;
import com.xiaotianwei.adapter.ListviewAdapter;
import com.xiaotianwei.bean.Record;
import com.xiaotianwei.db.AccountDao;
import com.xiaotianwei.db.RecordDao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Jizhangfragment extends Fragment {
    private ImageView mIv_jizhang_addyusuan,mIv_jizhang_rili,touxiang;
    private ListView listView;
    private ImageView empty;
    private RecordDao dao;
    private TextView shourushuzi,zhichushuzi,rilitext,shouruwenzi,zhichuwenzi;
    List<Record>list;
    private SharedPreferences sharedPreferences;
    private BaseAdapter adapter;
    private int pos;
    private AccountDao accountDao;
    private int month,day;
    private RecordDao recorddao;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_jizhang,container,false);
        initView(view);
        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initAdapter() {
        adapter=new ListviewAdapter(list,getActivity());
        sharedPreferences=getActivity().getSharedPreferences("Login_state", Context.MODE_APPEND);
        final  String islogin=sharedPreferences.getString("username",null);
        if(islogin!=null)
        listView.setAdapter(adapter);
    }

    private void initData() {
        dao=new RecordDao(getActivity());
        list=dao.queryallbymonth("1");
    }

    private void initListener() {
        sharedPreferences=getActivity().getSharedPreferences("Login_state", Context.MODE_APPEND);
        final  String islogin=sharedPreferences.getString("username",null);

            mIv_jizhang_addyusuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(islogin==null){
                        Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    Intent i = new Intent();
                    i.setClass(getActivity(), Tianjianyusuan.class);
                    startActivity(i);}
                }
            });

        mIv_jizhang_rili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_jizhang_datapicker,null);
                final DatePicker datePicker= (DatePicker) view.findViewById(R.id.item_datapicker);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //点击外部不可取消
                builder.setView(view);
                datePicker.init(2017, 1, 9, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        day=dayOfMonth;
                        month=monthOfYear;
                        sharedPreferences=getActivity().getSharedPreferences("md",Context.MODE_APPEND);
                        SharedPreferences.Editor edit=sharedPreferences.edit();
                        edit.putString("month",monthOfYear+1+"");
                        edit.putString("date",dayOfMonth+"");
                        edit.commit();
                       onDestroy();
                        onResume();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {            ///asdasdasd

                        rilitext.setText(day+"");
                        shouruwenzi.setText(month+1+"月收入");
                        zhichuwenzi.setText(month+1+"月支出");
                    }
                });
                builder.create().show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //pos=position;
                Intent i=new Intent();
                i.setClass(getActivity(), Item_detail.class);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islogin==null){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent();
                    i.setClass(getActivity(), Tianjianyusuan.class);
                    startActivity(i);}

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //点击外部不可取消
                builder.setTitle("小天位");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setMessage("是否删除此条数据");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Record record =list.get(position);
                        list.remove(position);
                        int getid=record.getId();
                        float money=Float.parseFloat(record.getMoney());
                        String type=record.getType();
                        String accountname=record.getAccountname();
                        dao=new RecordDao(getActivity());
                        dao.mDelete(getid);
                        adapter.notifyDataSetChanged();
                        accountDao=new AccountDao(getActivity());
                        switch (accountname)
                        {
                            case "现金": {
                                if (type.equals("shouru")){
                                        accountDao.mUpdatebyzhichu("现金",money);
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("现金",money);
                                }
                                break;
                            }

                            case "储蓄卡": {
                                if (type.equals("shouru")){
                                    accountDao.mUpdatebyzhichu("储蓄卡",money);
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("储蓄卡",money);
                                }
                                break;
                                }

                            case "信用卡": {
                            if (type.equals("shouru")){
                                accountDao.mUpdatebyzhichu("现金",money);
                            }
                            else
                            {
                                accountDao.mUpdatebyshouru("现金",money);
                            }
                            break;
                            }

                            case "支付宝": {
                                if (type.equals("shouru")){
                                    accountDao.mUpdatebyzhichu("现金",money);
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("现金",money);
                                }
                                break;
                            }}
                        onResume();
                        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                       // listView.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

                return true;
            }
        });
    }

    private void initView(View view) {
        rilitext= (TextView) view.findViewById(R.id.rilitext);
        touxiang= (ImageView) view.findViewById(R.id.jizhang_Im_touxiang);
        mIv_jizhang_addyusuan= (ImageView) view.findViewById(R.id.jizhang_Iv_addyusuan);
        mIv_jizhang_rili= (ImageView) view.findViewById(R.id.jizhang_Iv_rili);
        listView= (ListView) view.findViewById(R.id.listview);
        empty= (ImageView) view.findViewById(R.id.jizhang_emptyview);
        empty.setImageResource(R.drawable.accountpager_listview_none);
        shourushuzi= (TextView) view.findViewById(R.id.jizhang_Tv_shourushuzi);
        zhichushuzi= (TextView) view.findViewById(R.id.jizhang_Tv_zhichushuzi);
        shouruwenzi= (TextView) view.findViewById(R.id.jizhang_Tv_shouruwenzi);
        zhichuwenzi= (TextView) view.findViewById(R.id.jizhang_Tv_zhichuwenzi);
    }
    public float getzhichu()
    {

        sharedPreferences=getActivity().getSharedPreferences("md",Context.MODE_APPEND);
        String month=sharedPreferences.getString("month",null);
        if (month==null)month="1";
        float zhichu=0;
        List<Record>list=new ArrayList<>();
        recorddao=new RecordDao(getActivity());
        Record record=new Record();
        list=recorddao.queryallbymonth(month);
        for(int i=0;i<list.size();i++)
        {
            record=list.get(i);
            String gettype=record.getType();
            float temp=Float.parseFloat(record.getMoney());
            if(gettype.equals("zhichu")) zhichu=zhichu+temp;
        }
        zhichu=-zhichu;
        return zhichu;
    }
    public float getshouru()
    {
        sharedPreferences=getActivity().getSharedPreferences("md",Context.MODE_APPEND);
        String month=sharedPreferences.getString("month",null);
        if (month==null)month="1";
        float shouru=0;
        List<Record>list=new ArrayList<>();
        recorddao=new RecordDao(getActivity());
        Record record=new Record();
        list=recorddao.queryallbymonth(month);
        for(int i=0;i<list.size();i++)
        {
            record=list.get(i);
            String gettype=record.getType();
            float temp=Float.parseFloat(record.getMoney());
            if(gettype.equals("shouru")) shouru=shouru+temp;
        }
        return shouru;
    }

    @Override
    public void onResume() {
        sharedPreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
        /*float getshouru=sharedPreferences.getFloat("shouru",0);
        float getzgichu=sharedPreferences.getFloat("zhichu",0);*/
        String islogin=sharedPreferences.getString("username",null);
        if(islogin!=null)
        { shourushuzi.setText(getshouru()+"");
        zhichushuzi.setText(-getzhichu()+"");}
        sharedPreferences=getActivity().getSharedPreferences("md",Context.MODE_APPEND);
        String getmonth=sharedPreferences.getString("month",null);
        if (getmonth==null)getmonth="1";
        list=dao.queryallbymonth(getmonth);
       // list=dao.queryall();
        initAdapter();
        listView.setEmptyView(empty);
        adapter.notifyDataSetChanged();
        super.onResume();
    }

}
