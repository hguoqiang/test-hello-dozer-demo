package org.example.converters;

import org.dozer.DozerConverter;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import java.math.BigDecimal;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-02 14:30
 **/
@Component
public class MyMoneyConverter extends DozerConverter<BigDecimal, MonetaryAmount> {



    public MyMoneyConverter() {
        super(BigDecimal.class, MonetaryAmount.class);
    }

    @Override
    public MonetaryAmount convertTo(BigDecimal source, MonetaryAmount destination) {
        System.out.println("MonetaryAmount  convertTo");
        return Money.of(source, Monetary.getCurrency( "CNY" ));
    }

    @Override
    public BigDecimal convertFrom(MonetaryAmount monetaryAmount, BigDecimal destination) {
        NumberValue number = monetaryAmount.getNumber();
        System.out.println("BigDecimal  convertFrom");
        return  number.numberValue(BigDecimal.class);
    }
}
