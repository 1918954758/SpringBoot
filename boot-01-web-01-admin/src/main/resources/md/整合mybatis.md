# 整合mybatis

[mybatis-GitHub地址](https://github.com/mybatis)
[mybatis-官方文档地址](https://mybatis.org/mybatis-3/zh/index.html)
- SpringBoot 官方的starter： spring-boot-starter-*
- 第三方的starter：*-spring-boot-starter

-导入mybatis依赖

```xml
<dependencys>
    <!-- Spring Boot 整合 Mybatis -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.4</version>
    </dependency>
</dependencys>
```

## mybatis纯配置的方式

### 1. 配置mybatis全局配置
location： mybatis/mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
</configuration>
```

### 2. 在yaml中配置mybatis全局配置位置
```yaml
#mybatis config
mybatis:
  #mybatis global config
  config-location: classpath:mybatis/mybatis-config.xml
```

### 3. 编写bean对象文件
```java
@Data
@ToString
public class UserTb {
    private Integer dpetNo;
    private String dpetName;
    private String loc;
}
```

### 4. 编写mapper接口文件
```java
@Mapper
public interface UserTbMapper {
    UserTb getUserTb(Integer dpetNo);
}
```
     
### 5. 配置mapper配置文件
mybatis/mapper/UserTbMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zichen.admin.mapper.UserTbMapper">
    <select id="getUserTb" resultType="com.zichen.admin.bean.UserTb">
        select * from dpet where dpetNo = #{dpetNo}
    </select>
</mapper>
```

### 6. 在yaml中配置mybatis的mapper文件位置
```yaml
#mybatis config
mybatis:
  #mybatis global config
  config-location: classpath:mybatis/mybatis-config.xml
  #mybatis mapper config
  mapper-locations: classpath:mybatis/mapper/UserTbMapper.xml
```

### 7. 编写service文件
```java
@Service
public class UserTbService {

    @Autowired
    private UserTbMapper userTbMapper;

    public UserTb getUserTbByDpetNo(Integer dpetNo) {
        return userTbMapper.getUserTb(dpetNo);
    }
}
```

### 8. 编写controller测试文件
```java
@Controller
public class UserTbController {

    @Autowired
    private UserTbService userTbService;

    @ResponseBody
    @GetMapping("/getUserTb")
    public String getUserTbByDpetNo(@RequestParam("id") Integer dpetNo) {
        UserTb userTbByDpetNo = userTbService.getUserTbByDpetNo(dpetNo);
        return userTbByDpetNo.toString();
    }
}
```

### 10. 注意，mapper接口都要标注 @Mapper 注解
### 11. 注意，service接口都要标注 @Service 注解
### 12. 注意，controller接口都要标注 @Controller 注解

### 13. 页面测试
![image-mybatis纯配置的方式测试结果](../image/mybatis纯配置的方式测试结果.png)


### 14. 补充
- mybatis-config.xml 全局配置
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- setting config -->
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="autoMappingBehavior" value="PARTIAL"/>
        <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="25"/>
        <setting name="defaultFetchSize" value="100"/>
        <setting name="safeRowBoundsEnabled" value="false"/>
        <!-- 开启驼峰命名规则 -->
        <setting name="mapUnderscoreToCamelCase" value="false"/>
        <setting name="localCacheScope" value="SESSION"/>
        <setting name="jdbcTypeForNull" value="OTHER"/>
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
    </settings>

    <!-- Aliases config -->
    <typeAliases>
        <typeAlias alias="UserTb" type="com.zichen.admin.bean.UserTb"/>
    </typeAliases>
</configuration>
```
## mybatis自动配置的方式