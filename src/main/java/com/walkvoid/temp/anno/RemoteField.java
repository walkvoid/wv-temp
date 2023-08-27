package com.walkvoid.temp.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/14
 * @desc
 */
@Target({FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface RemoteField {

    QueryMethod queryMethod() default QueryMethod.UNKNOWN;

    String condition() default "";


    enum QueryMethod {
        UNKNOWN(null, "", null),

        CUST_NAME("fjalsdf", "queryName", new Class[]{Long.class}),
        ;

        public String clazz;

        public String method;

        public Class<?>[] paramTypes = {List.class, Long.class};

        QueryMethod(String clazz, String method, Class<?>[] paramTypes) {
            this.clazz = clazz;
            this.method = method;
            this.paramTypes = paramTypes;
        }

        public String getClazz() {
            return clazz;
        }

        public String getMethod() {
            return method;
        }

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }
    }


}
