package com.xiaotianwei.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import com.briup.xiaotianwei.R;



public class Jizhangtixing extends Activity {
    private ImageView mIv_gengduo_back,mIv_gengduo_timeselector;
    private TextView mIv_gengduo_showtime;
    private TimePicker timePicker;
    private Button mBtn_gengduo_save;
    private int mHou;
    private int min;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null){   //璁剧疆activity娌℃湁鏍囬鏍�
            getActionBar().hide();
        }
        setContentView(R.layout.activity_jizhang);
        initView();
        initData();
        initListener();
    }

    private void initData() {

    }

    protected void onResume() {

        super.onResume();
    }

    private void initListener() {
        mIv_gengduo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });        //璁剧疆鏍囬鏍忚繑鍥炴寜閽偣鍑讳簨浠�

        //+++++++++++++++++++++++
        mBtn_gengduo_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //璁剧疆淇濊祼浣犵偣鍑讳簨浠�
                Toast.makeText(Jizhangtixing.this, "淇濆瓨鎴愬姛", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mIv_gengduo_timeselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //璁剧疆鏃堕棿閫夋嫨鍣ㄦ椂闂寸洃鍚�
                            //***************************
                 View view=getLayoutInflater().inflate(R.layout.dialog_gengduo_timepicker,null);
                final TimePicker timePicker= (TimePicker) view.findViewById(R.id.item_timepicker);
                AlertDialog.Builder builder=new AlertDialog.Builder(Jizhangtixing.this);
                builder.setCancelable(true);   //鐐瑰嚮澶栭儴涓嶅彲鍙栨秷
                builder.setView(view);
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {     //寰楀埌閫夋嫨鐨勬椂闂�
                       mHou=hourOfDay;min=minute;
                    }
                });

                builder.setPositiveButton("纭畾", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       /* int mHour=timePicker.getCurrentHour();
                        int mMinuts=timePicker.getCurrentMinute();
                        Toast.makeText(Jizhangtixing.this, mHour+""+mMinuts, Toast.LENGTH_SHORT).show();*/
                        if(min<10)
                        mIv_gengduo_showtime.setText(mHou+":0"+min);
                        else
                            mIv_gengduo_showtime.setText(mHou+":"+min);     //璁剧疆鏃堕棿
                    }
                });
                builder.setNegativeButton("鍙栨秷", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();

            }
        });
    }

    private void initView() {
        mIv_gengduo_back= (ImageView) findViewById(R.id.gengduo_Iv_back);
        //mIv_gengduo_showtime= (TextView) findViewById(gengduo_Tv_showTime);
        mBtn_gengduo_save= (Button) findViewById(R.id.gengduo_Btn_save);
        mIv_gengduo_timeselector= (ImageView) findViewById(R.id.gengduo_Im_timeselector);
        timePicker= (TimePicker) findViewById(R.id.item_timepicker);
    }
}
