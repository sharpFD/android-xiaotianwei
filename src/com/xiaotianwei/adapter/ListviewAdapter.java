package com.xiaotianwei.adapter;

import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.bean.Record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListviewAdapter extends BaseAdapter {
    private List<Record> list;
    private Context context;

    public ListviewAdapter(List<Record> list, Context context) {
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
    public int findpicbypinyin(String name)
    {

        int newname=0;
        if(name.equals("canying")) newname=R.drawable.bt_food;
        if(name.equals("yanjiu")) newname=R.drawable.bt_wine;
        if(name.equals("jiaotong")) newname=R.drawable.bt_car;
        if(name.equals("gouwu")) newname=R.drawable.bt_shopping;
        if(name.equals("yule")) newname=R.drawable.bt_yule;
        if(name.equals("kuisun")) newname=R.drawable.bt_kuisun;
        if(name.equals("shenghuofuwu")) newname=R.drawable.bt_service;
        if(name.equals("yiyao")) newname=R.drawable.bt_madecine;
        if(name.equals("zhufang")) newname=R.drawable.bt_house;
        if(name.equals("shuidianmei")) newname=R.drawable.bt_water;
        if(name.equals("shicai")) newname=R.drawable.bt_shicai;
        if(name.equals("chongzhi")) newname=R.drawable.bt_chongzhi;

        if(name.equals("gongzi")) newname=R.drawable.bt_wages;
        if(name.equals("jiangjin")) newname=R.drawable.bt_bouns;
        if(name.equals("fuli")) newname=R.drawable.bt_fuli;
        if(name.equals("shouyi")) newname=R.drawable.bt_invest;
        if(name.equals("hongbao")) newname=R.drawable.bt_hongbao;
        if(name.equals("jianzhi")) newname=R.drawable.bt_jianzhi;
        if(name.equals("shenghuofei")) newname=R.drawable.bt_shenghuofei;
        if(name.equals("baoxiao")) newname=R.drawable.bt_baoxiao;
        if(name.equals("tuikuan")) newname=R.drawable.bt_tuikuan;
        if(name.equals("gongjijin")) newname=R.drawable.bt_gongjijin;
        if(name.equals("shebaojin")) newname=R.drawable.bt_shebao;
        if(name.equals("yiwai")) newname=R.drawable.bt_yiwai;

        return newname;
    }
    public static String  findnamebypinyin(String name)
    {
        String newname=null;
        if(name.equals("canying")) newname="餐饮费";
        if(name.equals("yanjiu")) newname="烟酒";
        if(name.equals("jiaotong")) newname="交通";
        if(name.equals("gouwu")) newname="购物";
        if(name.equals("yule")) newname="娱乐";
        if(name.equals("kuisun")) newname="投资亏损";
        if(name.equals("shenghuofuwu")) newname="生活服务";
        if(name.equals("yiyao")) newname="医药";
        if(name.equals("zhufang")) newname="住房";
        if(name.equals("shuidianmei")) newname="水电煤";
        if(name.equals("shicai")) newname="食材";
        if(name.equals("chongzhi")) newname="充值";

        if(name.equals("gongzi")) newname="工资";
        if(name.equals("jiangjin")) newname="奖金";
        if(name.equals("fuli")) newname="福利";
        if(name.equals("shouyi")) newname="收益";
        if(name.equals("hongbao")) newname="红包";
        if(name.equals("jianzhi")) newname="兼职";
        if(name.equals("shenghuofei")) newname="生活费";
        if(name.equals("baoxiao")) newname="报销";
        if(name.equals("tuikuan")) newname="退款";
        if(name.equals("gongjijin")) newname="公积金";
        if(name.equals("shebaojin")) newname="社保金";
        if(name.equals("yiwai")) newname="意外收获";

        return newname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= View.inflate(context, R.layout.listview_showliist,null);
        TextView textview1= (TextView) view.findViewById(R.id.list_Tv_1);
        TextView textview2= (TextView) view.findViewById(R.id.list_Tv_2);
        TextView getidTv= (TextView) view.findViewById(R.id.listview_id);
        ImageView listpic= (ImageView) view.findViewById(R.id.list_Iv_pic);
        Record record=new Record();
        record=list.get(position);
        if(record.getType().equals("zhichu")){
            listpic.setImageResource(findpicbypinyin(record.getContent()));
            textview1.setText(record.getDate()+"日");
            textview2.setText(findnamebypinyin(record.getContent())+"-"+record.getMoney()+"元");
            getidTv.setText(record.getId()+"");
        }
        else
        {
            listpic.setImageResource(findpicbypinyin(record.getContent()));
            textview2.setText(record.getDate()+"日");
            textview1.setText(findnamebypinyin(record.getContent())+"+"+record.getMoney()+"元");
            getidTv.setText(record.getId()+"");
        }
        return view;
    }
}
