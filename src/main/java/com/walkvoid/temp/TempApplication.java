package com.walkvoid.temp;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/8/27
 * @desc
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TempApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(TempApplication.class).run(args);
    }
}
