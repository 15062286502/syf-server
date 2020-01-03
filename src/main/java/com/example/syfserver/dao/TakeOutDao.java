package com.example.syfserver.dao;

import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TakeOutDao {
    int allTakeOutList();

    List<OrderEntity> takeOutPageContext(int start, int pageSize);

    int queryTakeOutNameCount(String queryName);

    List<OrderEntity> queryTakeOutResult(String queryName, int start, int pageSize);

    void doDeleteTakeOut(String takeOutId);

    void doCompleteTakeOut(String id);

    List<UserEntity> doGetAllDeliveryPerson();

    void doUpdateDelivery(@Param("outId") String outId, @Param("delivery") String delivery);
}
