package com.xiaotianwei.adapter;

import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.bean.Account;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZijinListviewAdapter extends BaseAdapter {
    private List<Account> list;
    private Context context;

    public ZijinListviewAdapter(List<Account> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= View.inflate(context, R.layout.zijinlistview,null);
        TextView accountnameTv= (TextView) view.findViewById(R.id.zijinlist_type);
        TextView priceTv= (TextView) view.findViewById(R.id.zijinlist_price);
        TextView idTv= (TextView) view.findViewById(R.id.zijinlisetid);
        ImageView listpicIv= (ImageView) view.findViewById(R.id.zijinlist_pic);
        Account account=new Account();
        account=list.get(position);
        int id=account.getId();
        String accountname=account.getAccountname();
        float price=account.getPrice();
        int listpic=account.getPic();
        accountnameTv.setText(accountname);
        priceTv.setText(price+"");
        idTv.setText(id+"");

        listpicIv.setImageResource(listpic);
        return view;
    }
}
