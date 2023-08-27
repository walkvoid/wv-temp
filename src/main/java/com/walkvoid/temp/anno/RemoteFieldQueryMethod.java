package com.walkvoid.temp.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/14
 * @desc
 */
@Target({FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface RemoteFieldQueryMethod {

    String clazz();

    String method();

    Class<?>[] paramTypes() default {};

    String condition() default "";


}
