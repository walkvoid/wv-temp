package com.walkvoid.temp.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @author walkvoid
 * @version v1.0.0
 * @date 2023/7/14
 * @desc
 */
@Target({METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface EnableRemoteField {

    String[] attrs() default {};
}
