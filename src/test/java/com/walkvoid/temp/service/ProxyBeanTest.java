package com.walkvoid.temp.service;

import com.walkvoid.temp.service.proxybean.PhotoService;
import com.walkvoid.temp.service.proxybean.ProxyBeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/29
 * @desc
 */
@Slf4j
public class ProxyBeanTest {

    private ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(ProxyBeanConfig.class);
    }

    @Test
    public void getPhotoServiceTest() {
        PhotoService bean = applicationContext.getBean(PhotoService.class);
        String walkvoid = bean.getName("walkvoid");
        log.info("PhotoService:{}", bean.getClass().getName());
    }
}
