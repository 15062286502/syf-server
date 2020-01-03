package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.TakeOutDao;
import com.example.syfserver.entity.TakeOutOrderEntity;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.TakeOutService;
import com.example.syfserver.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TakeOutServiceImpl implements TakeOutService {
    @Autowired
    private TakeOutDao takeOutDao;

    @Override
    public int queryAllTakeOutOrder(String queryName) {
        if (queryName.equals("")) {
            return takeOutDao.allTakeOutList();
        } else if (queryName != null) {
            return takeOutDao.queryTakeOutNameCount(queryName);
        } else {
            return 0;
        }
    }

    @Override
    public List<?> queryTakeOutOrderPageContext(int start, int pageSize, String queryName) {
        if (StringUtil.isEmpty(queryName)) {

            return   takeOutDao.takeOutPageContext(start, pageSize);
        } else {
            return   takeOutDao.queryTakeOutResult(queryName, start, pageSize);
        }
    }

    @Override
    public void deleteTakeOut(List<TakeOutOrderEntity> orderEntity) {
        for (TakeOutOrderEntity takeOutMap : orderEntity){
            takeOutDao.doDeleteTakeOut(takeOutMap.getId());
        }
    }

    @Override
    public void completeTakeOut(List<TakeOutOrderEntity> orderEntity) {
        for (TakeOutOrderEntity takeInMap : orderEntity){
            takeOutDao.doCompleteTakeOut(takeInMap.getId());
        }
    }

    @Override
    public List<Map<String, String>> getAllDeliveryPerson() {
        List<Map<String, String>> finalList = new ArrayList<>();
        List<UserEntity> userEntities = takeOutDao.doGetAllDeliveryPerson();
        Iterator<UserEntity> iterator = userEntities.iterator();
        while (iterator.hasNext()){
            Map<String,String> userMap = new HashMap<>();
            UserEntity userEntity = iterator.next();
            userMap.put("label",userEntity.getRealName());
            userMap.put("value",String.valueOf(userEntity.getId()));
            finalList.add(userMap);
        }
        return finalList;
    }

    @Override
    public void updateDelivery(Map<String, String> delivery) {
        takeOutDao.doUpdateDelivery(delivery.get("outId"),delivery.get("region"));
    }
}
