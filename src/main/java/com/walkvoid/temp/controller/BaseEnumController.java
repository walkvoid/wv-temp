package com.walkvoid.temp.controller;

import com.walkvoid.temp.models.UserAddReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc 基础枚举转换测试controller
 */
@RestController
public class BaseEnumController {

    @PostMapping("/base-enum/add-user")
    public UserAddReq addUser(@RequestBody UserAddReq userAddReq) {
        return userAddReq;
    }
}
