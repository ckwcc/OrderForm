package com.ckw.orderform.presenter;

import android.util.Log;

import com.ckw.orderform.NetLoader.ApiService;
import com.ckw.orderform.NetLoader.CallBackListener;
import com.ckw.orderform.NetLoader.HttpManager;
import com.ckw.orderform.repository.OrderBean;
import com.ckw.orderform.repository.OrderListBean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ckw
 * on 2018/3/21.
 */

public class OrderPresenter implements OrderContract.Presenter {

    private CompositeDisposable mCompositeDisposable;
    private OrderContract.View mLoginView;
    private HttpManager mHttpManager;
    private ApiService mApiService;

    @Inject
    public OrderPresenter(HttpManager httpManager, ApiService apiService) {
        this.mHttpManager = httpManager;
        this.mApiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(OrderContract.View view) {
        mLoginView = view;
    }

    @Override
    public void dropView() {
        mCompositeDisposable.clear();
        mLoginView = null;
    }


    /*
    * 得到全部的order
    * */
    @Override
    public void getAllOrderList() {
        mHttpManager.request(mApiService.getAllOrder(), mCompositeDisposable, mLoginView, new CallBackListener<OrderListBean>() {

            @Override
            public void onSuccess(OrderListBean data) {
                mLoginView.getAllOrderSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mLoginView.showLoginFailure(errorMsg);
            }
        } );
    }

    /*
    * 得到搜索的order
    * */
    @Override
    public void getSearchOrderList(String jsonStr) {
        mHttpManager.request(mApiService.getAllOrder(jsonStr), mCompositeDisposable, mLoginView, new CallBackListener<OrderListBean>() {
            @Override
            public void onSuccess(OrderListBean data) {
                mLoginView.getAllOrderSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mLoginView.showLoginFailure(errorMsg);
            }
        });
    }

    @Override
    public void addOrder(String jsonStr) {
        mHttpManager.request(mApiService.addOrder(jsonStr), mCompositeDisposable, mLoginView, new CallBackListener<String>() {
            @Override
            public void onSuccess(String data) {
                mLoginView.addOrderSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mLoginView.showLoginFailure(errorMsg);
            }
        });
    }

    @Override
    public void deleteOrder(String jsonStr) {
        mHttpManager.request(mApiService.deleteOrder(jsonStr), mCompositeDisposable, mLoginView, new CallBackListener<String>() {
            @Override
            public void onSuccess(String data) {
                mLoginView.deleteSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mLoginView.showLoginFailure(errorMsg);
            }
        });
    }

    @Override
    public void updateOrder(String jsonStr) {
        mHttpManager.request(mApiService.updateOrder(jsonStr), mCompositeDisposable, mLoginView, new CallBackListener<String>() {
            @Override
            public void onSuccess(String data) {
                mLoginView.updateSuccess(data);
            }

            @Override
            public void onError(String errorMsg) {
                mLoginView.showLoginFailure(errorMsg);
            }
        });
    }
}
