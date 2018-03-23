package com.ckw.orderform.modify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.ckw.orderform.R;
import com.ckw.orderform.base.BaseActivity;
import com.ckw.orderform.repository.OrderBean;
import com.ckw.orderform.utils.FragmentActivityUtils;

/**
 * Created by ckw
 * on 2018/3/23.
 */

public class ModifyActivity extends BaseActivity {
    private ModifyFragment modifyFragment;
    private FragmentManager manager;
    private OrderBean mOrderBean;
    @Override
    protected void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        modifyFragment = (ModifyFragment) manager.findFragmentById(R.id.modify_container);
        if(modifyFragment == null){
            modifyFragment = ModifyFragment.newInstance(mOrderBean);
            FragmentActivityUtils.addFragmentToActivity(manager,modifyFragment,R.id.modify_container);
        }
    }

    @Override
    protected void handleBundle(@NonNull Bundle bundle) {
        mOrderBean = bundle.getParcelable("orderBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setToolbar() {

    }
}
