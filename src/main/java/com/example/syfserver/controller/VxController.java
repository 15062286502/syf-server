package com.example.syfserver.controller;

import com.example.syfserver.entity.GoodsEntity;
import com.example.syfserver.service.GoodsService;
import com.example.syfserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
}
