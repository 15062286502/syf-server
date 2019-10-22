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
    public void deleteSelectUser(String userName) {
        userdao.excuteDelete(userName);
    }

    @Override
    public UserEntity login(String name) {
        return userdao.userList(name);
    }

    @Override
    public int queryAllUsers(String queryName) {
        if (queryName.equals("")) {
            return userdao.allUserList();
        } else if (queryName != null) {
            return userdao.queryNameCount(queryName);
        } else {
            return 0;
        }
    }

    @Override
    public List<?> queryUserPageContext(int start, int pageSize, String queryName) {
        if (queryName.equals("")) {
            return userdao.userPageContext(start, pageSize);
        } else if (queryName != null) {
            return userdao.queryNameResult(queryName, start, pageSize);
        } else {
            return null;
        }
    }

    @Override
    public String addUser(UserEntity addUserEntity) {
        if ((userdao.checkAddNewUser(addUserEntity)).size() != 0) {
            return "error";
        } else {
            userdao.addNewUser(addUserEntity);
            return "success";
        }
    }

    @Override
    public void editUser(UserEntity editUserEntity) {

        userdao.editUser(editUserEntity);

    }
}
