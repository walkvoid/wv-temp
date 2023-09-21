package com.walkvoid.temp.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/9/21
 * @desc
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig  {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.walkvoid.temp.swagger"))
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-swagger-demo")
                .description("this is a demo")
                .contact(new Contact("walkvoid", "https://www.baidu.com", "2916147177@qq.com"))
                .version("1.0.0")
                .build();
    }

}
