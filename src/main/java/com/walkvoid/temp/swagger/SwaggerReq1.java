package com.walkvoid.temp.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/9/20
 * @desc
 */
@Setter
@Getter
@ApiModel(value = "Swagger Req 1", description = "xxxxx")
public class SwaggerReq1 implements Serializable {
    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("订单id集合")
    private List<Long> orderIds;
}
