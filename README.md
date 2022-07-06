# Spring Boot Cache ? 
<img src="https://github.com/rasitesdmr/SpringBoot-Caching/blob/master/images/caching.png" width="100%" height="50%"/>

## Maven Dependencies 
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
    <version>2.5.0</version>
</dependency>
```
## Prerequisites

* AOP
* H2 DB
* Caching

## Used Technologies

* Spring Boot 2.7.1
* Spring Boot Validation
* Lombok
* H2 DB 
* Spring Boot Caching
* Spring Boot AOP
* Spring Boot Jpa

# Cache (Önbellek ) ? 
+ Cache , uygulamamızın performansını artırmak için kullandığımız yöntemlerdir .
+ Örneğin , veri tabanına bir sorgu atıyoruz . Gelene verileri ilk olarak önbelleğe yazıyoruz . 
Daha sonra kullanıcıya gösteriyoruz . Eğer kullanıcı daha sonra aynı sorguyu çalıştırırsa veri tabanına
gitmek yerine verileri önbellekten alıyoruz . 

# Neden Cache Kullanmalıyız ? 
+ Önbelek kullanmanın birinci nedeni , veri erişimini daha hızlı hale getirmek .
+ Çok istenen kaynak birden çok kez istendiğinde geliştiricinin hızlı bir şekilde cevap verebilmesi 
için önbelleğe alması genellikle yararlıdır.
+ Bellekten veri erişimi , veri tabanından veri almaya kıyasla her zaman daha hızlıdır.
