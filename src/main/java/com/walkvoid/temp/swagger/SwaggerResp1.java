package com.walkvoid.temp.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/9/20
 * @desc
 */
@Setter
@Getter
@ApiModel("Swagger Resp 1")
public class SwaggerResp1 implements Serializable {

    @ApiModelProperty("响应码")
    private String code;

    @ApiModelProperty("订单金额(元)")
    private BigDecimal amount;
}
