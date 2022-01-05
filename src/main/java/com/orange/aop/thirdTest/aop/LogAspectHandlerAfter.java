package com.orange.aop.thirdTest.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspectHandlerAfter {

    @Pointcut("execution(* com.orange.aop.thirdTest.controller..*.*(..))")
    private void pointCut(){}

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {

        log.info("==== doAfter 方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}已经执行完", method);
    }
}
