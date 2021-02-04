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
        //配置要转换的bean, UserDto 和 UserDo 之间的字段转换
        HelloDozerBeanMapping smartBeanMapping = HelloDozerBeanMapping.create(UserDto.class, UserDo.class);

        //指定要转换的字段
        // value 和 name 都是String类型 不需要转换器
        smartBeanMapping.fields("value", "name");
        // active 和 act  是不同类型 ，需要转换器
        smartBeanMapping.fields("active", "act", new BooleanIntegerConverter());
        //birthday 在两个类中是 同名的 但是类型不一样，参数是指定转换格式
        smartBeanMapping.fields("birthday", new StringDateConverter("yyyy-MM-dd"));

        return smartBeanMapping;
    }
}
