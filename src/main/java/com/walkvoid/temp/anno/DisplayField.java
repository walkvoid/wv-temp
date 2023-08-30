package com.walkvoid.temp.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
@Documented
@Target({FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface DisplayField {
}
