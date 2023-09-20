package com.walkvoid.temp.swagger;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/9/20
 * @desc
 */
@Getter
@Setter
public class BaseReq<T> {

    private String name;

    private T param;
}
