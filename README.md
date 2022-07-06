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

* Caching

## Used Technologies


* Spring Boot 2.7.1
* Spring Boot Validation
* Lombok
* H2 DB 
* Spring Boot MapStruct
* Spring Boot Caching
* Spring Boot AOP
* Spring Boot Jpa

### H2 DB Access
```xml
http://localhost:8080/h2-console
```

## Cache (Önbellek ) ? 
+ Cache , uygulamamızın performansını artırmak için kullandığımız yöntemlerdir .
+ Örneğin , veri tabanına bir sorgu atıyoruz . Gelene verileri ilk olarak önbelleğe yazıyoruz . 
Daha sonra kullanıcıya gösteriyoruz . Eğer kullanıcı daha sonra aynı sorguyu çalıştırırsa veri tabanına
gitmek yerine verileri önbellekten alıyoruz . 

## Neden Cache Kullanmalıyız ? 
+ Önbelek kullanmanın birinci nedeni , veri erişimini daha hızlı hale getirmek .
+ Çok istenen kaynak birden çok kez istendiğinde geliştiricinin hızlı bir şekilde cevap verebilmesi 
için önbelleğe alması genellikle yararlıdır.
+ Bellekten veri erişimi , veri tabanından veri almaya kıyasla her zaman daha hızlıdır.

## Nasıl Aktif Edilir ? 
+ Bu özelliği aktif etmek için Main metoduna @EnableCaching anotasyonu eklenir .

```xml

@SpringBootApplication
@EnableCaching
public class SpringBootCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCachingApplication.class, args);
    }

}
```
### Bilmemiz Gereken Bazı Anotasyonlar 
* @Cacheable("cacheName")
* @CacheEvict("cacheName")
* @CachePut("cacheName")
* @Caching()

## @Cacheable() ? 
+ Daha önce ilgili key değerine (cacheName) değer yazılmamışsa önce belleğe yazılır . Daha sonra 
kullanıcıya ilgili değerleri döner. Eğer önbellekle ilgili bilgi varsa önbellekten çağrılacaktır .

```xml
    @GetMapping("/list")
    @Cacheable("cacheUsers")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> userDTOList = userMapper.userToUserDTOList(userService.getUsers());
        return ResponseEntity.ok(userDTOList);
    }
```
+ Yukardaki örnekte bir kullanıcı listemizin olduğunu varsayalım . Bu listeyi ilk çektiğimizde veri tabanında 
listeler gelecektir . Daha sonra aynı listeyi tekrar çektiğimizde bu sefer önbellekten gelecektir . Ve daha kısa sürede gelecektir .

## @CacheEvict() ? 
+ Önbellekte ilgili key ile yazılmış değer varsa onu silecektir . 
+ Örneğin @Cacheable("users") adında bir metot çağırdık ve önbelleğe kullanıcı listesi yazdırdı . Daha sonra
@CacheEvict("users") ifadesi olarak metodu çağırırsak , bellekteki kullanıcı listesini silercektir.

```xml
    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "cacheUsers",key = "#id")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
```
+ Yukardaki örnekte key vererek kısıtlama yaptık verdiğimiz id deki kullanıcıları bellekten silecektir. 

## @CachePut() ? 
+ Eğer bir metodun her defasında çalışmasını istiyorsak kullanırız . Bu sayede önbellekte her zaman en güncel veriler olacaktır .

## @Caching() ? 
+ Aynı yöntemde aynı anda hem @CachePut hem de @CacheEvict ek açıklamalarına ihtiyacımız olduğunda kullanırız . 
+ Yani kısacası aynı türde birden fazla açıklama kullanmak istediğimizde kullanırız .

```xml
    @PostMapping("/save")
    @Caching(put = @CachePut(value = "cacheUsers" , key = "#result.id") , evict = @CacheEvict(value = "cacheUsers", allEntries = true))
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
```
+ Yukardaki örnekte yeni bir kullanıcı ekleriz @CachePut önbelleği günceller ve  eski listeyi @CacheEvict temizler . 

## @Cacheable İle @CachePut Arasındaki fark ? 
+ @Cacheable : Önbelleği kullanarak yöntem yürütülmesinin atlanmasına neden olur .
+ @CachePut : Yöntem her zaman yürütülür ve sonucu her zaman önbelleğe alır
