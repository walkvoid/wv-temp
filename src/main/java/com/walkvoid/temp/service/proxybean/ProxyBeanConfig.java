package com.walkvoid.temp.service.proxybean;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/29
 * @desc
 */
//@Configuration
public class ProxyBeanConfig {

    @Bean
    public PhotoService photoService() {
        return new PhotoService();
    }

    @Bean
    public PhotoServiceAspect photoServiceAspect() {
        return new PhotoServiceAspect();
    }


}
