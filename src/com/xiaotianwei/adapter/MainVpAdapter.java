package com.xiaotianwei.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainVpAdapter extends FragmentPagerAdapter {
    private List<Fragment>mFragments;
    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }
    public MainVpAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        this.mFragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {  //返回每一个界面对应的fragment对象
        return mFragments.get(position);
    }

    @Override
    public int getCount() { //返回Fragment的数量
        return mFragments.size();
    }
}
