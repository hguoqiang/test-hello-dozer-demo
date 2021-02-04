package org.example;

import org.example.demo.UserDo;
import org.example.demo.UserDto;
import org.hello.dozer.spring.boot.autoconfigure.HelloDozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-04 19:17
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DozerDemoApplication.class)
public class DozerDemoApplicationTest {


    @Autowired
    HelloDozerBeanMapper mapper;

    @Test
    public void testUserDto2UserDo(){
        UserDto dto = new UserDto("zhangsan",new BigDecimal(89.9),1,"1999-12-23");
        UserDo userDo = mapper.map(dto, UserDo.class);
        System.out.println(userDo);
    }
}