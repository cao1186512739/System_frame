package com.opendev.annotation.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.opendev.exception.AccessLessException;
import com.opendev.annotation.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面抽象类
 * <p>
 * 将主业务需求功能抽取出来，如果其他操作，继承实现即可
 */
@Slf4j
@Aspect
@Component
public class AccessLimitAspect {

    private RateLimiter rateLimiter = RateLimiter.create(Double.MAX_VALUE);

    @Pointcut("execution(public * com.opendev..*(..))")
    public void web() {}


    @Around("web()")//环绕通知,环绕增强，相当于MethodInterceptor
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("拦截到了{}方法...", pjp.getSignature().getName());
        Signature signature =  pjp.getSignature();
         MethodSignature methodSignature=(MethodSignature)signature;
        //获取目标方法
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(AccessLimit.class)) {
            //获取目标方法的@AccessLimit
            AccessLimit lxRateLimit = targetMethod.getAnnotation(AccessLimit.class);
            rateLimiter.setRate(lxRateLimit.perSecond());
            if(!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())) {
                //抛出异常给全局抓取
                log.info("抛出异常给全局抓取");
                throw new AccessLessException();
            }
        }
        return  pjp.proceed();
    }
}
