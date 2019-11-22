package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.GoodsDao;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<?> doGetAllList() {
        List<Map> goodsList = new ArrayList();

        List<String> kindList = goodsDao.doGetAllKind();
        List<GoodsEntity> goodsEntities = goodsDao.doGetAllList();

        for (int i = 0; i < kindList.size(); i++) {
            Map<String, Object> foodMap = new HashMap<>();
            foodMap.put("kind", kindList.get(i));

            List<GoodsEntity> eachGoods = new ArrayList<>();
            for (GoodsEntity goodsEntity :
                    goodsEntities) {
                if (goodsEntity.getKind().equals(kindList.get(i))) {
                    eachGoods.add(goodsEntity);
                }

            }

            foodMap.put("foods", eachGoods);
            goodsList.add(foodMap);
        }


        return goodsList;
    }
}
