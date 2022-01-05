package com.orange.aop.secondTest.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 创建一个切面类
 */
@Aspect
@Component
//数值越小，优先级越高
@Order(0)
public class PermissionSecondAdvice {

    //定义一个切点，所有被@PermissionAnnotation修饰的方法会被织入advice
    @Pointcut("@annotation(com.orange.aop.secondTest.annotation.PermissionAnnotation)")
    private void permissionCheck(){}

    @Around("permissionCheck()")
    public Object permissionCheckSecond(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("第二个切面:"+System.currentTimeMillis());
        Object[] args = joinPoint.getArgs();
        Long id = ((JSONObject) args[0]).getLong("id");
        String name = ((JSONObject) args[0]).getString("name");
        System.out.println("id="+id);
        System.out.println("name="+name);

        //不是admin则抛出异常
        if(!"admin".equals(name)){
            return JSON.parseObject("{\"message\":\"not admin\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
