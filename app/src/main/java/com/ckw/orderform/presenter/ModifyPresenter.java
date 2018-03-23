package com.ckw.orderform.presenter;

import com.ckw.orderform.NetLoader.ApiService;
import com.ckw.orderform.NetLoader.CallBackListener;
import com.ckw.orderform.NetLoader.HttpManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2018/3/23.
 */

public class ModifyPresenter implements ModifyContract.Presenter{
    private CompositeDisposable mCompositeDisposable;
    private ModifyContract.View mView;
    private HttpManager mHttpManager;
    private ApiService mApiService;

    @Inject
    public ModifyPresenter(HttpManager mHttpManager, ApiService mApiService) {
        this.mHttpManager = mHttpManager;
        this.mApiService = mApiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(ModifyContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void updateOrder(String jsonStr) {
        mHttpManager.request(mApiService.updateOrder(jsonStr), mCompositeDisposable, mView, new CallBackListener<String>() {
            @Override
            public void onSuccess(String data) {
                mView.updateSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mView.showUpdateFailure(errorMsg);
            }
        });
    }
}
