package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.MenuDao;
import com.example.syfserver.dao.UserDao;
import com.example.syfserver.entity.MenuEntity;
import com.example.syfserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Override
    public ArrayList<MenuEntity> getAllMenus() {
        return menuDao.menuList();
    }
}
