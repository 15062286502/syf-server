package com.example.syfserver.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.syfserver.dao.GoodsDao;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.VxUserEntity;
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
        OrderEntity orderEntity = new OrderEntity();

        StringBuffer sb = new StringBuffer();
        sb.append("syf");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        sb.append(dateFormat.format(date));

        orderEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
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
        orderDetail.put("identifier", orderEntity.getIdentifier());
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderDetail.put("time", dateFormat1.format(orderEntity.getCreateTime()));
        orderDetail.put("mealNumber", orderEntity.getMealNumber());
        return orderDetail;
    }

    @Override
    public List<Map<?, ?>> doGetRTakeInOrder(Map<String, String> token) {
        List<Map<?, ?>> realOrderList = new ArrayList<>();
        List<OrderEntity> orderList = goodsDao.doGetTakeInOrder(token.get("openId"));
        for (OrderEntity orderEntity :
                orderList) {
            Map<String, Object> orderMap = new HashMap<>();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderMap.put("orderEntity", orderEntity);
            JSONArray orderJsonArray = JSONArray.parseArray(orderEntity.getOrderDesc());
            orderMap.put("orderName", (orderJsonArray.getJSONObject(0)).get("name"));
            orderMap.put("orderImg", (orderJsonArray.getJSONObject(0)).get("img"));
            orderMap.put("orderTime", sd.format(orderEntity.getCreateTime()));
            orderMap.put("orderDesc", (orderJsonArray.getJSONObject(0)).get("detail"));
            realOrderList.add(orderMap);
        }
        return realOrderList;
    }

    @Override
    public void doSaveVxUser(Map<String, String> userInfo) {
        List<VxUserEntity> vxUserList = goodsDao.doSelectVxUser(userInfo.get("openId"));
        if (vxUserList != null && vxUserList.isEmpty()) {
            VxUserEntity vxUserEntity = new VxUserEntity();
            vxUserEntity.setId(userInfo.get("openId"));
            vxUserEntity.setVxName(userInfo.get("vxName"));
            vxUserEntity.setVxImg(userInfo.get("vxImg"));
            vxUserEntity.setVxAddress(userInfo.get("vxAddress"));
            goodsDao.doSaveVxUser(vxUserEntity);
        }

    }

    @Override
    public void doSaveVxAddress(Map<String, String> address) {

        List<VxUserEntity> vxUserList = goodsDao.doSelectVxUser(address.get("openId"));

        JSONArray addressArray = JSONArray.parseArray(vxUserList.get(0).getVxAddress());
        if (addressArray != null) {
            addressArray.add(JSONObject.parseObject(address.get("address")));
        } else {
            addressArray = new JSONArray();
            addressArray.add(JSONObject.parseObject(address.get("address")));
        }


        goodsDao.doSaveVxAddress(addressArray.toString(), address.get("openId"));
    }

    @Override
    public List<?> doSelectVxAddress(Map<String, String> openId) {
        List<JSONArray> addressList = new ArrayList();
        List<?> address = goodsDao.doSelectVxAddress(openId.get("openId"));
        if (address != null && !address.isEmpty()) {
            addressList.add(JSONArray.parseArray(address.get(0).toString()));
        }
        return addressList;
    }

    @Override
    public void doDeleteVxAddress(Map<String, String> index) {
        List<?> address = goodsDao.doSelectVxAddress(index.get("openId"));
        if (address != null && !address.isEmpty()) {

            List<String> list = JSON.parseArray(address.get(0).toString(), String.class);

            if (list != null && !list.isEmpty()) {
                int index1 = Integer.parseInt(index.get("index"));

                list.remove(index1);

                JSONArray jsonArray = new JSONArray();


                for (int i = 0; i < list.size(); i++) {

                    JSONObject jsonObject = JSONObject.parseObject(list.get(i));
                    jsonArray.add(jsonObject);
                }

                goodsDao.doSaveVxAddress(jsonArray.toJSONString(), index.get("openId"));
            }

        }
    }


}
