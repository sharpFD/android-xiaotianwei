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
        if(name.equals("canying")) newname="������";
        if(name.equals("yanjiu")) newname="�̾�";
        if(name.equals("jiaotong")) newname="��ͨ";
        if(name.equals("gouwu")) newname="����";
        if(name.equals("yule")) newname="����";
        if(name.equals("kuisun")) newname="Ͷ�ʿ���";
        if(name.equals("shenghuofuwu")) newname="�������";
        if(name.equals("yiyao")) newname="ҽҩ";
        if(name.equals("zhufang")) newname="ס��";
        if(name.equals("shuidianmei")) newname="ˮ��ú";
        if(name.equals("shicai")) newname="ʳ��";
        if(name.equals("chongzhi")) newname="��ֵ";

        if(name.equals("gongzi")) newname="����";
        if(name.equals("jiangjin")) newname="����";
        if(name.equals("fuli")) newname="����";
        if(name.equals("shouyi")) newname="����";
        if(name.equals("hongbao")) newname="���";
        if(name.equals("jianzhi")) newname="��ְ";
        if(name.equals("shenghuofei")) newname="�����";
        if(name.equals("baoxiao")) newname="����";
        if(name.equals("tuikuan")) newname="�˿�";
        if(name.equals("gongjijin")) newname="������";
        if(name.equals("shebaojin")) newname="�籣��";
        if(name.equals("yiwai")) newname="�����ջ�";

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
            textview1.setText(record.getDate()+"��");
            textview2.setText(findnamebypinyin(record.getContent())+"-"+record.getMoney()+"Ԫ");
            getidTv.setText(record.getId()+"");
        }
        else
        {
            listpic.setImageResource(findpicbypinyin(record.getContent()));
            textview2.setText(record.getDate()+"��");
            textview1.setText(findnamebypinyin(record.getContent())+"+"+record.getMoney()+"Ԫ");
            getidTv.setText(record.getId()+"");
        }
        return view;
    }
}
