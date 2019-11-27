package com.example.syfserver.dao;

import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;
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
}
