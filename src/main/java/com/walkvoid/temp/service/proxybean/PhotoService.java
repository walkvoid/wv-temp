package com.walkvoid.temp.service.proxybean;

import org.springframework.stereotype.Service;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/29
 * @desc
 */
@Service
public class PhotoService {

    public PhotoService() {
        System.out.println("::::::PhotoService...init::::::");
    }


    public String getName(String name){
        return "hello," + name;
    }
}
