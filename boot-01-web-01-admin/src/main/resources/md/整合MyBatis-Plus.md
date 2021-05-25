# Mybatis-Plus

## 1. 添加依赖
```xml
<dependencys>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.3</version>
    </dependency>
<dependencys>
```

- 注意依赖间的引用，可以剔除重复的依赖
- 注掉 mybatis-spring-boot-starter
```xml
 <!-- Spring Boot 整合 Mybatis -->
<dependencys>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.4</version>
    </dependency>
</dependencys>
```

- mybatis配置文件需要注掉
    - 映射文件位置注掉
    - 驼峰命名注掉
    - 实体类别名包路径注掉
- mybatis-plus代替mybatis
    - 映射文件位置不需要写，默认在 classpath*:mapper/**/*.xml
    - 增加驼峰命名
    - 增加实体类别名包路径
```yaml
#mybatis:
  #mybatis global config
  #config-location: classpath:mybatis/mybatis-config.xml
  #mybatis mapper config
  #mapper-locations: classpath:mybatis/mapper/*.xml,classpath:mapper/*.xml
  #configuration:
    #map-underscore-to-camel-case: true
  #type-aliases-package: com.zichen.admin.bean

mybatis-plus:
  # config-location: default = "classpath*:/mapper/**/*.xml"
  type-aliases-package: com.zichen.admin.bean
  configuration:
    map-underscore-to-camel-case: true
```

## 2. MyBatis-Plus分析
- MyBatis
    - mapper接口
    - service接口
    - controller接口
    - mapper映射文件 xxxMapper.xml

- MyBaits-Plus
    - MybatisPlusAutoConfiguration.class注解解析
        - @ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
        - @ConditionalOnSingleCandidate(DataSource.class)  容器中存在唯一候选数据源时，MyBatis-Plus自动配置才生效
        - @EnableConfigurationProperties(MybatisPlusProperties.class) 绑定的自动配置属性
            - 自动配置类
                - @ConfigurationProperties(prefix = ""mybatis-plus"") public class MybatisPlusProperties {}
                - 默认配置了映射文件位置 private String[] mapperLocations = new String[]{"classpath*:/mapper/**/*.xml"};
    - bean解析
        - Bean:SqlSessionFactory    id:sqlSessionFactory
        - Bean:SqlSessionTemplate   id:sqlSessionTemplate

## 3. 准备数据
```sql
DROP TABLE IF EXISTS user;

CREATE TABLE `user`
(
    `id`    bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`  varchar(30) DEFAULT NULL COMMENT '姓名',
    `age`   int         DEFAULT NULL COMMENT '年龄',
    `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DELETE FROM user;

INSERT INTO 
    user (id, name, age, email) 
VALUES
    (1, 'Jone', 18, 'test1@baomidou.com'),
    (2, 'Jack', 20, 'test2@baomidou.com'),
    (3, 'Tom', 28, 'test3@baomidou.com'),
    (4, 'Sandy', 21, 'test4@baomidou.com'),
    (5, 'Billie', 24, 'test5@baomidou.com');
```

## 4. 配置数据连接信息
```yaml
spring:
  datasource:
    #url: jdbc:mysql://localhost:3306/mysql?serverTimezone=Asia/Shanghai&characterEncoding=utf8    SpringBoot 2.0+
    #url: jdbc:mysql://localhost:3306/mysql?serverTimezone=GMT    SpringBoot 2.0+
    url: jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Shanghai&characterEncoding=utf8    # SpringBoot 2.0+
    #url: jdbc:mysql://localhost:3306/zichen?serverTimezone=Asia/Shanghai&characterEncoding=utf8    SpringBoot 2.0+
    username: 'root'
    password: 'root'
    #type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## 5. 准备库表对应的实体类(非一一对应，因为有注解可以忽略数据库没有的字段)
```java
@Data
public class User {
    @TableField(exist = false)//User表中没有该字段
    private String userName;
    @TableField(exist = false)//User表中没有该字段
    private String passWord;

    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

## 6. mapper接口

- 建议在这里标注，因为，如果在启动类上标注，测试的时候注入的接口名会报错（IDEA问题，不影响功能）

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

## 7. 测试
```java
@Slf4j
@SpringBootTest
public class MyBaitsPlusSimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        User user = userMapper.selectById(6L);
        log.info("查询到的信息：{}", user);
    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectList(null);
        log.info("selectAll() : {}", users);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setId(9L);
        user.setName("zichen22");
        user.setAge(44);
        user.setEmail("zichen@554.com");
        userMapper.insert(user);
        log.info("插入的数据是：{}", user);
    }


    @Test
    public void update() {
        /**
         * 封装 set数据（要更新的数据值）
         */
        User user1 = new User();
        user1.setAge(27);
        user1.setEmail("123456@zichen.com");
        Wrapper<User> wUser = new Wrapper<User>() {
            @Override
            public String getSqlSegment() {
                return null;
            }

            /**
             * 封装where 条件
             * @return
             */
            @Override
            public User getEntity() {
                User user1 = new User();
                user1.setId(6L);
                return user1;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public void clear() {

            }
        };
        log.info("查询到更新前的信息：{}", userMapper.selectById(6L));
        log.info("======更新开始======");
        userMapper.update(user1, wUser);
        log.info("======更新完成======");
        log.info("查询到更新后的信息：{}", userMapper.selectById(6L));
    }


    @Test
    public void delete() {
        Wrapper<User> userWrapper = new Wrapper<User>() {
            /**
             * 用于生成 where 条件
             * @return
             */
            @Override
            public User getEntity() {
                User user = new User();
                user.setId(9L);
                return user;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public void clear() {

            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        };
        log.info("查询到delete前的信息：{}", userMapper.selectById(9L));
        userMapper.delete(userWrapper);
        log.info("查询到delete后的信息：{}", userMapper.selectById(9L));
    }

    @Test
    public void deleteById() {
        log.info("查询到delete前的信息：{}", userMapper.selectById(8L));
        userMapper.deleteById(8L);
        log.info("查询到delete前的信息：{}", userMapper.selectById(8L));

    }
}
```

## 8. 补充
- 默认规则，MyBatis-Plus 会将实体类名当做表名（eg. User），去数据库查找表
- 如果数据库的表名和实体类名不对应，那么可以使用注解 @TableName(value = "table_name")（value可以不用写）

