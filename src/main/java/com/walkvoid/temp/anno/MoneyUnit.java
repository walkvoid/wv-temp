
package com.walkvoid.temp.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 金额单位注解
 */
@Target({FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface MoneyUnit {
        /**
         * 指定单位的类型,
         * @return
         */
        MoneyUnitType type() default MoneyUnitType.PER;

        /**
         * 提供给用户自定义的单位，如果此值大于0，则以divisor为准，type属性将被忽略
         * @return
         */
        int divisor() default 0;



    /**
     * 金额单位类型
     */
    enum MoneyUnitType {
        /**
         * 个位
         */
        PER(1),
        /**
         * 千
         */
        THOUSAND(3),

        /**
         * 万
         */
        TEN_THOUSAND(4),

        /**
         * 百万
         */
        MILLION(6),

        /**
         * 亿
         */
        A_HUNDRED_MILLION(8),
        ;

        private  int divisor;

        MoneyUnitType(int divisor) {
            this.divisor = divisor;
        }

        public int getDivisor() {
            return divisor;
        }
    }




}