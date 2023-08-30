package com.walkvoid.temp.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/8/27
 * @desc
 */
@Getter
@Setter
public class UserAddReq implements Serializable {

    private String name;

    private UserStatusEnum status;

}
