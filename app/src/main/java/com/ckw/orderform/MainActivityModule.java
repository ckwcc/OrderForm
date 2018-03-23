package com.ckw.orderform;

import com.ckw.orderform.NetLoader.ApiService;
import com.ckw.orderform.NetLoader.HttpManager;
import com.ckw.orderform.di.ActivityScoped;
import com.ckw.orderform.di.FragmentScoped;
import com.ckw.orderform.fragments.CreateOrderFragment;
import com.ckw.orderform.fragments.OrderListFragment;
import com.ckw.orderform.presenter.OrderContract;
import com.ckw.orderform.presenter.OrderPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2018/3/20.
 */
@Module
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CreateOrderFragment createOrderFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract OrderListFragment orderListFragment();

    @ActivityScoped
    @Provides
    OrderContract.Presenter loginPresenter(HttpManager httpManager, ApiService apiService){
        OrderPresenter loginPresenter = new OrderPresenter(httpManager,apiService);
        return loginPresenter;
    }


}
