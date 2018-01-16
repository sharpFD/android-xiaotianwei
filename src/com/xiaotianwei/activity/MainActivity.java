package com.xiaotianwei.activity;

import java.util.ArrayList;
import java.util.List;

import com.briup.xiaotianwei.R;
import com.xiaotianwei.adapter.MainVpAdapter;
import com.xiaotianwei.fragment.Baobiaofragment;
import com.xiaotianwei.fragment.Gengduofragment;
import com.xiaotianwei.fragment.Jizhangfragment;
import com.xiaotianwei.fragment.Zijinfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
 

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private ImageView mIv_jizhang,mIv_baobiao,mIv_zijin,mIv_gengduo;
    private TextView mTv_jizhang,mTv_baobiao,mTv_zijin,mTv_gengduo;
    private Fragment mJizhangFragment,mBaobiaoFragment,mZijinFragment,mGengduoFragment;
    private List<Fragment> mFragments;
    private ViewPager mVp_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null){         //设置标题栏隐藏
            getActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initListener() {
        mVp_show.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动结束的监听
            }

            @Override
            public void onPageSelected(int position) {
                //当界面被选中的时候
                switch (position) {
                    case 0: {
                        refresh(R.id.main_Iv_jizhang);
                        break;
                    }
                    case 1: {
                        refresh(R.id.main_Iv_baobiao);
                        break;
                    }
                    case 2: {
                        refresh(R.id.main_Iv_zijin);
                        break;
                    }
                    case 3: {
                        refresh(R.id.main_Iv_gengduo);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态改变的监听
            }
        });

    }

    private void initAdapter() {
        mVp_show.setAdapter(new MainVpAdapter(getSupportFragmentManager(), mFragments));
    }

    private void initData() {
        mJizhangFragment=new Jizhangfragment();
        mBaobiaoFragment=new Baobiaofragment();
        mZijinFragment=new Zijinfragment();
        mGengduoFragment=new Gengduofragment();  //实例化四个fragment
        mFragments=new ArrayList<>(); //实例化fragmentmanager
        mFragments.add(mJizhangFragment);   //将不同的fragment添加到fragmentmanager上
        mFragments.add(mBaobiaoFragment);
        mFragments.add(mZijinFragment);
        mFragments.add(mGengduoFragment);
    }

    private void initView() {
        mIv_jizhang= (ImageView) findViewById(R.id.main_Iv_jizhang);
        mIv_baobiao= (ImageView) findViewById(R.id.main_Iv_baobiao);
        mIv_zijin= (ImageView) findViewById(R.id.main_Iv_zijin);
        mIv_gengduo= (ImageView) findViewById(R.id.main_Iv_gengduo);

        mTv_jizhang= (TextView) findViewById(R.id.main_Tv_jizhang);
        mTv_baobiao= (TextView) findViewById(R.id.main_Tv_baobiao);
        mTv_zijin= (TextView) findViewById(R.id.main_Tv_zijin);
        mTv_gengduo= (TextView) findViewById(R.id.main_Tv_gengduo);

        mVp_show= (ViewPager) findViewById(R.id.main_vp);

        mIv_jizhang.setOnClickListener(this);
        mIv_baobiao.setOnClickListener(this);
        mIv_zijin.setOnClickListener(this);
        mIv_gengduo.setOnClickListener(this);
        startview();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.main_Iv_jizhang:      //当底部点击记账fragment时
            {
                mVp_show.setCurrentItem(0);   //设置当前item为0
                refresh(R.id.main_Iv_jizhang);  //设置当前点击的按钮变色，此处为标为绿色
                break;
            }
            case R.id.main_Iv_baobiao:         //当底部点击报表fragment时
            {
                mVp_show.setCurrentItem(1);
                refresh(R.id.main_Iv_baobiao);
                break;
            }
            case R.id.main_Iv_zijin:            //当底部点击资金fragment时
            {
                mVp_show.setCurrentItem(2);
                refresh(R.id.main_Iv_zijin);
                break;
            }
            case R.id.main_Iv_gengduo:          //当底部点击更多fragment时
            {
                mVp_show.setCurrentItem(3);
                refresh(R.id.main_Iv_gengduo);
                break;
            }
        }
    }
    public void refresh(int id)  //设置当前选中状态变色
    {
        switch (id)
        {
            case R.id.main_Iv_jizhang:
            {
                clear();                        //将底部fragment的颜色全部设为灰色（未选择状态）
                mIv_jizhang.setImageResource(R.drawable.tab_accounte);  //为点击的按钮设置图片
                mTv_jizhang.setTextColor(Color.parseColor("#00ff00"));  //为选中的按钮的文字设置为绿色
                break;
            }
            case R.id.main_Iv_baobiao:
            {
                clear();                        //将底部fragment的颜色全部设为灰色（未选择状态）
                mIv_baobiao.setImageResource(R.drawable.tab_form);//为点击的按钮设置图片
                mTv_baobiao.setTextColor(Color.parseColor("#00ff00"));//为选中的按钮的文字设置为绿色
                break;
            }
            case R.id.main_Iv_zijin:
            {
                clear();
                mIv_zijin.setImageResource(R.drawable.tab_founds);
                mTv_zijin.setTextColor(Color.parseColor("#00ff00"));
                break;
            }
            case R.id.main_Iv_gengduo:
            {
                clear();
                mIv_gengduo.setImageResource(R.drawable.tab_mine);
                mTv_gengduo.setTextColor(Color.parseColor("#00ff00"));
                break;
            }
        }
    }
    public void clear()//清空所有选中状态
    {
        mIv_jizhang.setImageResource(R.drawable.tab_accounte2);
        mIv_baobiao.setImageResource(R.drawable.tab_form2);
        mIv_zijin.setImageResource(R.drawable.tab_founds2);
        mIv_gengduo.setImageResource(R.drawable.tab_mine2);

        mTv_jizhang.setTextColor(Color.parseColor("#959595"));
        mTv_baobiao.setTextColor(Color.parseColor("#959595"));
        mTv_zijin.setTextColor(Color.parseColor("#959595"));
        mTv_gengduo.setTextColor(Color.parseColor("#959595"));
    }
    public void startview()
    {
        mIv_jizhang.setImageResource(R.drawable.tab_accounte);
        mTv_jizhang.setTextColor(Color.parseColor("#00ff00"));
    }

}