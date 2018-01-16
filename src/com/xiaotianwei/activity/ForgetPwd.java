package com.xiaotianwei.activity;
import com.briup.xiaotianwei.R;
import com.xiaotianwei.bean.User;
import com.xiaotianwei.db.UserDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgetPwd extends Activity {
    private EditText mEt_username,mEt_password,mEt_repassword;
    private Button mBtn_reset,mBtn_clear;
    private ImageView mIv_back;
    private  UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forget_pwd);
        initView();
        initListener();
    }
	private void initView() {
		// TODO Auto-generated method stub
		 mEt_username= (EditText) findViewById(R.id.forget_Et_username);
	        mEt_password= (EditText) findViewById(R.id.forget_Et_password);
	        mEt_repassword= (EditText) findViewById(R.id.forget_Et_password1);
	        mBtn_reset= (Button) findViewById(R.id.forget_Btn_reset);
	        mBtn_clear= (Button) findViewById(R.id.forget_Btn_clear);
	        mIv_back= (ImageView) findViewById(R.id.reset_back);
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		mBtn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //�������edittext�¼�
                mEt_username.setText("");
                mEt_password.setText("");
                mEt_repassword.setText("");
            }
        });
        mBtn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //�������������¼�
                String username=mEt_username.getText().toString();
                String password=mEt_password.getText().toString();
                String repassword=mEt_repassword.getText().toString();
                dao=new UserDao(ForgetPwd.this);
                if(username.equals("")||password.equals("")||repassword.equals("")) //��������Ϊ��
                {
                    Toast.makeText(ForgetPwd.this, "�������û���������", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.equals(repassword))  //������������һ��
                    {
                        int getid=dao.queryUserbybname(username);
                        if(getid!=-1)   //�û�����
                        {
                            int flag=0; //0��ʾ�û������ڣ�1��ʾ�û�����
                            User user=new User(username,password);
                            flag=dao.resetUser(username,user);
                            if(flag==1)
                                Toast.makeText(ForgetPwd.this, "���óɹ�", Toast.LENGTH_SHORT).show();      //���óɹ�
                            else
                            {
                                Toast.makeText(ForgetPwd.this, "����ʧ�ܣ��û�������", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else    //�û�������
                        {
                            Toast.makeText(ForgetPwd.this, "����ʧ�ܣ��û�������", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(ForgetPwd.this, "�����������벻һ��", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });             //���ذ�ť�����¼�
		
	}

    

 
}


