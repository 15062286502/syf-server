package com.example.syfserver.dao;

import com.example.syfserver.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    void doAddGood(@Param("goodsEntity") GoodsEntity goodsEntity);

    void doUpdateGood(@Param("goodsEntity") GoodsEntity goodsEntity);

    List<GoodsEntity> doGetGoodByNameAndId (@Param("goodName") String goodName,@Param("goodId") String goodId);
}
