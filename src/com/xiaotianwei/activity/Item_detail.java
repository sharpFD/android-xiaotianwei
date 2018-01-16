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
        });             //���ذ�ť�����¼�

        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //����item

                View view=getLayoutInflater().inflate(R.layout.dialog_tianjia_type,null);       //�Զ���dialog����ʾ�˻�����
                LinearLayout xianjin= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xianjin);  //�ҵ��Զ���dialog����Ĳ�ͬ�ؼ�
                LinearLayout chuxuka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_chuxuka);
                LinearLayout xinyongka= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_xinyongka);
                LinearLayout zhifubao= (LinearLayout) view.findViewById(R.id.dialog_tianjia_type_zhifubao);
                final AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setTitle("ѡ���˻�����");
                builder.setIcon(R.drawable.ic_launcher);                //����ͼ��
                sharedPreferences=getSharedPreferences("detail",MODE_APPEND);   //�õ�ѡ����˻������Ұ���ȷ���󴫵ݵ�activity��
                final SharedPreferences.Editor editor=sharedPreferences.edit();
                xianjin.setOnClickListener(new View.OnClickListener() {                 //�˻�����ѡ���ֽ�
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","�ֽ�");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_cash1);
                        mTv_Text1.setText("�ֽ�");


                    }
                });

                chuxuka.setOnClickListener(new View.OnClickListener() {                 //�˻�����ѡ���
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","���");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_chuxuka1);
                        mTv_Text1.setText("���");
                    }
                });

                xinyongka.setOnClickListener(new View.OnClickListener() {                 //�˻�����ѡ�����ÿ�
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","���ÿ�");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_creditcard1);
                        mTv_Text1.setText("���ÿ�");
                    }
                });

                zhifubao.setOnClickListener(new View.OnClickListener() {                 //�˻�����ѡ���ֽ�
                    @Override
                    public void onClick(View v) {
                        editor.putString("accountname","֧����");
                        editor.commit();
                        typepic.setImageResource(R.drawable.ft_wangluochongzhi1);
                        mTv_Text1.setText("֧����");
                    }
                });

                builder.setView(view);
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
                    final AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);       //�����޸ķ���dialog
                    final EditText editText = new EditText(Item_detail.this);
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setHint("�������޸ĺ�ķ���");
                    builder.setTitle("����");
                    builder.setIcon(R.drawable.ic_launcher);
                    builder.setView(editText);
                    builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(editText.getText().toString().equals("")){
                                Toast.makeText(Item_detail.this, "�������޸ĺ�ķ���", Toast.LENGTH_SHORT).show();
                            }
                            else
                            mTv_Text2.setText(editText.getText().toString());
                        }
                    });
                    builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
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
                final View view=getLayoutInflater().inflate(R.layout.dialog_jizhang_datapicker,null); //����ʱ��ѡ����dialog
                final DatePicker datepicker= (DatePicker) view.findViewById(R.id.item_datapicker);
                AlertDialog.Builder builder=new AlertDialog.Builder(Item_detail.this);
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setView(view);
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       int day=datepicker.getDayOfMonth();
                        mTv_Text3.setText(day+"");
                        mTv_Text4.setText(day+"");

                    }
                });
                builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //������水ť����¼�

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
                //account����������������������������������������������
                Record newrecord=new Record(type,content,date,money,accountname,month);
                dao.Mupdate(id,newrecord);
                accountDao=new AccountDao(Item_detail.this);
                if(oldaccountname.equals(accountname)){  //������޸��˻����ͣ�ֻ�޸Ľ��
                    switch (accountname)
                    {
                        case "�ֽ�": {                      //������ֽ�
                            if (type.equals("shouru")){                 //������������ͣ�ע�⣺֧��������֮�෴
                                if(oldmoney>Float.parseFloat(money))       //����޸�ǰ��money�����޸ĺ��money���Ͱ�ԭ���˻��������ǰ��ȥ���޸�ǰmoney-�޸ĺ�money���൱�ڲ�ۣ���
                                {
                                    accountDao.mUpdatebyzhichu("�ֽ�",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("�ֽ�",oldmoney-Float.parseFloat(money));
                                }
                                }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))        //����޸�ǰ��moneyС���޸ĺ��money���ͼӲ�ۣ���������
                                {
                                    accountDao.mUpdatebyzhichu("�ֽ�",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("�ֽ�",oldmoney-Float.parseFloat(money));
                                }
                               }
                            break;
                        }

                        case "���": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("���",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("���",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("���",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("���",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;

                        }

                        case "���ÿ�": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("���ÿ�",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("���ÿ�",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("���ÿ�",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("���ÿ�",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;
                        }

                        case "֧����": {
                            if (type.equals("shouru")){
                                if(oldmoney>Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("֧����",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("֧����",oldmoney-Float.parseFloat(money));
                                }
                            }
                            else
                            {
                                if(oldmoney<Float.parseFloat(money))
                                {
                                    accountDao.mUpdatebyzhichu("֧����",oldmoney-Float.parseFloat(money));
                                }
                                else
                                {
                                    accountDao.mUpdatebyshouru("֧����",oldmoney-Float.parseFloat(money));
                                }
                            }
                            break;
                        }

                    }

                    }


                else {
                    switch (accountname) {      //����޸����˻�����
                        case "�ֽ�": {
                            if (type.equals("shouru")){         //����޸ĵ�����������
                                accountDao.mUpdatebyshouru("�ֽ�",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney);
                                //�޸�ǰ�˻��������moneyҪ��ȥԭ��item���ȫ��money���޸ĺ��˻��������moneyҪ����edittext���洫������money��������������
                            }
                            else
                            {
                                accountDao.mUpdatebyzhichu("�ֽ�",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);
                                //�����֧�����ͣ���ô������������һ�¡�
                            }
                                break;
                        }

                        case "���": {
                            if (type.equals("shouru")){
                                accountDao.mUpdatebyshouru("���",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {
                                accountDao.mUpdatebyzhichu("���",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;

                        }

                        case "���ÿ�": {
                            if (type.equals("shouru")){ accountDao.mUpdatebyshouru("���ÿ�",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {accountDao.mUpdatebyzhichu("���ÿ�",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;
                        }

                        case "֧����": {
                            if (type.equals("shouru")){ accountDao.mUpdatebyshouru("֧����",Float.parseFloat(money.trim())); accountDao.mUpdatebyzhichu(oldaccountname,oldmoney); }
                            else
                            {accountDao.mUpdatebyzhichu("֧����",Float.parseFloat(money.trim())); accountDao.mUpdatebyshouru(oldaccountname,oldmoney);}
                            break;
                        }

                    }
                }

                Toast.makeText(Item_detail.this, "����ɹ�", Toast.LENGTH_SHORT).show();
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
        record=list.get(position);          //�õ���ǰ���item�ϵ���Ϣ������ֵ��record����
        int id=record.getId();
        String type=record.getType();
        String money=record.getMoney();
        String date=record.getDate();           ///�õ����������������������������������������
        String content=record.getContent();
        String accountname=record.getAccountname();
        oldaccountname=accountname;
        if(this.type.equals("shouru"))this.type.setText("��������");  //�������������
        else this.type.setText("����֧��");                             //�����֧������
        mTv_Text2.setText(money);
        mTv_Text3.setText(date);
        mTv_Text4.setText(date);
        switch (accountname)
        {
            case "�ֽ�":
            {
                typepic.setImageResource(R.drawable.ft_cash1);
                mTv_Text1.setText("�ֽ�");
                break;
            }

            case "���":
            {
                typepic.setImageResource(R.drawable.ft_chuxuka1);
                mTv_Text1.setText("���");
                break;
            }

            case "���ÿ�":
            {
                typepic.setImageResource(R.drawable.ft_creditcard1);
                mTv_Text1.setText("���ÿ�");
                break;
            }
            case "֧����":
            {
                typepic.setImageResource(R.drawable.ft_wangluochongzhi1);
                mTv_Text1.setText("֧����");
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
