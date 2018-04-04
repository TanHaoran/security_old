package com.jerry.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 11:20
 * Description: 几个拦截的执行顺序Filter->Interceptor->ControllerAdvice->Aspect->Controller,捕获异常的顺序正好相反
 */
//@Aspect
//@Component
public class TimeAspect {

    // 时间：@Around以包围的方式起作用
    // 地点：只针对UserController中的所有方法起作用
    @Around("execution(* com.jerry.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = new Date().getTime();

        Object proceed = joinPoint.proceed();

        System.out.println("time aspect 耗时: " + (new Date().getTime() - start));

        System.out.println("time aspect end");

        return proceed;
    }

}
