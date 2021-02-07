package org.example.beancopier.demo2;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-07 10:40
 **/
public class Demo2Test {
    public static void main(String[] args) {
        noConverter();
        useConverter();
    }
    //1、不使用Converter
    public static void noConverter() {
        Account po = new Account();
        po.setId(1);
        po.setCreateTime(new Date());
        po.setBalance(BigDecimal.valueOf(4000L));
        BeanCopier copier = BeanCopier.create(Account.class, AccountDto.class, false);
        AccountDto dto = new AccountDto();
        copier.copy(po, dto, null);
        //结论 ： 类型不同，未拷贝
        System.out.println(dto);
    }

    //2、使用Converter
    //基于目标对象的属性出发，如果源对象有相同名称的属性，则调一次convert方法：
    public static void useConverter() {
        Account po = new Account();
        po.setId(1);
        po.setCreateTime(new Date());
        po.setBalance(BigDecimal.valueOf(4000L));
        BeanCopier copier = BeanCopier.create(Account.class, AccountDto.class, true);
        AccountDto dto = new AccountDto();
        AccountConverter converter = new AccountConverter();
        copier.copy(po, dto, converter);
        System.out.println(dto);

        //结论 ： 使用转换器可以实现类型不同属性值拷贝
        //注：一旦使用Converter，BeanCopier只使用Converter定义的规则去拷贝属性，所以在convert方法中要考虑所有的属性。
    }

    static class AccountConverter implements Converter {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Object source, Class target, Object context) {
            if (source instanceof Integer) {
                return (Integer) source;
            } else if (source instanceof Date) {
                Date date = (Date) source;
                return sdf.format(date);
            } else if (source instanceof BigDecimal) {
                BigDecimal bd = (BigDecimal) source;
                return bd.toPlainString();
            }
            return null;
        }
    }

}
