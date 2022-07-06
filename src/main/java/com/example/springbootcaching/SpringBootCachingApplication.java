package com.example.springbootcaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class SpringBootCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCachingApplication.class, args);
    }

}
