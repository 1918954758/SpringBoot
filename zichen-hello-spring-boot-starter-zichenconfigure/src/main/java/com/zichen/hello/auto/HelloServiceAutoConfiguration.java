package com.zichen.hello.auto;

import com.zichen.hello.HelloProperties;
import com.zichen.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: HelloServiceAutoConfiguration
 * @description:
 * @author: zichen
 * @date: 2021/6/12  1:03
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)//绑定配置文件，并且将其放在容器中
public class HelloServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        return new HelloService();
    }
}
