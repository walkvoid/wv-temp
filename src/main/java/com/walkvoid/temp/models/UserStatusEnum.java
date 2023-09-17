package com.walkvoid.temp.models;

import com.walkvoid.temp.anno.BaseEnum;
import com.walkvoid.temp.anno.DatabaseField;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
@BaseEnum
public enum UserStatusEnum {
    INIT("init", "初始化"),
    ACTIVE("active", "已激活"),
    LOCKED("locked", "被锁定"),
    CANCEL("cancel", "已注销"),
    ;

    @DatabaseField
    private String value;

    private String desc;

    UserStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public String getValue() {
        return this.value;
    }
}
