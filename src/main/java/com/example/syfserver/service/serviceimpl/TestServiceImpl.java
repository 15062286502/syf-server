package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.TestDao;
import com.example.syfserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public void addList(String name,String password) {
        testDao.addAll(name, password);
    }
}
