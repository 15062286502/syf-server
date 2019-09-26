package com.example.syfserver.service.serviceimpl;

import com.example.syfserver.dao.UserDao;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    @Override
    public UserEntity login(String name) {
        return userdao.userList(name);
    }

    @Override
    public List<UserEntity> queryAllUsers() {
        return userdao.allUserList();
    }
}
