package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.GoodsDao;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.service.GoodsService;
import com.example.syfserver.tools.Encrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public Map<String, String> doGetOrder(Map<String, String> good) {
        Map<String, String> orderDetail = new HashMap<>();
        OrderEntity orderEntity =new OrderEntity();

        StringBuffer sb= new StringBuffer();
        sb.append("syf");
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        sb.append(dateFormat.format(date));

        orderEntity.setId(UUID.randomUUID().toString().replaceAll("-",""));
        orderEntity.setIdentifier(sb.toString());
        orderEntity.setTime(date);
        orderEntity.setMealNumber(Encrypter.genRandomNum());
        orderEntity.setState("0");

        orderEntity.setCupNumber(good.get("cupNumber"));
        orderEntity.setCutMoney(good.get("cutMoney"));
        orderEntity.setRemarks(good.get("remarks"));
        orderEntity.setSumMoney(good.get("sumMoney"));
        orderEntity.setDesc(good.get("cartList"));

        goodsDao.doGetOrder(orderEntity);
        orderDetail.put("identifier",orderEntity.getIdentifier());
        SimpleDateFormat dateFormat1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderDetail.put("time",dateFormat1.format(orderEntity.getTime()));
        orderDetail.put("mealNumber",orderEntity.getMealNumber());
        return orderDetail;
    }
}
