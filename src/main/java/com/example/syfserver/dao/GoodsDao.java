package com.example.syfserver.dao;

import com.example.syfserver.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface GoodsDao {
    List<GoodsEntity> doGetAllList();

    List<String> doGetAllKind();
}
