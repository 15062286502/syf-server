package com.example.syfserver.controller;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.entity.TakeOutOrderEntity;
import com.example.syfserver.service.TakeOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/takeOut")
public class TakeOutController {
    @Autowired
    private TakeOutService takeOutService;
    @RequestMapping("/getAllTakeOutOrder")
    public DtoEntity getAllTakeInList (@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryId){
        DtoEntity dto = new DtoEntity();
        PageResultEntity pgResult = new PageResultEntity();
        pgResult.setTotal(takeOutService.queryAllTakeOutOrder(queryId));
        pgResult.setData(takeOutService.queryTakeOutOrderPageContext(page * pageSize, pageSize, queryId));
        dto.setReturnObj(pgResult);
        return dto;
    }

    @RequestMapping("/takeOutDelete")
    public DtoEntity takeOutDelete(@RequestBody List<TakeOutOrderEntity> orderEntity) {
        DtoEntity dto = new DtoEntity();
        takeOutService.deleteTakeOut(orderEntity);
        dto.setIsLogin("true");
        return  dto;
    }


    @RequestMapping("/takeOutComplete")
    public DtoEntity takeOutComplete(@RequestBody List<TakeOutOrderEntity> orderEntity) {
        DtoEntity dto = new DtoEntity();
        takeOutService.completeTakeOut(orderEntity);
        dto.setIsLogin("true");
        return  dto;
    }
}
