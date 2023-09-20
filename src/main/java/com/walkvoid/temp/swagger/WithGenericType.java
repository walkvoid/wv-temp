package com.walkvoid.temp.swagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @author jiangjunqing
 * @date 2023/9/20
 * @desc 标识带范型的参数
 */

@Target({FIELD, PARAMETER})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface WithGenericType {

    String attr();
}
