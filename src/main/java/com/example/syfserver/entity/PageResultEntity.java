package com.example.syfserver.entity;

import java.util.List;

public class PageResultEntity {
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    private int total;
    private List<?> data;
}
