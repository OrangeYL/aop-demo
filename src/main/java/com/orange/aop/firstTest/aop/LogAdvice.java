package com.orange.aop.firstTest.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 创建一个切面类
 * 所有的get请求被调用前在控制台输出一句"get请求的advice触发了"。
 * @Aspect注解用来描述一个切面类，定义切面类的时候需要打上这个注解。
 * @Component注解将该类交给Spring来管理。在这个类里实现advice：
 */
@Aspect
@Component
public class LogAdvice {

    //定义一个切点，所有被GetMapping注解修饰的方法都会被织入advice
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointCut(){}

    //@Before表示logAdvice在目标方法执行之前执行
    @Before("logAdvicePointCut()")
    public void logAdvice(){
        //可以是其他操作，这里只是实例
        System.out.println("Get请求的Advice被触发了");
    }
}
