package com.example.syfserver.service;

import com.example.syfserver.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface TakeInService {
    int queryAllTakeInOrder(String queryName);

    List<?> queryTakeInOrderPageContext(int start, int pageSize, String queryName);

    void deleteTakeIn(List<Map<String,Object>> orderEntities);
}
