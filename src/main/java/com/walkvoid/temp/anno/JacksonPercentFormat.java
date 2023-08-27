package com.walkvoid.temp.anno;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.walkvoid.temp.support.json.JacksonPercentFormatDeserializer;
import com.walkvoid.temp.support.json.JacksonPercentFormatSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/12
 * @desc 百分比格式
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = JacksonPercentFormatSerializer.class)
@JsonDeserialize(using = JacksonPercentFormatDeserializer.class)
public @interface JacksonPercentFormat {

    /**
     * 百分比小数的位数，默认是2
     */
    int scale() default 2;

    /**
     * 是否带百分比符号(%), 默认不带
     * @return
     */
    boolean withSymbol() default false;



}
