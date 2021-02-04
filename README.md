# test-hello-dozer-demo
基于 hello-dozer-spring-boot-starter 写的demo

### 1. pom.xml 中 引入
```xml
<dependency>
        <groupId>org.hello.dozer</groupId>
        <artifactId>hello-dozer-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. 自定义转换器

转换器从功能上大致分成两种（实际上只有一种，都要继承DozerConverter），

**第一种可以理解成是全局的转换器**，主要作用是转换所有类中的指定字段，这类加上@Component，工程启动时，spring自动加载此bean

```java
@Component
public class MyMoneyConverter extends DozerConverter<BigDecimal, MonetaryAmount> {
    
}
```

**第二种可以理解成是指定bean之间的字段转换**，主要就是完成 A.java和 B.java 两个类之间的某些字段转换，

这类转换器也同样继承DozerConverter

```java
public class StringDateConverter extends DozerConverter<String, Date> {
    
}
```

然后需要配置 HelloDozerBeanMapping 对象，通过

```java
@Configuration
public class HelloDozerConfig {
    @Bean
    public HelloDozerBeanMapping userDto2UserDoFieldMapping() {
        
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
```



### 3. 测试

注入 HelloDozerBeanMapper  ，调用map 方法

```java
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
```