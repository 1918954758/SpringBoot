## SpringBoot 容器功能
### 1. 组件添加
- 之前Spring添加组件需要在xml中进行配置
beans.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- spring 给容器添加组件的方式 -->
    <bean id="user01" class="com.zichen.boot.bean.User">
        <property name="name" value="zhangsan"></property>
        <property name="age" value="18"></property>
    </bean>

    <bean id="cat" class="com.zichen.boot.bean.Pet">
        <property name="name" value="tomcat"></property>
    </bean>

</beans>
```
- 现在给springboot容器添加组件，是需要写一个类，给这个类标注@Configuration注解
- @Configuration
```java
/**
 * @name: MyConfig
 * @description:
 * @author: zichen
 * @date: 2021/5/2  22:11
 */
/**
 * 1. 配置类里面使用@Bean标注在方法上给容器注册组件，默认是单例的
 * 2. 配置类本身也是一个组件
 * 3. proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true) 每一次调用都会使用代理对象
 *      Lite(proxyBeanMethods = false) 每一次调用不会使用代理对象，都会是一个新的对象
 */
@Configuration(proxyBeanMethods = true)  //告诉springboot，这是一个配置类
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例
     * @return
     */
    @Bean  //给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01() {
        return new User("zhangsan", 18);
    }

    @Bean("tom")//也可以自定义一个组件的名字，如 “tom”
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```
    > 测试组件，参见启动类中的打印
    > 配置类本身也是一个组件，也可以使用 run.getBean(MyConfig.class);来获取

- @Configuration注解的两种模式
    - Full模式    @Configuration(proxyBeanMethods = true)
    - Lite模式    @Configuration(proxyBeanMethods = false)
    - 配置类组件之间无依赖关系用Lite模式，加速容器启动过程，减少判断
    - 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
- @Bean、@Component、@Controller、@Service、@Repository
    - @Bean - 表示该方法是一个bean
    - @Component - 在类上表示该类是一个组件
    - @Controller - 表示是一个控制器
    - @Service - 表示是一个业务逻辑组件
    - @Repository - 表示是一个数据库层组件
- @ComponentScan、@Import
    - @ComponentScan - 指定包扫描规则
    - @Import - 给容器导入一个组件，写在任意一个配置类中或者组件中都可以
        - 将指定类型的组件导入进来
```java
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //告诉springboot，这是一个配置类
public class MyConfig {
    
}       
```
-
    -
        - 作用是调用导入的组建的无惨构造器，创建导入的组件
        - 验证导入的组件
```java
class MainApplication{
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("=============");
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        DBHelper bean = run.getBean(DBHelper.class);
        System.out.println("DBHelper组件 = " + beanNamesForType);
    }
}
```
```properties
# 运行结果
com.zichen.boot.bean.User
user01
DBHelper组件 = [Ljava.lang.String;@62515a47
```
- @Conditional
**条件装配：满足Condition指定的条件，则进行组件注入**
![image-ConditionAnnotation](../image/ConditionAnnotation.png)
**ConditionalOnBean** - 当容器中有Bean，才去注入组件
**ConditionalOnMissingBean** - 当容器中没有Bean，才去注入组件
**ConditionalOnClass** - 当容器中有class，才去注入组件
**ConditionalOnMissingClass** - 当容器中没有class，才去注入组件
**ConditionalOnResource** - 当项目中的类路径存在某个资源的时候，才去注入组件
**ConditionalOnJava** - 当是指定的java版本号的时候，才去注入组件
**ConditionalOnWebApplication** - 当我们的应用是一个web应用的时候，才去注入组件
**ConditionalOnNotWebApplication** - 当我们的应用不是一个web应用的时候，才去注入组件
**ConditionalOnProperty** - 当配置文件中配置了某个属性的时候，才去注入组件
- 以ConditionOnBean为例：
````java
/**
 * 告诉springboot，这是一个springboot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        boolean tom = run.containsBean("tom");
        System.out.println("容器中Tom组件：" + tom);
        boolean user01 = run.containsBean("user01");
        System.out.println("容器中user01组件：" + user01);
    }
}
````
```properties
# 测试结果
容器中Tom组件：false
容器中user01组件：true
```
```java
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //告诉springboot，这是一个配置类
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例
     * @return
     */
    @ConditionalOnBean(DataSource.class)//表示，在容器中存在DataSource组件，就注入user01这个组件，没有则不注入
    @Bean  //给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```
```properties
# 再次启动启动类，执行结果是：
容器中Tom组件：true
容器中user01组件：false

# 如果将@ConditionalOnBean(DataSource.class)改成@ConditionalOnBean(MyConfig.class)，再次启动启动类，执行结果是：
容器中Tom组件：true
容器中user01组件：true
```

**根据上面的测试，给user01组件加上@ConditionalOnBean(MyConfig.class)注解，表明，如果容器中有MyConfig组件，则注入user01组件，如果没有DataSource组件，则不注入user01组件**
**使用@ConditionalOnBean(name="tom")测试结果是，只要方法上加了该注解，就不会将该方法注入，不管tom组件是否存在，都不会注入**
**也可以将@ConditionalOnBean注解加到配置类上，表示，满足条件的时候，配置类中的所有组件都注入，不满足条件的时候，配置类中的所有组件都不注入**
### 2. 原生配置文件引入
- @ImportResource - 导入资源
    当我们需要将beans.xml中的bean引入的时候，我们可以使用@ImportResource注解来导入
```java
@ImportResource("classpath:beans.xml")
public class MyConfig {
    ...
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="haha" class="com.zichen.boot.bean.User">
        <property name="name" value="zhangsan"></property>
        <property name="age" value="18"></property>
    </bean>
    <bean id="hehe" class="com.zichen.boot.bean.Pet">
        <property name="name" value="tomcat"></property>
    </bean>
</beans>
```
```java
//测试
/**
 * 告诉springboot，这是一个springboot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("容器中haha组件：" + haha);
        System.out.println("容器中hehe组件：" + hehe);
    }
}
```
```properties
# 测试结果：
容器中haha组件：true
容器中hehe组件：true

# 当我们不使用@ImportResource注解的时候，测试结果：
容器中haha组件：false
容器中hehe组件：false
```

### 3. 配置绑定
使用java读取到properties文件中的内容，并且把它封装到javaBean中，以供随时使用

```java
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class getProperties {
    public static void main(String[] args) {
        Properties pps = new Properties();
        pps.load(new FileInputStream("a.properties"));
        Enumeration enumeration = pps.propertyNames();//得到配置文件的名字
        while (enumeration.hasMoreElements()) {
            String strKey = (String) enumeration.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + " = " + strValue);
        }
    }
}
```
- @ConfigurationProperties


- @EnableConfigurationProperties + @ConfigurationProperties


- @Component + @ConfigurationProperties

### 4. 自动配置原理入门
- 引导加载自动配置类