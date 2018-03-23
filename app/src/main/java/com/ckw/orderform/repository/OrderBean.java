package com.ckw.orderform.repository;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ckw
 * on 2018/3/20.
 */

public class OrderBean implements Parcelable{

    private int id;
    private String customerName;  //客户
    private String kind;           //种类
    private Integer num;          //数量
    private Double price;          //单价
    private String ceng;            //层数
    private String sizeXiang;         //箱尺寸
    private String sizeBan;         //板尺寸
    private String sizeYa;			//尺寸
    private String peizhi;          //配置
    private Integer isYinshua;		//是否印刷  0 不印刷 1 印刷
    private String yinshuaText;     //印刷内容
    private Integer state;         //状态  0 未完成 1 已完成
    private Integer priority;       // 0 1 2 3 4 5 （5最高）

    public OrderBean(){

    }

    protected OrderBean(Parcel in) {
        id = in.readInt();
        customerName = in.readString();
        kind = in.readString();
        if (in.readByte() == 0) {
            num = null;
        } else {
            num = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        ceng = in.readString();
        sizeXiang = in.readString();
        sizeBan = in.readString();
        sizeYa = in.readString();
        peizhi = in.readString();
        if (in.readByte() == 0) {
            isYinshua = null;
        } else {
            isYinshua = in.readInt();
        }
        yinshuaText = in.readString();
        if (in.readByte() == 0) {
            state = null;
        } else {
            state = in.readInt();
        }
        if (in.readByte() == 0) {
            priority = null;
        } else {
            priority = in.readInt();
        }
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCeng() {
        return ceng;
    }

    public void setCeng(String ceng) {
        this.ceng = ceng;
    }

    public String getSizeXiang() {
        return sizeXiang;
    }

    public void setSizeXiang(String sizeXiang) {
        this.sizeXiang = sizeXiang;
    }

    public String getSizeBan() {
        return sizeBan;
    }

    public void setSizeBan(String sizeBan) {
        this.sizeBan = sizeBan;
    }

    public String getSizeYa() {
        return sizeYa;
    }

    public void setSizeYa(String sizeYa) {
        this.sizeYa = sizeYa;
    }

    public String getPeizhi() {
        return peizhi;
    }

    public void setPeizhi(String peizhi) {
        this.peizhi = peizhi;
    }

    public Integer getIsYinshua() {
        return isYinshua;
    }

    public void setIsYinshua(Integer isYinshua) {
        this.isYinshua = isYinshua;
    }

    public String getYinshuaText() {
        return yinshuaText;
    }

    public void setYinshuaText(String yinshuaText) {
        this.yinshuaText = yinshuaText;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", kind='" + kind + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", ceng='" + ceng + '\'' +
                ", sizeXiang='" + sizeXiang + '\'' +
                ", sizeBan='" + sizeBan + '\'' +
                ", sizeYa='" + sizeYa + '\'' +
                ", peizhi='" + peizhi + '\'' +
                ", isYinshua=" + isYinshua +
                ", yinshuaText='" + yinshuaText + '\'' +
                ", state=" + state +
                ", priority=" + priority +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(customerName);
        parcel.writeString(kind);
        if (num == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(num);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        parcel.writeString(ceng);
        parcel.writeString(sizeXiang);
        parcel.writeString(sizeBan);
        parcel.writeString(sizeYa);
        parcel.writeString(peizhi);
        if (isYinshua == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isYinshua);
        }
        parcel.writeString(yinshuaText);
        if (state == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(state);
        }
        if (priority == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(priority);
        }
    }
}
