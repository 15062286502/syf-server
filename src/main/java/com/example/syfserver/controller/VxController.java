package com.example.syfserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.service.GoodsService;
import com.example.syfserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/vx")
public class VxController {
    @Autowired
    private TestService testService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/test")
    public void test(@RequestParam("username") String name,@RequestParam("password") String password){
        testService.addList(name,password);
    }
    @RequestMapping("/getAllList")
    public List<?> getFoodList(){
        return goodsService.doGetAllList();
    }

    @RequestMapping("/getOrder")
    public Map<String,String> getOrderList(@RequestParam Map<String ,String> good){

        return goodsService.doGetOrder(good);
    }

    @RequestMapping("/takeInOrder")
    public List<Map<?,?>> getTakeInOrderList(@RequestParam Map<String ,String> token){

        return goodsService.doGetRTakeInOrder(token);
    }

    @RequestMapping("/saveUser")
    public void saveUserInfo(@RequestParam Map<String ,String> userInfo){
                goodsService.doSaveVxUser(userInfo);
    }

}
