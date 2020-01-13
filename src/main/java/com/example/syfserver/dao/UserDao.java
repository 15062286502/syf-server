package com.example.syfserver.dao;

import com.example.syfserver.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface UserDao {
    UserEntity userList(@Param("name") String name);

    int allUserList();

    List<UserEntity> userPageContext(int start, int pageSize);

    int queryNameCount(String queryName);

    List<UserEntity> queryNameResult(String queryName,int start, int pageSize);

    void excuteDelete(String userName);

    void addNewUser(@Param("addUserEntity")UserEntity addUserEntity);

    List<?> checkAddNewUser(@Param("addUserEntity")UserEntity addUserEntity);

    void editUser(@Param("editUserEntity")UserEntity editUserEntity);

    void uploadImage(@Param("url") String url,@Param("fileName") String fileName);

    int doQueryOrderNum();

    List<Map<String,String>> doQueryPercent();

    List<Map<String,Integer>> doQueryInDataByDay();

    List<Map<String,Integer>> doQueryOutDataByDay();

    List<Map<String,Integer>> doQueryOutDataByMonth();

    List<Map<String,Integer>> doQueryInDataByMonth();
}
