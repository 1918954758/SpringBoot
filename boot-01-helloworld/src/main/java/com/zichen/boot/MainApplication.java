package com.zichen.boot;

import com.zichen.boot.bean.Pet;
import com.zichen.boot.bean.User;
import com.zichen.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sun.applet.Main;

/**
 * @name: MainApplication
 * @description:
 * @author: zichen
 * @date: 2021/5/2  18:24
 */

/**
 * 告诉springboot，这是一个springboot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        // 2. 查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();

        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件、注册的组件就是单例的
        User user011 = run.getBean("user01", User.class);
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        MyConfig myConfig = run.getBean(MyConfig.class);
        System.out.println("配置类组件获取 = " + myConfig);

        System.out.println("验证组件是单例的 = " + (tom01 == tom02));

        // 如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件在容器中有，如果有就不会新创，保持单例
        User user1 = myConfig.user01();
        User user2 = myConfig.user01();
        System.out.println(user1 == user2);
    }
}
