package com.ckw.orderform.presenter;

import com.ckw.orderform.base.BasePresenter;
import com.ckw.orderform.base.BaseView;

/**
 * Created by ckw
 * on 2018/3/23.
 */

public class ModifyContract {
    public interface View extends BaseView{
        void updateSuccess(String msg);

        void showUpdateFailure(String msg);
    }

    public interface Presenter extends BasePresenter<View>{
        void updateOrder(String jsonStr);
    }
}
