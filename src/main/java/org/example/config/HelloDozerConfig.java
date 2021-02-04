package org.example.config;

import org.example.converters.BooleanIntegerConverter;
import org.example.converters.StringDateConverter;
import org.example.demo.UserDo;
import org.example.demo.UserDto;
import org.hello.dozer.spring.boot.autoconfigure.HelloDozerBeanMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-04 19:14
 **/
@Configuration
public class HelloDozerConfig {

    @Bean
    public HelloDozerBeanMapping userDto2UserDoFieldMapping() {
        HelloDozerBeanMapping smartBeanMapping = HelloDozerBeanMapping.create(UserDto.class, UserDo.class);

        smartBeanMapping.fields("value", "name");
        smartBeanMapping.fields("active", "act", new BooleanIntegerConverter());
        smartBeanMapping.fields("birthday", new StringDateConverter("yyyy-MM-dd"));

        return smartBeanMapping;
    }
}
