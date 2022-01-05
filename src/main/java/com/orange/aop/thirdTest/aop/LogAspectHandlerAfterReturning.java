package com.orange.aop.thirdTest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspectHandlerAfterReturning {

    @Pointcut("execution(* com.orange.aop.thirdTest.controller..*.*(..))")
    private void pointCut(){}

    /**
     * @AfterReturning 注解和 @After 有些类似，区别在于 @AfterReturning 注解可以用来捕获切入方法执行完之后的返回值
     * 对返回值进行业务逻辑上的增强处理,在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * @param joinPoint joinPoint
     * @param result result
     *需要注意的是，在 @AfterReturning 注解 中，属性 returning 的值必须要和参数保持一致
     * 否则会检测不到。该方法中的第二个入参就是被切方法的返回值，在 doAfterReturning 方法中可以对返回值进行增强
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        log.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        // 实际项目中可以根据业务做具体的返回值增强
        log.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }
}
