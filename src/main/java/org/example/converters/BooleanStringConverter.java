package org.example.converters;

import org.dozer.DozerConverter;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-04 15:16
 **/
//@Component
public class BooleanStringConverter extends DozerConverter<Boolean,String> {
    public BooleanStringConverter() {
        super(Boolean.class, String.class);
    }

    @Override
    public String convertTo(Boolean source, String destination) {
        System.out.println("公共的 BooleanStringConverter String convertTo  destination: " +destination);
        if(source){
            return "true";
        }
        return "false";
    }

    @Override
    public Boolean convertFrom(String source, Boolean destination) {
        System.out.println("convertFrom  destination: " +destination);
        if("true".equals(source)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}
