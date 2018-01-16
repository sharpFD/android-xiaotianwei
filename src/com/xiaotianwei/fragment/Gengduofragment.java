package com.xiaotianwei.fragment;
import com.briup.xiaotianwei.R;
import com.xiaotianwei.activity.Jizhangtixing;
import com.xiaotianwei.activity.LoginActivity;
import com.xiaotianwei.activity.MyInfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Will_Jack on 2017/1/4.
 */

public class Gengduofragment extends Fragment {
    private ImageView mIv_touxiang,mIv_setting;
    private TextView mTv_logininfo;
    private SharedPreferences sharedPreferences;
    private RelativeLayout jizhang,tuijian,yijian,haoping,guanyu;
    private int flag=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gengduo,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initData() {


    }

    private void initListener() {
        mIv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                final String[] nameArrStrings = new String[] { "�´���Ȼ��½", "�´β��ٵ�½"};
                builder.setTitle("�´��Ƿ��¼");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setSingleChoiceItems(nameArrStrings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        flag=which;
                    }
                });
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });


        mIv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTv_logininfo.getText().toString().equals("���¼"))
                {Intent i=new Intent();
                i.setClass(getActivity(), LoginActivity.class);
                startActivity(i);}
                else
                {
                    Intent i=new Intent();
                    i.setClass(getActivity(), MyInfo.class);
                    startActivity(i);
                }

            }
        });

        jizhang.setOnClickListener(new View.OnClickListener() {    //��������
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(getActivity(), Jizhangtixing.class);
                startActivity(i);
            }
        });


        tuijian.setOnClickListener(new View.OnClickListener() {  //�Ƽ�
            @Override
            public void onClick(View v) {
                final View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_gengduo_tuijian,null);
                final ImageView pengyouquan= (ImageView) view.findViewById(R.id.pengyouquan);
                final ImageView QQ= (ImageView) view.findViewById(R.id.QQ);
                final ImageView weibo= (ImageView) view.findViewById(R.id.weibo);
                final ImageView weixin= (ImageView) view.findViewById(R.id.weixin);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setTitle("�Ƽ�");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setView(view);
                pengyouquan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "��Ǹ����û������Ȧ", Toast.LENGTH_SHORT).show();
                    }
                });

                QQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "��Ǹ����û�а�װQQ", Toast.LENGTH_SHORT).show();
                    }
                });

                weixin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "��Ǹ����û�а�װ΢��", Toast.LENGTH_SHORT).show();
                    }
                });

                weibo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "��Ǹ����û�а�װ΢��", Toast.LENGTH_SHORT).show();
                    }
                });
                
                
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            
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

        yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setTitle("�������");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setView(R.layout.dialog_gengduo_edittext);
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "��л�������ǵ����", Toast.LENGTH_SHORT).show();
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

        haoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_gengduo_rattingbar,null);
                final RatingBar ratingBar= (RatingBar) view.findViewById(R.id.gengduo_ratingbar_ratingbar);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setTitle("��������");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setView(view);
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        float get=ratingBar.getRating();
                        Toast.makeText(getActivity(), "��л����"+get+"�Ǻ���", Toast.LENGTH_SHORT).show();
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


        guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);   //����ⲿ����ȡ��
                builder.setTitle("��������");
                builder.setIcon(R.drawable.ic_launcher);
                builder.setMessage("��Ƴ���Ա�������Ŀ���");
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
    }

    private void initView(View view) {
        mIv_touxiang= (ImageView) view.findViewById(R.id.gengduo_Iv_touxiang);
        mIv_setting= (ImageView) view.findViewById(R.id.gengduo_Iv_setting );
        mTv_logininfo= (TextView) view.findViewById(R.id.gengduo_Tv_qingdenglu);
        jizhang= (RelativeLayout) view.findViewById(R.id.fragment_gengduo_jizhangtixing);
        tuijian= (RelativeLayout) view.findViewById(R.id.fragment_gengduo_tuijian);
        yijian= (RelativeLayout) view.findViewById(R.id.fragment_gengduo_yijian);
        haoping= (RelativeLayout) view.findViewById(R.id.fragment_gengduo_haoping);
        guanyu= (RelativeLayout) view.findViewById(R.id.fragment_gengduo_guanyu);
    }

    @Override
    public void onResume() {
        sharedPreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
        if(sharedPreferences.getString("username",null)!=null)
        {
            //Toast.makeText(getActivity(), "������"+sharedPreferences.getString("username",null), Toast.LENGTH_SHORT).show();
            mIv_touxiang.setImageResource(R.drawable.login_mine_pic);
            mTv_logininfo.setText(sharedPreferences.getString("username",null));
        }
        else
        {
          //  Toast.makeText(getActivity(), "û����", Toast.LENGTH_SHORT).show();
            mIv_touxiang.setImageResource(R.drawable.mine_pic_nor);
            mTv_logininfo.setText("���¼");
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        if (flag==1)
        {
            sharedPreferences=getActivity().getSharedPreferences("Login_state",Context.MODE_APPEND);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",null);
            editor.putString("password",null);
            editor.commit();
        }
        super.onDetach();
    }
}
