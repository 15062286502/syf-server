package com.example.syfserver;

import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.ParentOrderEntity;
import com.example.syfserver.tools.EntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SyfServerApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        OrderEntity in = (OrderEntity)EntityFactory.getInstance().getEntity("IN");
        in.setCutMoney("11");
        in.setMealNumber("22");
        System.out.println(in.getMealNumber()+in.getCutMoney());
    }

}
