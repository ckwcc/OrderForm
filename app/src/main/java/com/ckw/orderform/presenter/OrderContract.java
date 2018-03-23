package com.ckw.orderform.presenter;

import com.ckw.orderform.base.BasePresenter;
import com.ckw.orderform.base.BaseView;
import com.ckw.orderform.repository.OrderListBean;



/**
 * Created by ckw
 * on 2018/3/21.
 */

public class OrderContract {
    public interface View extends BaseView {

        void getAllOrderSuccess(OrderListBean data);

        void addOrderSuccess(String msg);

        void deleteSuccess(String msg);

        //更新，确认
        void updateSuccess(String msg);

        void showLoginFailure(String msg);

    }

    public interface Presenter extends BasePresenter<View> {

        //得到所有的数据
        void getAllOrderList();

        void getSearchOrderList(String jsonStr);

        void addOrder(String jsonStr);

        void deleteOrder(String jsonStr);

        void updateOrder(String jsonStr);

    }
}
