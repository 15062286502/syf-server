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
    public int queryAllUsers(String queryName) {
        if(queryName==""){
            return userdao.allUserList();
        }else if(queryName!=null){
            return 0;
        }else{
            return 0;
        }
    }

    @Override
    public List<?> queryUserPageContext(int page,int pageSize,String queryName) {
        if(queryName==""){
            return userdao.userPageContext(page,pageSize);
        }else if(queryName!=null){
            return null;
        }else{
            return null;
        }
    }
}
