package com.walkvoid.temp.service.proxybean;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/29
 * @desc
 */
@Slf4j
@Aspect
@Component
public class PhotoServiceAspect {

    public PhotoServiceAspect(){
        System.out.println("::::::PhotoServiceAspect..init");
    }

    //@Pointcut("@annotation(com.walkvoid.temp.anno.EnableRemoteField)")
    @Pointcut("execution(* com.walkvoid.temp.service.proxybean.PhotoService.*(..))")
    public void cutPoint(){}

    @AfterReturning(pointcut = "cutPoint()",returning = "result")
    public Object afterReturning(JoinPoint joinPoint, Object result) {
        log.info("========== 进入切面==========");
        if (result == null) {
            return null;
        }
        //属性自动填充
        try {
            //remoteFieldAutoPopulate(joinPoint, result);
        }catch (Exception e) {
            log.info("切面逻辑执行发生异常", e);
        }
        log.info("==========  切面 结束==========");
        return result;
    }
}
