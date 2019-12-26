package com.example.syfserver.dao;

import com.example.syfserver.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsAdminDao {
    int allGoodsList();

    List<GoodsEntity> goodsPageContext(int start, int pageSize);

    int queryGoodNameCount(String queryName);

    List<GoodsEntity> queryGoodNameResult(String queryName,int start, int pageSize);

    void doDeleteGood(String goodId);
}
