package com.example.syfserver.controller;

import com.example.syfserver.entity.DtoEntity;
import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.PageResultEntity;
import com.example.syfserver.service.TakeInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/takeIn")
public class TakeInController {
    @Autowired
    private TakeInService takeInService;
    @RequestMapping("/getAllTakeInOrder")
    public DtoEntity getAllTakeInList (@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("loginName") String queryId){
        DtoEntity dto = new DtoEntity();
        PageResultEntity pgResult = new PageResultEntity();
        pgResult.setTotal(takeInService.queryAllTakeInOrder(queryId));
        pgResult.setData(takeInService.queryTakeInOrderPageContext(page * pageSize, pageSize, queryId));
        dto.setReturnObj(pgResult);
        return dto;
    }

    @RequestMapping("/takeInDelete")
    public DtoEntity userDelete(@RequestBody List<OrderEntity> orderEntity) {
        DtoEntity dto = new DtoEntity();
        takeInService.deleteTakeIn(orderEntity);
        dto.setIsLogin("true");
        return  dto;
    }

    @RequestMapping("/takeInComplete")
    public DtoEntity takeInComplete(@RequestBody List<OrderEntity> orderEntity) {
        DtoEntity dto = new DtoEntity();
        takeInService.completeTakeIn(orderEntity);
        dto.setIsLogin("true");
        return  dto;
    }

}
