package org.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 模拟 web层入参对象
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {

    /**
     * 姓名
     */
    private String value;

    /**
     * 工资
     */
    private BigDecimal salary;

    /**
     * 是否活跃
     */
    private Integer active;

    /**
     * 生日
     */
    private String birthday;



}