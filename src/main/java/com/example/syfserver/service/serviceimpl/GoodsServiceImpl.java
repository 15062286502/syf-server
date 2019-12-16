package com.example.syfserver.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.syfserver.dao.GoodsDao;
import com.example.syfserver.entity.*;
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

        orderEntity.setMealNumber(Encrypter.genRandomNum());

        orderEntity.setCutMoney(good.get("cutMoney"));


        goodsDao.doGetOrder((OrderEntity) setOrderEntity(orderEntity, good));
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

            realOrderList.add(setAllOrderList(orderEntity));
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
        if (address != null && !address.isEmpty() && address.get(0) != null) {
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

    @Override
    public Map<String, String> doGetTakeOutOrder(Map<String, String> good) {
        Map<String, String> orderDetail = new HashMap<>();
        TakeOutOrderEntity takeOutOrderEntity = new TakeOutOrderEntity();
        takeOutOrderEntity.setAddress(good.get("address"));
        goodsDao.doGetTakeOutOrder((TakeOutOrderEntity) setOrderEntity(takeOutOrderEntity, good));
        orderDetail.put("identifier", takeOutOrderEntity.getIdentifier());
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderDetail.put("time", dateFormat1.format(takeOutOrderEntity.getCreateTime()));
        orderDetail.put("deliveryPerson", takeOutOrderEntity.getDeliveryPerson());
        return orderDetail;

    }

    @Override
    public List<Map<?, ?>> doGetAllTakeOutOrder(Map<String, String> token) {
        List<Map<?, ?>> realOrderList = new ArrayList<>();
        List<TakeOutOrderEntity> orderList = goodsDao.doGetAllTakeInOrder(token.get("openId"));
        for (TakeOutOrderEntity orderEntity :
                orderList) {
            realOrderList.add(setAllOrderList(orderEntity));
        }
        return realOrderList;
    }


    private static ParentOrderEntity setOrderEntity(ParentOrderEntity parentOrderEntity, Map<String, String> good) {
        StringBuffer sb = new StringBuffer();
        if (parentOrderEntity instanceof OrderEntity) {
            sb.append("syf_0");
        } else {
            sb.append("syf_1");

        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        sb.append(dateFormat.format(date));

        parentOrderEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        parentOrderEntity.setIdentifier(sb.toString());
        parentOrderEntity.setCreateTime(date);
        parentOrderEntity.setState("0");

        parentOrderEntity.setCupNumber(good.get("cupNumber"));
        parentOrderEntity.setRemarks(good.get("remarks"));
        parentOrderEntity.setSumMoney(good.get("sumMoney"));
        parentOrderEntity.setOrderDesc(good.get("cartList"));
        parentOrderEntity.setOpenId(good.get("openId"));
        return parentOrderEntity;
    }

    private Map<?, ?> setAllOrderList(ParentOrderEntity parentOrderEntity) {
        Map<String, Object> orderMap = new HashMap<>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderMap.put("orderEntity", parentOrderEntity);
        JSONArray orderJsonArray = JSONArray.parseArray(parentOrderEntity.getOrderDesc());
        orderMap.put("orderName", (orderJsonArray.getJSONObject(0)).get("name"));
        orderMap.put("orderImg", (orderJsonArray.getJSONObject(0)).get("img"));
        orderMap.put("orderTime", sd.format(parentOrderEntity.getCreateTime()));
        orderMap.put("orderDesc", (orderJsonArray.getJSONObject(0)).get("detail"));
        return orderMap;
    }

    @Override
    public void doUpdateRemark(Map<String, Object> remarks) {

        String orderId = (String) remarks.get("orderId");
        JSONObject remark = new JSONObject(remarks);
        goodsDao.doUpdateRemark(orderId,remark.toString());
    }
}
