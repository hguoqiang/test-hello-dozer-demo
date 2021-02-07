package org.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.money.MonetaryAmount;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-05 10:18
 **/
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentB {


    /**
     * 姓名
     */
    private String name;

    /**
     * 工资
     */
    private MonetaryAmount salary;


    private Integer count;

    /**
     * 生日
     */
    private String birthday;
}
