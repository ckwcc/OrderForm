package com.ckw.orderform.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ckw.orderform.R;
import com.ckw.orderform.base.BaseFragment;

import butterknife.BindView;
import scut.carson_ho.searchview.ICallBack;
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

        // 5. 设置点击返回按键后的操作（通过回调接口）
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                getActivity().finish();
            }
        });
    }
}
