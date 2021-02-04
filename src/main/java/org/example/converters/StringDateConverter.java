package org.example.converters;

import org.dozer.DozerConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 自定义String类型日期和Date类型转换器
 * @author: huanggq
 * @create: 2021-02-04 16:20
 **/
public class StringDateConverter extends DozerConverter<String, Date> {


    /**
     * 日期格式 如 yyyy-MM-dd HH:mm:ss
     */
    private String parameter;


    @Override
    public String getParameter() {
        return parameter;
    }

    public StringDateConverter() {
        super(String.class, Date.class);
    }

    public StringDateConverter(String format) {
        super(String.class, Date.class);
        this.parameter = format;
    }

    @Override
    public Date convertTo(String source, Date destination) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(parameter);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertFrom(Date source, String destination) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(parameter);
        return dateFormat.format(source);
    }


    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
