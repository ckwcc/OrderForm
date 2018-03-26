package com.ckw.orderform.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ckw.orderform.R;
import com.ckw.orderform.base.BaseFragment;

import butterknife.BindView;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

/**
 * Created by ckw
 * on 2018/3/22.
 */

public class SearchFragment extends BaseFragment {

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mSearchStr;

    @BindView(R.id.search_view)
    scut.carson_ho.searchview.SearchView mSearchView;

    @BindView(R.id.search_size)
    SearchView mSearchSizeView;

    @BindView(R.id.btn_mode_one)
    Button mModeOne;

    @BindView(R.id.btn_mode_two)
    Button mModeTwo;

    @Override
    public void initPresenter() {
        
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {
        // 4. 设置点击键盘上的搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        mSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Intent intent = new Intent();
                intent.putExtra("search",string);
                getActivity().setResult(100,intent);
                getActivity().finish();
            }
        });

        mSearchSizeView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Intent intent = new Intent();
                intent.putExtra("search",string);
                getActivity().setResult(101,intent);
                getActivity().finish();
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                getActivity().finish();
            }
        });

        mSearchSizeView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                getActivity().finish();
            }
        });

        mModeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchSizeView.setVisibility(View.INVISIBLE);
                mSearchView.setVisibility(View.VISIBLE);
            }
        });

        mModeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.setVisibility(View.INVISIBLE);
                mSearchSizeView.setVisibility(View.VISIBLE);
            }
        });
    }
}
