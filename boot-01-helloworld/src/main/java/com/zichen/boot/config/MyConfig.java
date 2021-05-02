package com.zichen.boot.config;

import com.zichen.boot.bean.Pet;
import com.zichen.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
 *
 */
@Configuration(proxyBeanMethods = false)  //告诉springboot，这是一个配置类
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例
     * @return
     */
    @Bean  //给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01() {
        return new User("zhangsan", 18);
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
