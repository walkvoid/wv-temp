package com.walkvoid.temp.service;

import com.walkvoid.temp.TempApplication;
import com.walkvoid.temp.service.proxybean.PhotoService;
import com.wvframework.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/8/27
 * @desc
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TempApplication.class)
public class UserServiceTest {

    @Autowired
    private PhotoService photoService;

    @Test
    public void proxyTest() {
        log.info("::::::::::{}",photoService.getClass().getName());
    }

    @Test
    public void springUtilsTest() {

        log.info("::::::::::currentIsSpringEnvironment:{}", SpringUtils.currentIsSpringEnvironment());
        log.info("::::::::::PhotoService:{}", SpringUtils.getBean(PhotoService.class));
    }


}
