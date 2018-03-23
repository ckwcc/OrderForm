package com.ckw.orderform.modify;

import com.ckw.orderform.NetLoader.ApiService;
import com.ckw.orderform.NetLoader.HttpManager;
import com.ckw.orderform.di.ActivityScoped;
import com.ckw.orderform.di.FragmentScoped;
import com.ckw.orderform.presenter.ModifyContract;
import com.ckw.orderform.presenter.ModifyPresenter;
import com.ckw.orderform.presenter.OrderContract;
import com.ckw.orderform.presenter.OrderPresenter;
import com.ckw.orderform.search.SearchFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2018/3/23.
 */
@Module
public abstract class ModifyActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ModifyFragment modifyFragment();

    @ActivityScoped
    @Provides
    ModifyContract.Presenter modifyPresenter(HttpManager httpManager, ApiService apiService){
        ModifyContract.Presenter modifyPresenter = new ModifyPresenter(httpManager,apiService);
        return modifyPresenter;
    }
}
