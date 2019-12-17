package com.example.syfserver.constant;

public enum OrderEnum {
    IN("com.example.syfserver.entity.OrderEntity"),
    OUT("com.example.syfserver.entity.TakeOutOrderEntity");

    private String className;

    OrderEnum(String className){
        this.setClassName(className);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
