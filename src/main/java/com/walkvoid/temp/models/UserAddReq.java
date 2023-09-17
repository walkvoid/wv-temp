package com.walkvoid.temp.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    //private UserStatusEnum status;

    //public List<UserStatusEnum> statusList;

    public Map<String, UserStatusEnum> statusMap;

}
