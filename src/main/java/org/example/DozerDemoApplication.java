package org.example;

import org.hello.dozer.spring.boot.autoconfigure.HelloDozerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果不想使用自动装配 可以在springboot启动时候排除掉，不扫描
 * @SpringBootApplication(exclude = {HelloDozerAutoConfiguration.class})
 */
@SpringBootApplication(exclude = {HelloDozerAutoConfiguration.class})
public class DozerDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DozerDemoApplication.class, args);
    }

}
