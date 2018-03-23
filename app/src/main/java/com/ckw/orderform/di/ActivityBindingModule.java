package com.ckw.orderform.di;

import com.ckw.orderform.MainActivity;
import com.ckw.orderform.MainActivityModule;
import com.ckw.orderform.modify.ModifyActivity;
import com.ckw.orderform.modify.ModifyActivityModule;
import com.ckw.orderform.search.SearchActivity;
import com.ckw.orderform.search.SearchActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2017/12/7.
 * 所有的activity的绑定在这里
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity searchActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ModifyActivityModule.class)
    abstract ModifyActivity modifyActivity();

}
