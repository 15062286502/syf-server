package com.example.syfserver.entity;


public class OrderEntity extends ParentOrderEntity{

    private String mealNumber;
    private String cutMoney;


    public String getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(String mealNumber) {
        this.mealNumber = mealNumber;
    }


    public String getCutMoney() {
        return cutMoney;
    }

    public void setCutMoney(String cutMoney) {
        this.cutMoney = cutMoney;
    }


}
