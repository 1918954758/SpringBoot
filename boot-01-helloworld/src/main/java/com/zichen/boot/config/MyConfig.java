package com.zichen.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.zichen.boot.bean.Car;
import com.zichen.boot.bean.Pet;
import com.zichen.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

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
 *      解决问题：组件依赖
 *  4. @Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名：com.zichen.boot.bean.User
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //告诉springboot，这是一个配置类
/**
 * - 开启Car自动绑定功能
 * - 把Car自动注册到容器中
 */
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例
     * @return
     */
    @ConditionalOnBean(MyConfig.class)//表示，在容器中存在DataSource组件，就注入user01这个组件，没有则不注入
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

    /*
     * 自定义过滤器
     */
//    @Bean
//    public CharacterEncodingFilter filter() {
//        return null;
//    }
}
