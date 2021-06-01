# 1. 访问页面，Redis计数
## 1. 添加拦截器，拦截请求
- 编写自己的拦截器拦截请求
    - 由于这里要操作Redis
    - 所以需要使用redisTemplate来操作redis
```java
@Slf4j
@Component
public class RedisUrlCountInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 每次访问，uri访问次数会加1
        String uri = request.getRequestURI();
        log.info("redis保存页面访问次数，拦截的路径是：{}", uri);
        redisTemplate.opsForValue().increment(uri);
        return true;
    }
}
```

- 将自己的拦截器添加到WebMvcConfigurer中
```java
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    /**
     * Filter和Interceptor功能差不多，如何确定使用那个?
     * Filter: filter即使脱离spring也可以使用
     * Interceptor: interceptor是spring特有的，脱离spring不可食用
     * 
     * 所以Interceptor在spring容器中已经是存在的，直接装配使用即可
     * 如果我们直接new一个Interceptor放到WebMvcConfigurer中的话
     * 我们自己的拦截器操作redis功能将不可用
     */
    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login4Postman", "/sql", "/css/**", "/fonts/**", "/images/**", "/js/**", "/saveUserTb", "/saveUserTb4Annotation", "/insertCity");
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login4Postman", "/sql", "/css/**", "/fonts/**", "/images/**", "/js/**", "/saveUserTb", "/saveUserTb4Annotation", "/insertCity");
    }
}
```

## 2. 测试
- 访问页面
  http://localhost:8080/
  http://localhost:8080/sql
## 3. 查看redis是否有计数
![image-redis计数结果](image/redis计数结果.png)

# 2. 将计数结果放到页面展示
## 1. 编写Controller
```java
@Controller
@Slf4j
public class IndexController {
    
  @Autowired
  StringRedisTemplate redisTemplate;
  
  @GetMapping("/main.html")
  public String indexPage(HttpSession session, Model model) {
    log.info("indexPage 执行");

    // 操作redis，获取数据 放到Model中
    ValueOperations opsForValue = redisTemplate.opsForValue();
    String s = opsForValue.get("/main.html");
    String s1 = opsForValue.get("/dynamic_table");
    String s2 = opsForValue.get("/jump_to_ajax");
    String s3 = opsForValue.get("/basic_table");
    model.addAttribute("main_count", s);
    model.addAttribute("dynamic_table_count", s1);
    model.addAttribute("jump_to_ajax_count", s2);
    model.addAttribute("basic_table_count", s3);
    return "index";
  }
}
```

## 2. 编写HTML
```html
<div class="col-md-6">
    <!--statistics start-->
    <div class="row state-overview">
        <div class="col-md-6 col-xs-12 col-sm-6">
            <div class="panel purple">
                <div class="symbol">
                    <i class="fa fa-gavel"></i>
                </div>
                <div class="state-value">
                    <div class="value" th:text="${main_count}">230</div>
                    <div class="title">访问主页次数</div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xs-12 col-sm-6">
            <div class="panel red">
                <div class="symbol">
                    <i class="fa fa-tags"></i>
                </div>
                <div class="state-value">
                    <div class="value" th:text="${dynamic_table_count}">3490</div>
                    <div class="title">访问动态表格次数</div>
                </div>
            </div>
        </div>
    </div>
    <div class="row state-overview">
        <div class="col-md-6 col-xs-12 col-sm-6">
            <div class="panel blue">
                <div class="symbol">
                    <i class="fa fa-money"></i>
                </div>
                <div class="state-value">
                    <div class="value" th:text="${favicon_count}">22020</div>
                    <div class="title">ajax请求页被访问次数</div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xs-12 col-sm-6">
            <div class="panel green">
                <div class="symbol">
                    <i class="fa fa-eye"></i>
                </div>
                <div class="state-value">
                    <div class="value" th:text="${basic_table_count}">390</div>
                    <div class="title">基本表被访问次数</div>
                </div>
            </div>
        </div>
    </div>
    <!--statistics end-->
</div>
```

## 3. 访问页面
- http://localhost:8080/
- http://localhost:8080/dynamic_table
- http://localhost:8080/jump_to_ajax
- http://localhost:8080/basic_table

## 4. 查看结果
![image-redis计数页面访问次数结果图片](image/redis计数页面访问次数结果图片.png)

# 3. 读取Redis数据问题
- 总结
  - 1. RedisTemplate使用的序列类在在操作数据的时候，比如说存入数据会将数据先序列化成字节数组然后在存入Redis数据库，这个时候打开Redis查看的时候，你会看到你的数据不是以可读的形式展现的，而是以字节数组显示
  - 2. 当然从Redis获取数据的时候也会默认将数据当做字节数组转化,这样就会导致一个问题，当需要获取的数据不是以字节数组存在redis当中而是正常的可读的字符串的时候
  - 3. 当Redis当中的数据值是以可读的形式显示出来的时候，只能使用StringRedisTemplate才能获取到里面的数据。
  - 4. 所以当你使用RedisTemplate获取不到数据的时候请检查一下是不是Redis里面的数据是可读形式而非字节数组,RedisTemplate就无法获取导数据，这个时候获取到的值就是NULL。这个时候StringRedisTempate就派上了用场。
  
```shell
[zichen@bogon bin]$ ./redis-cli
127.0.0.1:6379> auth 123456
OK
127.0.0.1:6379> keys *
1) "key2"
2) "key1"
127.0.0.1:6379>
```
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {
  @Test
  public void redisConnectionTest() {
    ValueOperations<Object, Object> operations = redisTemplates.opsForValue();
    //ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
    //String key1 = stringStringValueOperations.get("key1");
    //String key2 = stringStringValueOperations.get("key2");
    Object key1 = operations.get("key1");
    Object key2 = operations.get("key2");
    log.info("key1 【{}】 | key2 【{}】", key1 ,key2);
    //2021-06-01 17:03:49.052  INFO 6524 --- [           main] com.zichen.admin.ConnectRedisTest        : key1 【null】 | key2 【null】
  }
}
```
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {
  @Test
  public void redisConnectionTest() {
    //ValueOperations<Object, Object> operations = redisTemplates.opsForValue();
    ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
    String key1 = stringStringValueOperations.get("key1");
    String key2 = stringStringValueOperations.get("key2");
    log.info("key1 【{}】 | key2 【{}】", key1 ,key2);
    
    //2021-06-01 17:02:20.442  INFO 9172 --- [           main] com.zichen.admin.ConnectRedisTest        : key1 【123】 | key2 【456】
  }
}
```

- 测试RedisTemplate
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {
  @Test
  public void redisTemplateTest() {
    ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
    operations.set("binary1", "123456");

    Object binary1 = operations.get("binary1");
    log.info("binary1 = 【{}】", binary1);
    //2021-06-01 17:07:39.986  INFO 4336 --- [           main] com.zichen.admin.ConnectRedisTest        : binary1 = 【123456】
  }
}
```

```shell
127.0.0.1:6379> keys *
1) "key2"
2) "\xac\xed\x00\x05t\x00\abinary1"
3) "key1"
127.0.0.1:6379>
```