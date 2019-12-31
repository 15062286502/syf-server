package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.TakeInDao;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.service.TakeInService;
import com.example.syfserver.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TakeInServiceImpl implements TakeInService {
    @Autowired
    private TakeInDao takeInDao;

    @Override
    public int queryAllTakeInOrder(String queryName) {
        if (queryName.equals("")) {
            return takeInDao.allTakeInList();
        } else if (queryName != null) {
            return takeInDao.queryTakeInNameCount(queryName);
        } else {
            return 0;
        }
    }

    @Override
    public List<?> queryTakeInOrderPageContext(int start, int pageSize, String queryName) {
        List<Map<String,Object>> finalList = new ArrayList<>();
        List<OrderEntity> takeInList = new ArrayList<>();

        if (StringUtil.isEmpty(queryName)) {

            takeInList =  takeInDao.takeInPageContext(start, pageSize);
        } else {
            takeInList =  takeInDao.queryTakeInResult(queryName, start, pageSize);
        }
        SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OrderEntity orderEntity:
                takeInList) {
            Map<String,Object> timeMap = new HashMap<>();
            timeMap.put("time",sd.format(orderEntity.getCreateTime()));
            timeMap.put("takeIn",orderEntity);
            finalList.add(timeMap);
        }
        return finalList;
    }

    @Override
    public void deleteTakeIn(List<Map<String,Object>> orderEntities) {
        for (Map<String,Object> takeInMap : orderEntities){
            takeInDao.doDeleteTakeIn(((LinkedHashMap<String,Object>)takeInMap.get("takeIn")).get("id").toString());
        }
    }
}
