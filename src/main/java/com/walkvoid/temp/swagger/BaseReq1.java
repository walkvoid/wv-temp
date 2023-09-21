package com.walkvoid.temp.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/9/20
 * @desc
 */

@Setter
@Getter
@ApiModel("Base Req 1")
public class BaseReq1 implements Serializable {

    @ApiModelProperty("sms code")
    private String smsCode;
}
