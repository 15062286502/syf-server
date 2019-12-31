package com.example.syfserver.dao;

import com.example.syfserver.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
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
}
