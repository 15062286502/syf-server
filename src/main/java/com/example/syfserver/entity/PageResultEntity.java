package com.example.syfserver.entity;

import java.util.List;

public class PageResultEntity {
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String toString(){
        return "PageResult [data=" + data + ", totalCount=" + total + "]";
    }

    private Integer total;
    private List<?> data;
}
