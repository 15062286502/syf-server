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

        if (StringUtil.isEmpty(queryName)) {

            return   takeInDao.takeInPageContext(start, pageSize);
        } else {
            return   takeInDao.queryTakeInResult(queryName, start, pageSize);
        }

    }

    @Override
    public void deleteTakeIn(List<OrderEntity> orderEntities) {
        for (OrderEntity takeInMap : orderEntities){
            takeInDao.doDeleteTakeIn(takeInMap.getId());
        }
    }
}
