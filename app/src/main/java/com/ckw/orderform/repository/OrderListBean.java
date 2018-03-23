package com.ckw.orderform.repository;

import java.util.List;

/**
 * Created by ckw
 * on 2018/3/22.
 */

public class OrderListBean {
    List<OrderBean> orderList;

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }
}
