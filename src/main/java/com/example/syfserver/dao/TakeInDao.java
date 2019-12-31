package com.example.syfserver.dao;

import com.example.syfserver.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TakeInDao {
    int allTakeInList();

    List<OrderEntity> takeInPageContext(int start, int pageSize);

    int queryTakeInNameCount(String queryName);

    List<OrderEntity> queryTakeInResult(String queryName, int start, int pageSize);

    void doDeleteTakeIn(String takeInId);
}
