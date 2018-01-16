package com.xiaotianwei.fragment;

import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.activity.Tianjiazhanghuzijin;
import com.xiaotianwei.activity.Zhuangzhang;
import com.xiaotianwei.adapter.ZijinListviewAdapter;
import com.xiaotianwei.bean.Account;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Zijinfragment extends Fragment {
    private Button zhaungzhang;
    private TextView jieyu;
    private ImageView tianjiazijin;
    private ListView zijinLv;
    private float sum=0;
    private RecordDao recorddao;
    private SharedPreferences sharedpreferences;
    private List<Account> list;
    private AccountDao accountDao;
    private BaseAdapter myadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_zijin,container,false);
        initView(view);
        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initListener() {
        zhaungzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
                String islogin=sharedpreferences.getString("username",null);
                if(islogin==null) Toast.makeText(getActivity(), "请先登陆", Toast.LENGTH_SHORT).show();
                else{
                Intent i=new Intent();
                i.setClass(getActivity(), Zhuangzhang.class);
                startActivity(i);}
            }
        });

        tianjiazijin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
                String islogin=sharedpreferences.getString("username",null);
                if(islogin==null) Toast.makeText(getActivity(), "请先登陆", Toast.LENGTH_SHORT).show();
                else{
                Intent i=new Intent();
                i.setClass(getActivity(), Tianjiazhanghuzijin.class);
                startActivity(i);}
            }
        });

        zijinLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Account account=new Account();
                account=list.get(position);
                 final int getid=account.getId();
                 final String accountname=account.getAccountname();
                 final float price=account.getPrice();
                final int pic =account.getPic();

                final View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_zijinlistitem,null);
                final EditText jine_Et= (EditText) view.findViewById(R.id.item_zijinjine);
                jine_Et.setHint(price+"");
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //点击外部不可取消
                builder.setTitle(accountname);
                builder.setIcon(pic);
                builder.setView(view);

                builder.setPositiveButton("存入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int newprice=Integer.parseInt(jine_Et.getText().toString().trim());
                        float chajia=newprice-price;
                        sum=sum+chajia;
                            jieyu.setText(sum+"");
                        accountDao=new AccountDao(getActivity());
                        Account newaccount=new Account(accountname,newprice,pic);
                        accountDao.mUpdatebyid(getid,newaccount);
                        myadapter.notifyDataSetChanged();
                        onDestroy();
                        onResume();
                        Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
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

    }

    private void initAdapter() {

    }

    private void initData() {
        //sum=getshouru()+getzhichu();
       sharedpreferences=getActivity().getSharedPreferences("Login_state", Context.MODE_APPEND);
        String islogin=sharedpreferences.getString("username",null);
       /* if(islogin!=null)
        jieyu.setText(sum+"");
        else
            jieyu.setText("0.00");*/
        accountDao=new AccountDao(getActivity());
         sum=accountDao.queryallprice();
        if(islogin!=null)
            jieyu.setText(sum+"");
        else
            jieyu.setText("0.00");

    }

    private void initView(View view) {
        zhaungzhang= (Button) view.findViewById(R.id.zijin_btn_zhuangzhang);
        jieyu= (TextView) view.findViewById(R.id.zijin_Tv_price);
        tianjiazijin= (ImageView) view.findViewById(R.id.zijin_Iv_tianjia);
        zijinLv= (ListView) view.findViewById(R.id.zijin_Lv);
    }



    @Override
    public void onResume() {
        sharedpreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
        String islogin=sharedpreferences.getString("username",null);
        SharedPreferences.Editor edit=sharedpreferences.edit();
        /*edit.putFloat("shouru",getshouru());
        edit.putFloat("zhichu",getzhichu());*/
        edit.commit();
        if(islogin!=null){
        accountDao=new AccountDao(getActivity());
            initAdapter();
            list=accountDao.queryall();
            myadapter=new ZijinListviewAdapter(list,getActivity());
            myadapter.notifyDataSetChanged();
        zijinLv.setAdapter(myadapter);
        }
        super.onResume();
    }

}