package com.xiaotianwei.activity;
import com.briup.xiaotianwei.R;
import com.xiaotianwei.bean.User;
import com.xiaotianwei.db.UserDao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText mEt_username,mEtpassword;
    private Button m_Btn_login,mBtn_register;
    private TextView m_Tv_forgetpwd;
    private ImageView m_Iv_back;
    private UserDao dao;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null){
            getActionBar().hide();
        }
        setContentView(R.layout.fragment_login);
        initView();
        initData();
        initListerner();
    }

    private void initListerner() {
        m_Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginstate;
                dao=new UserDao(LoginActivity.this);
                String username=mEt_username.getText().toString();
                String password=mEtpassword.getText().toString();
                if(username.equals("")||password.equals("")){           //如果输入框得到空值
                    Toast.makeText(LoginActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                }
                else{
                User user=new User(username,password);
                loginstate=dao.veryfyUser(user);
                if(loginstate==true)    //登陆成功后的逻辑事件
                {
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    sharedPreferences=getSharedPreferences("Login_state",MODE_APPEND);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //注册按钮点击事件，跳转到注册页面
                Intent i=new Intent();
                i.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        m_Iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); //标题栏返回箭头点击事件
        m_Tv_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //忘记密码点击事件，跳转到Forgetpwd.activity
                Intent i=new Intent();
                i.setClass(LoginActivity.this,ForgetPwd.class);
                startActivity(i);
            }
        });

    }

    private void initData() {

    }

    private void initView() {
        mEt_username= (EditText) findViewById(R.id.login_Et_username);
        mEtpassword= (EditText) findViewById(R.id.login_Et_password);
        m_Btn_login= (Button) findViewById(R.id.login_Btn_login);
        mBtn_register= (Button) findViewById(R.id.login_Btn_register);
        m_Tv_forgetpwd= (TextView) findViewById(R.id.login_Tv_forgetpwd);
        m_Iv_back= (ImageView) findViewById(R.id.login_back);
    }
}
