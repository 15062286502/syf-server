package com.example.syfserver.service;

import com.example.syfserver.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<?> doGetAllList();

    Map<String,String> doGetOrder(Map<String ,String> good);

}
