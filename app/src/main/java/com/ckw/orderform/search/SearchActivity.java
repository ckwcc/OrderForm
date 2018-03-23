package com.ckw.orderform.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.ckw.orderform.R;
import com.ckw.orderform.base.BaseActivity;
import com.ckw.orderform.utils.FragmentActivityUtils;

/**
 * Created by ckw
 * on 2018/3/22.
 */

public class SearchActivity extends BaseActivity {
    private SearchFragment mSearchFragment;
    private FragmentManager manager;

    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        mSearchFragment = (SearchFragment) manager.findFragmentById(R.id.search_container);
        if(mSearchFragment == null){
            mSearchFragment = SearchFragment.newInstance();
            FragmentActivityUtils.addFragmentToActivity(manager,mSearchFragment,R.id.search_container);
        }
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setToolbar() {

    }
}
