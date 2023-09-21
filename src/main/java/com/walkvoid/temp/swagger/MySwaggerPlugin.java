package com.walkvoid.temp.swagger;

import com.fasterxml.classmate.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

/**
 * @author jiangjunqing
 * @date 2023/9/20
 * @desc
 */
@Slf4j
@Configuration
public class MySwaggerPlugin implements ModelPropertyBuilderPlugin {


    @Autowired
    private TypeResolver typeResolver;

    @Override
    public void apply(ModelPropertyContext context) {
        context.getSpecificationBuilder().description("xixihaha")
                        .build();
        //context.getOwner().getModelSpecificationBuilder().("wufawuting").build();




        log.info("parameterContext");

    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
