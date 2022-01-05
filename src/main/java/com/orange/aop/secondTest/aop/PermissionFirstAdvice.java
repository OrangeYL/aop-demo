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
 * 1.自定义一个注解PermissionsAnnotation
 * 2.创建一个切面类，切点设置为拦截所有标注PermissionsAnnotation的方法，截取到接口的参数，进行简单的权限校验
 * 3.将PermissionsAnnotation标注在controller的测试接口上
 * @Aspect注解用来描述一个切面类，定义切面类的时候需要打上这个注解。
 * @Component注解将该类交给Spring来管理。在这个类里实现第一步权限校验逻辑：
 * @Order注解管理切面类执行顺序，该注解后的数字越小，所在切面类越先执行。
 */
@Aspect
@Component
@Order(1)
public class PermissionFirstAdvice {

    //定义一个切点，所有被@PermissionAnnotation修饰的方法会被织入advice
    @Pointcut("@annotation(com.orange.aop.secondTest.annotation.PermissionAnnotation)")
    private void permissionCheck(){}

    /**
     * @Around可以自由选择增强动作与目标方法的执行顺序，也就是说可以在增强动作前后，甚至过程中执行目标方法。
     * 这个特性的实现在于，调用ProceedingJoinPoint参数的proceed()方法才会执行目标方法。
     * @Around可以改变执行目标方法的参数值，也可以改变执行目标方法之后的返回值。
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("第一个切面:"+System.currentTimeMillis());
        Object[] args = joinPoint.getArgs();
        Long id = ((JSONObject) args[0]).getLong("id");
        String name = ((JSONObject) args[0]).getString("name");
        System.out.println("id="+id);
        System.out.println("name="+name);

        //id小于0则抛出异常
        if(id<0){
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
