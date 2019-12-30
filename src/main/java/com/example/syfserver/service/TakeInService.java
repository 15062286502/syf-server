package com.example.syfserver.service;

import com.example.syfserver.entity.OrderEntity;

import java.util.List;

public interface TakeInService {
    int queryAllTakeInOrder(String queryName);

    List<?> queryTakeInOrderPageContext(int start, int pageSize, String queryName);
}
