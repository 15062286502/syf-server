package com.example.syfserver.service;

import com.example.syfserver.entity.TakeOutOrderEntity;

import java.util.List;
import java.util.Map;

public interface TakeOutService {
    int queryAllTakeOutOrder(String queryName);

    List<?> queryTakeOutOrderPageContext(int start, int pageSize, String queryName);

    void deleteTakeOut(List<TakeOutOrderEntity> orderEntity);

    void completeTakeOut(List<TakeOutOrderEntity> orderEntity);

    List<Map<String,String>> getAllDeliveryPerson();

    void updateDelivery(Map<String,String> delivery);
}
