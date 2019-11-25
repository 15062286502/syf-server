package com.example.syfserver.service;

import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<?> doGetAllList();

    Map<String,String> doGetOrder(Map<String ,String> good);

    List<OrderEntity> doGetRTakeInOrder(Map<String ,String> token);
}
