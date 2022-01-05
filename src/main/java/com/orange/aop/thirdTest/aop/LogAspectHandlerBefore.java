package com.orange.aop.thirdTest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspectHandlerBefore {

    @Pointcut("execution(* com.orange.aop.thirdTest.controller..*.*(..))")
    private void pointCut(){}

    /**
     * @Before 注解指定的方法在切面切入目标方法之前执行，可以做一些 Log 处理
     * 也可以做一些信息的统计，比如获取用户的请求 URL 以及用户的 IP 地址等等
     * 这个在做个人站点的时候都能用得到，都是常用的方法
     * @param joinPoint
     * JointPoint 对象很有用，可以用它来获取一个签名
     * 利用签名可以获取请求的包名、方法名，包括参数（通过 joinPoint.getArgs() 获取）等。
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        log.info("====doBefore方法进入了====");
        //获取签名
        Signature signature = joinPoint.getSignature();
        //获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        //获取即将执行的方法名
        String name = signature.getName();
        log.info("即将执行方法为: {}，属于{}包",name,declaringTypeName);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        // 获取请求 ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }
}
