package com.walkvoid.temp.swagger;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

/**
 * @author jiangjunqing
 * @date 2023/9/20
 * @desc
 */
public class MySwaggerPlugin implements ParameterBuilderPlugin {

    @Override
    public void apply(ParameterContext parameterContext) {

    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return false;
    }
}
