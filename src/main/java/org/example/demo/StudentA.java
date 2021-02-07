package org.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-05 10:18
 **/
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentA {


    /**
     * 姓名
     */
    private String value;

    /**
     * 工资
     */
    private BigDecimal salary;


    private Integer count;

    /**
     * 生日
     */
    private Date birthday;


}
