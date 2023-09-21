package com.walkvoid.temp.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangjunqing
 * @date 2023/9/20
 * @desc swagger插件开发测试
 * https://blog.csdn.net/universsky2015/article/details/119949383
 */
@Api(tags = "swagger测试")
@RestController
public class SwaggerController {

    @ApiOperation("apiModel测试罢了")
    @RequestMapping(value = "/apiModel", method = RequestMethod.POST )
    public ResponseEntity<SwaggerResp1> apiModelTest(@RequestBody SwaggerReq1 req1) {
        return ResponseEntity.ok(new SwaggerResp1());
    }



}
