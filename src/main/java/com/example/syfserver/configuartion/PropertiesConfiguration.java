package com.example.syfserver.configuartion;

import com.example.syfserver.entity.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration

@PropertySource("classpath:address.properties")
public class PropertiesConfiguration {
    @Value("${USER_IMAGE_URL}")
    public String USER_IMAGE_URL;
    @Value("${USER_IMAGE_ADDRESS}")
    public String USER_IMAGE_ADDRESS;
    @Value("${GOODS_IMAGE_URL}")
    public String GOODS_IMAGE_URL;
    @Value("${GOODS_IMAGE_ADDRESS}")
    public String GOODS_IMAGE_ADDRESS;

    @Bean
    public Address getAddress() {
        Address address = new Address();
        address.USER_IMAGE_URL = USER_IMAGE_URL;
        address.GOODS_IMAGE_ADDRESS = GOODS_IMAGE_ADDRESS;
        address.USER_IMAGE_ADDRESS = USER_IMAGE_ADDRESS;
        address.GOODS_IMAGE_URL = GOODS_IMAGE_URL;
        return address;
    }


}
