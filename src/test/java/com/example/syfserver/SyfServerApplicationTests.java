package com.example.syfserver;

import com.example.syfserver.entity.OrderEntity;
import com.example.syfserver.entity.ParentOrderEntity;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.tools.EntityFactory;
import com.example.syfserver.tools.RedisKeyUtil;
import com.example.syfserver.tools.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SyfServerApplicationTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;
    @Test
    public void contextLoads() throws Exception {
        OrderEntity in = (OrderEntity)EntityFactory.getInstance().getEntity("IN");
        in.setCutMoney("11");
        in.setMealNumber("22");
        System.out.println(in.getMealNumber()+in.getCutMoney());
    }

    @Test
    public void testRedisConnection()  {
        redisTemplate.opsForValue().set("test", 1);
        int test = (int) redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

    @Test
    public void testRedis()  {
        UserEntity userVo = new UserEntity();
        userVo.setAddress("上海");
        userVo.setName("测试dfas");
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        redisService.expireKey("name",120, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey("name",userVo.getName());
        UserEntity vo = (UserEntity) operations.get(key);
        System.out.println(vo);
    }

    @Test
    public void testValueOption( )throws  Exception{
        UserEntity userVo = new UserEntity();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        valueOperations.set("test",userVo);


    }
}
