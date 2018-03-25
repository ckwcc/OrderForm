package com.ckw.orderform.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ckw.orderform.R;
import com.ckw.orderform.adapters.RecyclerViewAdapter;
import com.ckw.orderform.base.BaseFragment;
import com.ckw.orderform.modify.ModifyActivity;
import com.ckw.orderform.presenter.OrderContract;
import com.ckw.orderform.presenter.OrderPresenter;
import com.ckw.orderform.repository.OrderBean;
import com.ckw.orderform.repository.OrderListBean;
import com.ckw.orderform.repository.StateBean;
import com.ckw.orderform.search.SearchActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2018/3/20.
 */

public class OrderListFragment extends BaseFragment implements OrderContract.View, RecyclerViewAdapter.ButtonClickListener {

    private String mSearchStr;

    public static OrderListFragment newInstance() {

        Bundle args = new Bundle();

        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Inject
    public OrderListFragment( ) {
    }

    private List<OrderBean> mData;

    @Inject
    OrderPresenter mPresenter;

//    @BindView(R.id.fab)
//    FloatingActionButton mFab;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.search_container)
    RelativeLayout mRlSearch;
    private RecyclerViewAdapter mAdapter;

    private Button mConfirmView;//确认

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void initVariables() {
        //得到所有的order
        mPresenter.getAllOrderList();
    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            Log.d("flag", "----------------->setUserVisibleHint: 用户可见");
            mPresenter.getAllOrderList();
        }
    }

    @Override
    protected void operateViews(View view) {
        initRecyclerView();
    }

    @Override
    protected void initListener() {
        mRlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(intent,1);
            }
        });

//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPresenter.getAllOrderList();
//            }
//        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 100){
            mSearchStr = data.getStringExtra("search");
            if("1".equals(mSearchStr)){
                StateBean stateBean = new StateBean();
                stateBean.setState(1);
                String transToJson = transStateToJson(stateBean);
                mPresenter.getSearchOrderList(transToJson);
            }else if ("0".equals(mSearchStr)){
                StateBean stateBean = new StateBean();
                stateBean.setState(0);
                String transToJson = transStateToJson(stateBean);
                mPresenter.getSearchOrderList(transToJson);
            }else {
                StateBean stateBean = new StateBean();
                stateBean.setCustomerName(mSearchStr);
                String transToJson = transStateToJson(stateBean);
                mPresenter.getSearchOrderList(transToJson);
            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void initRecyclerView(){
        mData = new ArrayList<>();

        mAdapter = new RecyclerViewAdapter(mData,getContext(),this);
        //绑定适配器
        mRecyclerView.setAdapter(mAdapter);

        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void getAllOrderSuccess(OrderListBean data) {
        Log.d("----", "getAllOrderSuccess: 得到数据的长度："+data.getOrderList().size());
        mData.clear();
        mData.addAll(data.getOrderList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addOrderSuccess(String msg) {
    }

    @Override
    public void deleteSuccess(String msg) {
        mPresenter.getAllOrderList();
    }

    @Override
    public void updateSuccess(String msg) {
        mConfirmView.setText("已确认");
        mPresenter.getAllOrderList();
        Toast.makeText(getContext(),"确认成功",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showLoginFailure(String msg) {
        Log.d("----", "showLoginFailure: "+msg);
    }

    @Override
    public void onDeleteButtonClick(int pos) {
        OrderBean orderBean = mData.get(pos);
        int id = orderBean.getId();
        OrderBean paramBean = new OrderBean();
        paramBean.setId(id);
        String s = transToJson(paramBean);
        mPresenter.deleteOrder(s);
    }

    @Override
    public void onConfirmButtonClick(int pos,Button view) {
        OrderBean orderBean = mData.get(pos);
        int id = orderBean.getId();

        OrderBean newOrder = new OrderBean();
        newOrder.setId(id);
        newOrder.setState(1);
        String transToJson = transToJson(newOrder);
        mPresenter.updateOrder(transToJson);
        mConfirmView = view;

    }

    @Override
    public void onUpdateButtonClick(int pos) {
        Intent intent = new Intent(getContext(), ModifyActivity.class);
        OrderBean orderBean = mData.get(pos);
        intent.putExtra("orderBean",orderBean);
        startActivity(intent);
    }


    private String transToJson(OrderBean orderBean){
        Gson gson = new Gson();
        return gson.toJson(orderBean);
    }

    private String transStateToJson(StateBean stateBean){
        Gson gson = new Gson();
        return gson.toJson(stateBean);
    }

    private String transIntToJson(int i){
        Gson gson = new Gson();
        return gson.toJson(i);
    }
}
