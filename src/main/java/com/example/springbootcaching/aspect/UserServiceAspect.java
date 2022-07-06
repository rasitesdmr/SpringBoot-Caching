package com.example.springbootcaching.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class UserServiceAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.springbootcaching.service.*.createUser(..))")
    public void createUserBefore(JoinPoint joinPoint) {
        logger.info("Kullanıcı Bilgilerinizi Giriniz ");
        logger.info("{}", joinPoint);

    }

    @After("execution(* com.example.springbootcaching.service.*.createUser(..))")
    public void createUserAfter(JoinPoint joinPoint) {
        logger.info("Kullanıcı Kaydedildi");
        logger.info("{}", joinPoint);
    }

    @Around("execution(* com.example.springbootcaching.service.*.createUser(..))")
    public Object createUserAround(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("createUser() yöntemi çağrılmadan önce");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("create() yöntemi çağrıldıktan sonra ");
        System.out.println("Değer : " + value);
        return value;
    }

    @Before("execution(* com.example.springbootcaching.service.*.getUsers(..))")
    public void getUsersBefore(JoinPoint joinPoint) {
        logger.info("Kullanıcı listesi oluşturuluyor ");
        logger.info("{}", joinPoint);

    }

    @After("execution(* com.example.springbootcaching.service.*.getUsers(..))")
    public void getUsersBeforeAfter(JoinPoint joinPoint) {
        logger.info("Kullanıcı listesi oluşturuldu ");
        logger.info("{}", joinPoint);
    }

    @Around("execution(* com.example.springbootcaching.service.*.getUsers(..))")
    public Object getUsersAround(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("getUsers() yöntemi çağrılmadan önce");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("getUsers() yöntemi çağrıldıktan sonra ");
        System.out.println("Değer : " + value);
        return value;
    }
}
