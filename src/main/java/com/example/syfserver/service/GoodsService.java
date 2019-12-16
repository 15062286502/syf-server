package com.example.syfserver.service;

import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<?> doGetAllList();

    Map<String,String> doGetOrder(Map<String ,String> good);

    List<Map<?,?>> doGetRTakeInOrder(Map<String ,String> token);

    void doSaveVxUser(Map<String,String> userInfo);

    void doSaveVxAddress(Map<String,String> address);

    List<?> doSelectVxAddress(Map<String,String> openId);

    void doDeleteVxAddress(Map<String,String> index);

    Map<String,String> doGetTakeOutOrder(Map<String ,String> good);

    List<Map<?,?>> doGetAllTakeOutOrder(Map<String ,String> token);

    void doUpdateRemark(Map<String,Object> index);

}
