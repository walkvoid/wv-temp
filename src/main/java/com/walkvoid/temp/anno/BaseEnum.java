package com.walkvoid.temp.anno;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.walkvoid.temp.support.json.BaseEnumDeserializer;
import com.walkvoid.temp.support.json.BaseEnumSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangjunqing
 * @date 2023/9/17
 * @desc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = BaseEnumSerializer.class)
@JsonDeserialize(using = BaseEnumDeserializer.class)
public @interface BaseEnum {

    String databaseField() default "value";

    String frontendField() default "desc";
}