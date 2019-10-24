package com.example.syfserver.tools;

import com.example.syfserver.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class RemovePassword {
    public static List<?> removePassword(List<UserEntity> user){
        List<UserEntity> userList = new ArrayList<>();
        for (UserEntity userEntity: user
             ) {
            userEntity.setPassword("");
            userList.add(userEntity);
        }
        return userList;
    }
}
