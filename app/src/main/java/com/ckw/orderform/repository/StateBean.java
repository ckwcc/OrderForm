package com.ckw.orderform.repository;

/**
 * Created by ckw
 * on 2018/3/23.
 */

public class StateBean {

    public StateBean() {

    }

    private Integer state;         //状态  0 未完成 1 已完成

    private String customerName;  //客户

    private String sizeXiang;

    public String getSizeXiang() {
        return sizeXiang;
    }

    public void setSizeXiang(String sizeXiang) {
        this.sizeXiang = sizeXiang;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StateBean{" +
                "state=" + state +
                ", customerName='" + customerName + '\'' +
                ", sizeXiang='" + sizeXiang + '\'' +
                '}';
    }
}
