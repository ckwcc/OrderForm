package com.ckw.orderform.search;

import android.support.transition.Visibility;

import com.ckw.orderform.di.FragmentScoped;
import com.ckw.orderform.fragments.CreateOrderFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ckw
 * on 2018/3/22.
 */
@Module
public abstract class SearchActivityModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();
}
