package com.walkvoid.temp.anno;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.walkvoid.temp.support.json.JacksonMoneyFormatDeserializer;
import com.walkvoid.temp.support.json.JacksonMoneyFormatSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/12
 * @desc 金额格式注解
 * {@link }
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = JacksonMoneyFormatSerializer.class)
@JsonDeserialize(using = JacksonMoneyFormatDeserializer.class)
public @interface JacksonMoneyFormat {

    /**
     * 金额的单位，个，万，亿，或者自定义
     * @return
     */
    MoneyUnit unit() default @MoneyUnit(type = MoneyUnit.MoneyUnitType.PER);

    /**
     * 小数的位数，默认是2
     * @return
     */
    int scale() default 2;

    /**
     * 是否带千分位分隔符，默认为false，如果是true，则整数部分每三位使用逗号分隔开
     */
    boolean withThousandSeparator() default false;

    /**
     * 是否移除小数点后无效的0，默认不移除
     * @return
     */
    boolean stripTrailingZeros() default false;

    /**
     * 前面带货币符号，默认不带
     * @return
     */
    String withSymbol() default "";

}
