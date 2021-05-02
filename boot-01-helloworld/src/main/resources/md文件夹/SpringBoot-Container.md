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

- @Bean、@Component、@Controller、@Service、@Repository

- @ComponentScan、@Import

- @Conditional

### 2. 原生配置文件引入
- @ImportResource
