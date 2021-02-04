package org.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.Date;

/**
 * 模拟数据库层对象
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 工资
     */
    private MonetaryAmount salary;

    /**
     * 是否活跃
     */
    private Boolean act;

    /**
     * 生日
     */
    private Date birthday;
}