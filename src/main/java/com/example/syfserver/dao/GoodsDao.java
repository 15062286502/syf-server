package com.example.syfserver.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.TakeOutOrderEntity;
import com.example.syfserver.entity.VxUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface GoodsDao {
    List<GoodsEntity> doGetAllList();

    List<String> doGetAllKind();

    void doGetOrder(@Param("orderEntity")OrderEntity orderEntity);

    List<OrderEntity> doGetTakeInOrder(@Param("openId") String openId);

    void doSaveVxUser(@Param("vxUserEntity") VxUserEntity vxUserEntity);

    List<VxUserEntity> doSelectVxUser(@Param("id")String vxId);

    void doSaveVxAddress(@Param("address") String address,@Param("openId") String openId);

    List<?> doSelectVxAddress(@Param("id")String vxId);

    void  doGetTakeOutOrder(@Param("takeOutOrderEntity") TakeOutOrderEntity takeOutOrderEntity);

    List<TakeOutOrderEntity>  doGetAllTakeInOrder (@Param("openId") String openId);

    void doUpdateRemark(@Param("orderId")String orderId, @Param("remark")String remark);
}
