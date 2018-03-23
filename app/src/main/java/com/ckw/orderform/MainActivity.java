package com.ckw.orderform;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.ckw.orderform.adapters.FragmentAdapter;
import com.ckw.orderform.base.BaseActivity;
import com.ckw.orderform.fragments.CreateOrderFragment;
import com.ckw.orderform.fragments.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Inject
    CreateOrderFragment mCreateOrderFragment;

    @Inject
    OrderListFragment mOrderListFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initViewPager();
        initTabLayout();
    }


    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setToolbar() {

    }

    private void initViewPager(){
        String[] arrTabTitles = new String[]{"创建订单","订单列表"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mCreateOrderFragment);
        fragments.add(mOrderListFragment);

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, arrTabTitles));
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
