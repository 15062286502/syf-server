package com.example.syfserver.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.syfserver.entity.UserEntity;
import com.example.syfserver.service.TokenService;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String generateToken(String userAgent, String username) {
        StringBuilder token = new StringBuilder();
        //加token:
        token.append("token:");
        //加设备
        UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
        if (userAgent1.getOperatingSystem().isMobileDevice()){
            token.append("MOBILE-");
        } else {
            token.append("PC-");
        }
        //加加密的用户名
        token.append(username + "-");
        //加时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        //加六位随机数111111-999999
        token.append(new Random().nextInt((999999 - 111111 + 1)) + 111111);
        System.out.println("token=>" + token.toString());
        return token.toString();

    }

    @Override
    public void saveToken(String token, UserEntity user) {
        //如果是PC，那么token保存两个小时；如果是MOBILE
        if (token.startsWith("token:PC")) {
        } else {
        }

    }
}
