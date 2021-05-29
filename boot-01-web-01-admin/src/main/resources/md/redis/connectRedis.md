# 1. Connect Redis
## 1. 引入依赖
```xml
<dependencys>
    <!-- 引入redis 依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencys>
```

## 2. 配置redis连接地址
```yaml
spring:
  redis:
    host: 192.168.60.128
    port: 6379
    password: 123456
  #使用下面的URL不知道为什么连不上
  #url: redis://redis:123456@192.168.60.128:6379
```

## 3. 编写测试类
- StringRedisTemplate 连接redis的客户端<String, String>
- RedisTemplate 连接redis客户端<Object, Object>
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void connectRedisTest() {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("userName", "zichen");
        String userName = stringStringValueOperations.get("userName");
        log.info(userName);
    }
}
```

## 4. 测试结果
```text
2021-05-29 15:04:31.001  INFO 4496 --- [           main] com.zichen.admin.ConnectRedisTest        : zichen
```

# 2. 切换连接redis客户端
## 1. 引入jedis依赖
```xml
<dependencys>
    <!-- 引入jedis依赖 -->
    <!-- 由于springBoot版本管理有jedis的依赖版本，故此不需要写版本 -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
</dependencys>
```
- 此外还需要引入 commons-pool2 依赖
```xml
<dependencys>
    <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-pool2 -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
        <version>2.9.0</version>
    </dependency>
</dependencys>
```
- 如果不引入
    - JedisConnectionConfiguration中的@ConditionalOnClass({ GenericObjectPool.class, JedisConnection.class, Jedis.class })会报错


## 2. 配置连接redis客户端类型
- 在redis配置属性里面可以发现，需要配置连接redis客户端类型
```yaml
spring:
  redis:
    client-type: jedis
```

## 3. 测试
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    public void redisConnectionTypeTest() {
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        log.info(aClass.getName());
    }
}
```

## 4. 测试
```text
2021-05-29 15:44:08.245  INFO 10604 --- [           main] com.zichen.admin.ConnectRedisTest        : org.springframework.data.redis.connection.jedis.JedisConnectionFactory
```

# 3. 切换Lettuce 没有Jedis那么麻烦
## 1. 只需要修改yaml配置文件 client-type： lettuce 即可

## 2. 代码实现
```java
@Slf4j
@SpringBootTest
public class ConnectRedisTest {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    public void redisConnectionTypeTest() {
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        log.info(aClass.getName());
    }
}
```

## 3. yaml
```yaml
spring:
  redis:
    client-type: lettuce
```

## 4. 测试
```text
2021-05-29 15:45:50.763  INFO 2036 --- [           main] com.zichen.admin.ConnectRedisTest        : org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
```