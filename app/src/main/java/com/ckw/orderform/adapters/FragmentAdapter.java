package com.ckw.orderform.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ckw
 * on 2018/3/20.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = null;
    private String[] tabTitles = null;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments , String[] arrTabTitles) {
        super(fm);
        this.fragments = fragments;
        this.tabTitles = arrTabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
