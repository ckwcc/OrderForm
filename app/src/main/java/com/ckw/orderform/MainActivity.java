package com.ckw.orderform;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

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

    private FragmentAdapter mAdapter;

    @Inject
    CreateOrderFragment mCreateOrderFragment;

    @Inject
    OrderListFragment mOrderListFragment;
    private long mExitTime;

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
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, arrTabTitles);
        mViewPager.setAdapter(mAdapter);
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //1.点击返回键条件成立
        if(keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {

            //2.点击的时间差如果大于2000，则提示用户点击两次退出
            if(System.currentTimeMillis() - mExitTime > 2000) {
                //3.保存当前时间

                mExitTime  = System.currentTimeMillis();

                //4.提示
                Toast.makeText(this,"再按一次返回退出App",Toast.LENGTH_SHORT).show();

            } else {
                //5.点击的时间差小于2000，退出。

                finish();
                System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
