package org.example.converters;

import org.dozer.DozerConverter;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-02 20:12
 **/
public class BooleanIntegerConverter extends DozerConverter<Boolean, Integer> {

    public BooleanIntegerConverter() {
        super(Boolean.class, Integer.class);
    }

    @Override
    public Integer convertTo(Boolean source, Integer destination) {

        if (source) {
            return 1;
        }
        return 0;
    }

    @Override
    public Boolean convertFrom(Integer source, Boolean destination) {
        System.out.println("BooleanIntegerConverter");
        if (1 == source) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}
