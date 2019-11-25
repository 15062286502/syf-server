package com.example.syfserver.entity;

import java.util.Date;

public class OrderEntity {
    private String id;
    private String identifier;
    private Date createTime;
    private String state;
    private String mealNumber;
    private String orderDesc;
    private String remarks;
    private String sumMoney;
    private String cutMoney;
    private String cupNumber;
    private String openId;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(String mealNumber) {
        this.mealNumber = mealNumber;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getCutMoney() {
        return cutMoney;
    }

    public void setCutMoney(String cutMoney) {
        this.cutMoney = cutMoney;
    }

    public String getCupNumber() {
        return cupNumber;
    }

    public void setCupNumber(String cupNumber) {
        this.cupNumber = cupNumber;
    }
}
