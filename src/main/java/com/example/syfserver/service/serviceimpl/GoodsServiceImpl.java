package com.example.syfserver.service.serviceimpl;

import com.alibaba.fastjson.JSONArray;
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
        orderEntity.setCreateTime(date);
        orderEntity.setMealNumber(Encrypter.genRandomNum());
        orderEntity.setState("0");

        orderEntity.setCupNumber(good.get("cupNumber"));
        orderEntity.setCutMoney(good.get("cutMoney"));
        orderEntity.setRemarks(good.get("remarks"));
        orderEntity.setSumMoney(good.get("sumMoney"));
        orderEntity.setOrderDesc(good.get("cartList"));
        orderEntity.setOpenId(good.get("openId"));

        goodsDao.doGetOrder(orderEntity);
        orderDetail.put("identifier",orderEntity.getIdentifier());
        SimpleDateFormat dateFormat1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderDetail.put("time",dateFormat1.format(orderEntity.getCreateTime()));
        orderDetail.put("mealNumber",orderEntity.getMealNumber());
        return orderDetail;
    }

    @Override
    public List<Map<?,?>> doGetRTakeInOrder(Map<String, String> token) {
        List<Map<?,?>> realOrderList = new ArrayList<>();
        List<OrderEntity> orderList = goodsDao.doGetTakeInOrder(token.get("openId"));
        for (OrderEntity orderEntity:
        orderList) {
            Map<String,Object> orderMap = new HashMap<>();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderMap.put("orderEntity",orderEntity);
            JSONArray orderJsonArray = JSONArray.parseArray(orderEntity.getOrderDesc());
            orderMap.put("orderName",(orderJsonArray.getJSONObject(0)).get("name"));
            orderMap.put("orderImg",(orderJsonArray.getJSONObject(0)).get("img"));
            orderMap.put("orderTime",sd.format(orderEntity.getCreateTime()));
            orderMap.put("orderDesc",(orderJsonArray.getJSONObject(0)).get("detail"));
            realOrderList.add(orderMap);
        }
        return realOrderList;
    }
}
