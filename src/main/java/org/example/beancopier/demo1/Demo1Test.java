package org.example.beancopier.demo1;

import org.springframework.cglib.beans.BeanCopier;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-07 10:28
 **/
public class Demo1Test {

    public static void main(String[] args) {
        normalCopyTest();
        normalCopyTest2();
    }

    //1. 属性名称、类型都相同:
    public static void normalCopyTest() {
        // create(Class source, Class target, boolean useConverter)
        final BeanCopier beanCopier = BeanCopier.create(User.class, UserDto.class, false);
        User user = new User();
        user.setAge(10);
        user.setName("zhangsan");
        UserDto userDto = new UserDto();
        beanCopier.copy(user, userDto, null);
        System.out.println(userDto);
        //结论：属性名称相同类型相同的属性拷贝OK。

    }
    //2. 属性名称相同、类型不同：
    public static void normalCopyTest2() {
        // create(Class source, Class target, boolean useConverter)
        final BeanCopier beanCopier = BeanCopier.create(User.class, UserWithDiffType.class, false);
        User user = new User();
        user.setAge(10);
        user.setName("zhangsan");
        UserWithDiffType userDto = new UserWithDiffType();
        beanCopier.copy(user, userDto, null);
        System.out.println(userDto);
        //结论 ：属性名称相同而类型不同的属性不会被拷贝。
        //注意：即使源类型是原始类型(int, short和char等)，目标类型是其包装类型(Integer, Short和Character等)，或反之：都 不会被拷贝。
    }


    // 结论 ：BeanCopier只拷贝名称和类型都相同的属性。


}
