package com.example.syfserver.service;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.GoodsEntity;

import java.io.File;
import java.util.List;

public interface GoodsAdminService {
    int queryAllGoods(String queryName);

    List<?> queryGoodsPageContext(int start,int pageSize,String queryName);

    DtoEntity deleteGoods(List<GoodsEntity> goodsList);

    String getGoodImgUrl(File file, String fileName);

    void doAddGood(GoodsEntity goodsEntity);

    void doUpdateGood(GoodsEntity goodsEntity);

    List<?> getGoodByName(String goodName,String goodId);
}
